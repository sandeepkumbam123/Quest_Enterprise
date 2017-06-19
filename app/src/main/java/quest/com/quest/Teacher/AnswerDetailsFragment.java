package quest.com.quest.Teacher;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Headers;
import quest.com.quest.NetworkUtils.ApiConstants;
import quest.com.quest.NetworkUtils.RequestConstants;
import quest.com.quest.NetworkUtils.RetrofitAPIRequests;
import quest.com.quest.NetworkUtils.RetrofitRequestHandler;
import quest.com.quest.R;
import quest.com.quest.Utils.PrefUtils;
import quest.com.quest.activities.DashBoardActivity;
import quest.com.quest.databinding.AnswersListLayoutBinding;
import quest.com.quest.dialog.QuestDialog;
import quest.com.quest.fragments.QuestionFragment;
import quest.com.quest.models.ListofExams;
import quest.com.quest.models.StartExamModel;

/**
 * Created by kumbh on 26-03-2017.
 */

public class AnswerDetailsFragment extends Fragment {


    private static final String TAG = AnswerDetailsFragment.class.getSimpleName();
    private AnswersListLayoutBinding dataBinding;
    private RecyclerView recyclerAnswersList;
    private AnswersRecyclerAdapter adapter;
    private String examId;
    private Map<String , Object> requestData;

    public static AnswerDetailsFragment getInstance(Activity activity, Bundle bundle){
        AnswerDetailsFragment fragment = new AnswerDetailsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();

        examId = getArguments().getString(ApiConstants.EXAM_ID) != null ? getArguments().getString(ApiConstants.EXAM_ID) : "" ;
        requestData = new HashMap<>();
        requestData.put(ApiConstants.EXAM_ID , examId);
        getExamDetails(requestData);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dataBinding = DataBindingUtil.bind(inflater.inflate(R.layout.answers_list_layout,container,false));
        recyclerAnswersList = dataBinding.answersList;
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerAnswersList.setLayoutManager(layoutManager);
        dataBinding.tvSkippedAnswer.setVisibility(View.GONE);
        dataBinding.tvIncorrectAnswer.setVisibility(View.GONE);
        dataBinding.inCorrectAnswerCount.setVisibility(View.GONE);
        dataBinding.tvSkippedAnswerCount.setVisibility(View.GONE);
        dataBinding.correctLayout.setVisibility(View.INVISIBLE);
        dataBinding.incorrectLayout.setVisibility(View.INVISIBLE);
        dataBinding.skippedLayout.setVisibility(View.INVISIBLE);
        dataBinding.studentCode.setVisibility(View.GONE);
        setToolBar();


        return dataBinding.getRoot();
    }
    private void setToolBar() {

        ((TeacherDashBoardActivity) getActivity())
                .setToolbarTitle(PrefUtils.getDetailsfromSP(getActivity(),ApiConstants.USER_NAME),"Answers");
    }



    private void getExamDetails(Map<String ,Object> params){
        new RetrofitRequestHandler(getActivity()).startExam(RequestConstants.REQ_START_EXAM,
                params, new RetrofitAPIRequests.ResponseListener<StartExamModel>() {
                    @Override
                    public void onSuccess(int requestId, Headers headers, StartExamModel response) {
                        if(response.isIsSuccess()){
                            adapter = new AnswersRecyclerAdapter(getActivity(), response);
                            recyclerAnswersList.setAdapter(adapter);
                            dataBinding.tvStudentName.setText(response.getExamTitle());
                            dataBinding.tvCollegeName.setText(response.getDuration()+" Minutes");
                            dataBinding.tvCorrectAnswerCount.setText(response.getQuestionsList().size());
                            dataBinding.tvCorrectAnswer.setText("Number of Questions");




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
    }
}
