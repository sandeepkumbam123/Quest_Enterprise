package quest.com.quest.fragments;

import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import okhttp3.Headers;
import quest.com.quest.NetworkUtils.ApiConstants;
import quest.com.quest.NetworkUtils.RequestConstants;
import quest.com.quest.NetworkUtils.RetrofitAPIRequests;
import quest.com.quest.NetworkUtils.RetrofitRequestHandler;
import quest.com.quest.R;
import quest.com.quest.SqliteDb.Database;
import quest.com.quest.Utils.PrefUtils;
import quest.com.quest.activities.BaseActivity;
import quest.com.quest.activities.DashBoardActivity;
import quest.com.quest.databinding.FragmentQuestionBinding;
import quest.com.quest.dialog.QuestDialog;
import quest.com.quest.models.AttemptedQuestionModel;
import quest.com.quest.models.ExamIDModel;
import quest.com.quest.models.FastestAnswersModel;
import quest.com.quest.models.QuestionModel;
import quest.com.quest.models.ResultData;
import quest.com.quest.models.StartExamModel;
import quest.com.quest.models.SubmitResult;

import static android.view.View.GONE;

/**
 * Created by skumbam on 03-03-2017.
 */

public class QuestionFragment extends Fragment {
    private static String TAG = QuestionFragment.class.getSimpleName();
    private FragmentQuestionBinding dataBinding;
    QuestionModel qm;
    private Database database;
    private List<AttemptedQuestionModel> models  = new ArrayList<>();
    public final ObservableField<String> time =new ObservableField<>();
    CountDownTimer countDownTimer;
    public  int questionPosition =0;
    private AttemptedQuestionModel attemptedQuestionModel;
    HashMap<String , Object> requestData  = new HashMap<>();
    private int internalExamID;
    HashMap<String , Object> examData  = new HashMap<>();

    private Database mDB;

    private TextView mExamId;
    private TextView mSubjectId;

    private String mExamIDString;


    public static QuestionFragment getInstance(Bundle bundle){
        QuestionFragment fragment = new QuestionFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = new Database(getActivity());
        models = database.getQuestions(database);
        setToolBar();
        setHasOptionsMenu(true);

    }

    private void setToolBar() {

        ((DashBoardActivity) getActivity()).setToolbarTitle("Sandeep","Exam");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dataBinding = DataBindingUtil.bind(inflater.inflate(R.layout.fragment_question,null,false));
        dataBinding.setFragment(this);
        initViews();
        if(getArguments().getString(ApiConstants.EXAM_ID) !=null){
            mExamIDString = getArguments().getString(ApiConstants.EXAM_ID);
            dataBinding.examId.setText(mExamIDString+"");

        }

        examData.put(ApiConstants.MANUAL_ID , mExamIDString );
        getExamID(examData);

        mDB = new Database(getActivity());
        Collections.shuffle(models);
        createQuestionFragment(0,models.get(0));

        //sample for branch push

        countDownTimer(models.get(0).getExamDuration(),dataBinding.timerCountdown);
        return dataBinding.getRoot();
    }

    private void initViews() {
        mExamId = dataBinding.examId;
        mSubjectId = dataBinding.subjectId;

        mExamId.setText(models.get(0).getExamId());
        mSubjectId.setText(models.get(0).getExamTitle());

        if(models.size()==1){
            dataBinding.btSubmitExam.setVisibility(View.VISIBLE);
            dataBinding.btnNextQuestion.setVisibility(GONE);
            dataBinding.btnPreviousquestion.setVisibility(GONE);
        }else{
            dataBinding.btSubmitExam.setVisibility(GONE);
            dataBinding.btnNextQuestion.setVisibility(View.VISIBLE);
            dataBinding.btnPreviousquestion.setVisibility(View.VISIBLE);
        }


    }


