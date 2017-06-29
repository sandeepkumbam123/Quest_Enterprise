package quest.com.quest.Teacher;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Headers;
import quest.com.quest.Adapters.PreviousAnswersRecyclerAdapter;
import quest.com.quest.NetworkUtils.ApiConstants;
import quest.com.quest.NetworkUtils.RequestConstants;
import quest.com.quest.NetworkUtils.RetrofitAPIRequests;
import quest.com.quest.NetworkUtils.RetrofitRequestHandler;
import quest.com.quest.R;
import quest.com.quest.Utils.PrefUtils;
import quest.com.quest.databinding.AnswersListLayoutBinding;
import quest.com.quest.dialog.QuestDialog;
import quest.com.quest.models.PreviousExamsListModel;
import quest.com.quest.models.StartExamModel;

/**
 * Created by kumbh on 10-06-2017.
 */

public class PreviousAnswersFragment extends Fragment{

    private static final String TAG = PreviousAnswersFragment.class.getSimpleName();
    private AnswersListLayoutBinding dataBinding;
    private RecyclerView recyclerAnswersList;
    private PreviousAnswersRecyclerAdapter adapter;
    private String examId;
    private Map<String , Object> requestData;
    public static final String ANSWERS_MODEL = "answers_model";
    private PreviousExamsListModel modelData = null;



    public static PreviousAnswersFragment getInstance(Activity activity, Bundle bundle){
        PreviousAnswersFragment fragment = new PreviousAnswersFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onStart() {
        super.onStart();

        modelData = getArguments().getParcelable(ANSWERS_MODEL) != null ? (PreviousExamsListModel) getArguments().getParcelable(ANSWERS_MODEL) : null;
        if(modelData!=null){
            adapter = new PreviousAnswersRecyclerAdapter(getActivity(), modelData);
            recyclerAnswersList.setAdapter(adapter);
            dataBinding.tvStudentName.setText(modelData.getTitle());
            dataBinding.tvCollegeName.setText(modelData.getDuration()+"");
            dataBinding.studentCode.setText(modelData.getExam_manualID());
            dataBinding.ivStudentProfilePic.setVisibility(View.GONE);
            int skippedAnswer =0;
            int correctAnswer = 0;
            int inCorrectAnswer = 0;
            for(PreviousExamsListModel.QuestionListBean answersModel : modelData.getQuestion_list()){
                if(answersModel.getAnswer().equalsIgnoreCase(answersModel.getUser_anser())){
                    correctAnswer++;
                }else {
                    if(answersModel.getUser_anser().isEmpty() || answersModel.getUser_anser() == null){
                        skippedAnswer++;
                    }
                    else{
                        inCorrectAnswer++;
                    }
                }
            }
            dataBinding.inCorrectAnswerCount.setText(""+inCorrectAnswer);
            dataBinding.tvCorrectAnswerCount.setText(""+ correctAnswer);
            dataBinding.tvSkippedAnswerCount.setText(""+ skippedAnswer);
        }


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dataBinding = DataBindingUtil.bind(inflater.inflate(R.layout.answers_list_layout,container,false));
        recyclerAnswersList = dataBinding.answersList;
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerAnswersList.setLayoutManager(layoutManager);

        if(modelData!=null){
            adapter = new PreviousAnswersRecyclerAdapter(getActivity(), modelData);
            recyclerAnswersList.setAdapter(adapter);
            dataBinding.tvStudentName.setText(modelData.getTitle());
            dataBinding.tvCollegeName.setText(modelData.getDuration()+"");
            dataBinding.studentCode.setText(modelData.getExam_manualID());
            dataBinding.ivStudentProfilePic.setVisibility(View.GONE);
            int skippedAnswer =0;
            int correctAnswer = 0;
            int inCorrectAnswer = 0;
            for(PreviousExamsListModel.QuestionListBean answersModel : modelData.getQuestion_list()){
                if(answersModel.getAnswer().equalsIgnoreCase(answersModel.getUser_anser())){
                    correctAnswer++;
                }else {
                    if(answersModel.getUser_anser().isEmpty() || answersModel.getUser_anser() == null){
                        skippedAnswer++;
                    }
                    else{
                        inCorrectAnswer++;
                    }
                }
            }
            dataBinding.inCorrectAnswerCount.setText(""+inCorrectAnswer);
            dataBinding.tvCorrectAnswerCount.setText(""+ correctAnswer);
            dataBinding.tvSkippedAnswerCount.setText(""+ skippedAnswer);
        }
        setToolBar();


        return dataBinding.getRoot();
    }
    private void setToolBar() {

        ((TeacherDashBoardActivity) getActivity())
                .setToolbarTitle("Sandeep","Answers");
    }



   /* private void getExamDetails(Map<String ,Object> params){
        new RetrofitRequestHandler(getActivity()).startExam(RequestConstants.REQ_START_EXAM,
                params, new RetrofitAPIRequests.ResponseListener<StartExamModel>() {
                    @Override
                    public void onSuccess(int requestId, Headers headers, StartExamModel response) {
                        if(response.isIsSuccess()){
                            adapter = new Pre(getActivity(), response);
                            recyclerAnswersList.setAdapter(adapter);
                            dataBinding.tvStudentName.setText(response.getExamTitle());
                            dataBinding.tvCollegeName.setText(response.getDuration()+"");
                            dataBinding.tvCorrectAnswerCount.setText(response.getQuestionsList().size());
                            dataBinding.tvCorrectAnswer.setText("Number of Questions");


l

                        }else {
                            QuestDialog.showOkDialog(getActivity(),
                                    response.getErrorCode()+"",response.getErrorMessage());
                        }
                    }

                    @Override
                    public void onFailure(int requestId, Throwable error) {
                        Log.d(TAG,error.toString());
                    }
                });
    }*/
}
