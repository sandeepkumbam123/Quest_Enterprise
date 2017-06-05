package quest.com.quest.fragments;

import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import quest.com.quest.NetworkUtils.ApiConstants;
import quest.com.quest.R;
import quest.com.quest.SqliteDb.Database;
import quest.com.quest.activities.BaseActivity;
import quest.com.quest.activities.DashBoardActivity;
import quest.com.quest.databinding.FragmentQuestionBinding;
import quest.com.quest.dialog.QuestDialog;
import quest.com.quest.models.AttemptedQuestionModel;
import quest.com.quest.models.FastestAnswersModel;
import quest.com.quest.models.QuestionModel;
import quest.com.quest.models.ResultData;
import quest.com.quest.models.StartExamModel;

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
    private AnswerSubmitted answerSubmitted;

    private TextView mExamId;
    private TextView mSubjectId;

    private int examId;


    public static QuestionFragment getInstance(Bundle bundle){
        QuestionFragment fragment = new QuestionFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(getArguments().getInt(ApiConstants.EXAM_ID) !=0){
            examId = getArguments().getInt(ApiConstants.EXAM_ID);
            dataBinding.examId.setText(examId);
        }
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
        Collections.shuffle(models);
        createQuestionFragment(0,models.get(0));



        countDownTimer(models.get(0).getExamDuration(),dataBinding.timerCountdown);
        return dataBinding.getRoot();
    }

    private void initViews() {
        mExamId = dataBinding.examId;
        mSubjectId = dataBinding.subjectId;

        mExamId.setText(models.get(0).getExamId());
        mSubjectId.setText(models.get(0).getExamTitle());

    }


    public void getNextQuestion(View v){

        questionPosition = questionPosition +1;
        if(questionPosition <= models.size()){
            dataBinding.btnNextQuestion.setEnabled(true);
        }else{
            dataBinding.btnNextQuestion.setEnabled(false);
        }

        if(models.size() == questionPosition){
            dataBinding.btSubmitExam.setVisibility(View.VISIBLE);
        }else {
            dataBinding.btSubmitExam.setVisibility(View.GONE);
        }
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,  R.anim.enter_from_left, R.anim.exit_to_right);
        transaction.replace(R.id.fl_question_container,QuestionTagFragment.getInstance(models.get(questionPosition)));
        transaction.commit();
        answerSubmitted.onanswerSubmitted();
    }

    public void getPreviousQuestion(View v){
        questionPosition = questionPosition -1 ;
        if(questionPosition<0){
            dataBinding.btnPreviousquestion.setEnabled(false);
        }
        else
            dataBinding.btnPreviousquestion.setEnabled(true);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);
        transaction.replace(R.id.fl_question_container, QuestionTagFragment.getInstance(models.get(questionPosition)));
        transaction.commit();
        answerSubmitted.onanswerSubmitted();
    }

    public  void createQuestionFragment(int position , AttemptedQuestionModel model){
        if(countDownTimer!=null){
            countDownTimer.cancel();
        }
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fl_question_container, QuestionTagFragment.getInstance(model)).commit();
    }



    public void submitResult(View v){
        countDownTimer.cancel();
        answerSubmitted.onanswerSubmitted();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_container, ResultsFragment.getInstance(resultCalculation()))
                .addToBackStack(null)
                .commit();
    }


    public void submitResult(){
        countDownTimer.cancel();
        answerSubmitted.onanswerSubmitted();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_container, ResultsFragment.getInstance(resultCalculation()))
                .addToBackStack(null)
                .commit();
    }
    public  void countDownTimer(int timer, final TextView textView){
        countDownTimer = new CountDownTimer(timer, 1000) {

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
            else if(model.getAttemptedAnswer() != model.getCorrectAnswer()){
                attemptedAnswers=attemptedAnswers+1;
                obtainedMarks = obtainedMarks-model.getNegativeMarks();

            }
            listofAnswersData.put(Integer.parseInt(model.getQuestionNumber()),model.getAttemptedAnswer());



        }

        return  new ResultData(examId,examTitle,modelList.get(0).getExamDuration(),modelList.get(0).getTotalMarks(),obtainedMarks,attemptedAnswers,
                correctAnswers,"",fastestCorrectAnswers , modelList.size(),listofAnswersData);

    }


    public interface AnswerSubmitted{
        void onanswerSubmitted();
    }
}