    public void getNextQuestion(View v){
        questionPosition++;
       /* questionPosition = questionPosition +1;
        if(questionPosition < models.size()-1){*/
        if(questionPosition <=models.size()){
            dataBinding.btnNextQuestion.setEnabled(true);
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,  R.anim.enter_from_left, R.anim.exit_to_right);

            transaction.replace(R.id.fl_question_container,QuestionTagFragment.getInstance(models.get(questionPosition),mExamIDString));
            transaction.commit();
        }
       /* }else{
            dataBinding.btnNextQuestion.setEnabled(false);

        }
*/
        if(questionPosition+1 == models.size()){
            dataBinding.btSubmitExam.setVisibility(View.VISIBLE);
            dataBinding.btnNextQuestion.setEnabled(false);
            dataBinding.btnPreviousquestion.setEnabled(true);
        }else{
            dataBinding.btSubmitExam.setVisibility(GONE);
            dataBinding.btnNextQuestion.setEnabled(true);
            dataBinding.btnPreviousquestion.setEnabled(true);
        }


    }

    public void getPreviousQuestion(View v){
//        models.set(questionPosition,((DashBoardActivity)getActivity()).attemptedQuestionModel);
        questionPosition--;
//        models = null;
//        models = mDB.getQuestions(database);
       /* questionPosition = questionPosition -1 ;
        if(questionPosition<0){
            dataBinding.btnPreviousquestion.setEnabled(false);
        }
        else {*/
        dataBinding.btnPreviousquestion.setEnabled(true);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);
        transaction.replace(R.id.fl_question_container, QuestionTagFragment.getInstance(models.get(questionPosition),mExamIDString));
        transaction.commit();
        if(questionPosition-1 < 0){
            dataBinding.btnPreviousquestion.setEnabled(false);
            dataBinding.btnNextQuestion.setEnabled(true);
        }else{
            dataBinding.btnPreviousquestion.setEnabled(true);
            dataBinding.btnNextQuestion.setEnabled(true);
        }

//        }
    }

    public  void createQuestionFragment(int position , AttemptedQuestionModel model){
        if(countDownTimer!=null){
            countDownTimer.cancel();
        }
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fl_question_container, QuestionTagFragment.getInstance(model,mExamIDString)).commit();
    }



    public void submitResult(View v){
        countDownTimer.cancel();
        attemptedQuestionModel = ((DashBoardActivity)getActivity()).getAttemptedModel();
        if(attemptedQuestionModel != null ) {
            mDB.updateAttemptedAnswer(mDB, attemptedQuestionModel, attemptedQuestionModel.getCriticality(), attemptedQuestionModel.getExamDuration()
                    , attemptedQuestionModel.getExamTitle(), attemptedQuestionModel.getTotalMarks(), attemptedQuestionModel.getNegativeMarks() ,mExamIDString);
        }


        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_container, ResultsFragment.getInstance(resultCalculation()))
                .addToBackStack(null)
                .commit();
    }


    public void submitResult(){
        countDownTimer.cancel();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_container, ResultsFragment.getInstance(resultCalculation()))
                .addToBackStack(null)
                .commit();
    }
    public  void countDownTimer(int timer, final TextView textView){
        countDownTimer = new CountDownTimer(timer*1000, 1000) {

            public void onTick(long millis) {
                long second = (millis / 1000) % 60;
                long minute = (millis / (1000 * 60)) % 60;
                long hour = (millis / (1000 * 60 * 60)) % 24;
                String time ="";
                if(hour==0){
                    time = String.format("%02d:%02d", minute, second);
                }else if(hour==0 && minute==0 && second ==0){
                    submitResult();

                }
                else {
                    time = String.format("%02d:%02d:%02d", hour, minute, second);
                }

                textView.setText(time);
            }

            public void onFinish() {
                submitResult();
            }
        };

        countDownTimer.start();
    }


    public ResultData resultCalculation(){
        List<AttemptedQuestionModel> modelList = database.getQuestions(database);
        String examId = modelList.get(0).getExamId();
        String examTitle = modelList.get(0).getExamTitle();
        Map<Integer,Integer> listofAnswersData = new HashMap<>();

        int correctAnswers = 0;
        int attemptedAnswers =0;
        int obtainedMarks =0;
        List<FastestAnswersModel> fastestCorrectAnswers = new ArrayList<>();
        JSONObject jsonObject = new JSONObject();
        int timeTakentoAttempt = 0;
        for(AttemptedQuestionModel model : modelList){

            if(model.getAttemptedAnswer() == model.getCorrectAnswer()){
                correctAnswers = correctAnswers+1;
                attemptedAnswers = attemptedAnswers+1;
                obtainedMarks =model.getAnswerMark()+obtainedMarks;
                if(fastestCorrectAnswers.size()<5){
                    FastestAnswersModel fastModel = new FastestAnswersModel(model.getQuestionNumber(),
                            model.getTimeTakentoAttempt(),model.getQuestionNumber());
                    fastestCorrectAnswers.add(fastModel);

                }
                else {
                    for(FastestAnswersModel fastavailablemodel :fastestCorrectAnswers){
                        if(model.getTimeTakentoAttempt() < fastavailablemodel.getTimeDuration()){
                            fastestCorrectAnswers.remove(fastavailablemodel);
                            fastestCorrectAnswers.add(new FastestAnswersModel(model.getQuestionNumber(),
                                    model.getTimeTakentoAttempt(),model.getQuestionNumber()));
                            break;
                        }
                    }
                }
            }
            else if(model.getAttemptedAnswer() != model.getCorrectAnswer() && model.getAttemptedAnswer()!=0){
                attemptedAnswers=attemptedAnswers+1;
                obtainedMarks = obtainedMarks-model.getNegativeMarks();

            }

            listofAnswersData.put(Integer.parseInt(model.getQuestionNumber()),model.getAttemptedAnswer());

            try {
                switch (model.getAttemptedAnswer()){
                    case 0:
                        jsonObject.put(model.getQuestionNumber(),"");
                        break;
                    case 1:
                        jsonObject.put(model.getQuestionNumber(),ApiConstants.OPTION_1);
                        break;
                    case 2:
                        jsonObject.put(model.getQuestionNumber(),ApiConstants.OPTION_2);
                        break;
                    case 3:
                        jsonObject.put(model.getQuestionNumber(),ApiConstants.OPTION_3);
                        break;
                    case 4:
                        jsonObject.put(model.getQuestionNumber(),ApiConstants.OPTION_4);
                        break;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            timeTakentoAttempt = timeTakentoAttempt + model.getTimeTakentoAttempt();
        }

        requestData.put(ApiConstants.USer_ANSWER_DATA , jsonObject.toString());
        requestData.put(ApiConstants.EXAM_ID ,internalExamID);
        requestData.put(ApiConstants.STUDENT_ID , PrefUtils.getExamIdDetailsfromSP(getActivity(),ApiConstants.USER_ID));
        requestData.put(ApiConstants.TOTAL , modelList.get(0).getTotalMarks());
        requestData.put(ApiConstants.IS_PASS , (obtainedMarks/modelList.get(0).getTotalMarks() >= 0.35 ? "yes" :"no"));
        submitResult(requestData);
        return  new ResultData(examId,examTitle,modelList.get(0).getExamDuration(),modelList.get(0).getTotalMarks(),obtainedMarks,attemptedAnswers,
                correctAnswers,"",fastestCorrectAnswers , modelList.size(),listofAnswersData,modelList.get(0).getTimeTakentoAttempt());

    }

    private void submitResult(Map<String , Object> params){
        new RetrofitRequestHandler(getActivity()).submitExam(RequestConstants.REQ_SUBMIT_EXAM, params, new RetrofitAPIRequests.ResponseListener<SubmitResult>() {
            @Override
            public void onSuccess(int requestId, Headers headers, SubmitResult response) {
                if(!response.ismSuccess()){
                    QuestDialog.showOkDialog(getActivity(),response.getmErrorCode(),response.getmErrorMessage());
                }
            }

            @Override
            public void onFailure(int requestId, Throwable error) {
                Log.d(TAG ,error.toString());
            }
        });
    }


    private void getExamID(Map<String , Object> params){
        new RetrofitRequestHandler(getActivity()).getExamId(RequestConstants.REQ_GET_EXAM_ID, params, new RetrofitAPIRequests.ResponseListener<ExamIDModel>() {
            @Override
            public void onSuccess(int requestId, Headers headers, ExamIDModel response) {
                if(response != null && response.isSuccess()){
                    internalExamID = response.getExamId();
                }
            }

            @Override
            public void onFailure(int requestId, Throwable error) {
                Log.d(TAG , error.toString());


            }
        });
    }


}
