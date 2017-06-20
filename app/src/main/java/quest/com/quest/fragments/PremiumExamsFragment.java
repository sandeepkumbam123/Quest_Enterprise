package quest.com.quest.fragments;

import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Headers;
import quest.com.quest.Adapters.RecyclerAdapter;
import quest.com.quest.NetworkUtils.ApiConstants;
import quest.com.quest.NetworkUtils.Constants;
import quest.com.quest.NetworkUtils.GraysAPIRequests;
import quest.com.quest.NetworkUtils.RequestConstants;
import quest.com.quest.NetworkUtils.RetrofitAPIRequests;
import quest.com.quest.NetworkUtils.RetrofitRequestHandler;
import quest.com.quest.R;
import quest.com.quest.SqliteDb.Database;
import quest.com.quest.Utils.PrefUtils;
import quest.com.quest.Utils.Utilities;
import quest.com.quest.activities.DashBoardActivity;
import quest.com.quest.databinding.PremiumExamBinding;
import quest.com.quest.dialog.QuestDialog;
import quest.com.quest.models.ListofExams;
import quest.com.quest.models.StartExamModel;

/**
 * Created by skumbam on 03-03-2017.
 */

public class PremiumExamsFragment extends Fragment implements  RecyclerAdapter.onExamDetailsClicked {
    public static final String TAG = PremiumExamsFragment.class.getSimpleName();
private  PremiumExamBinding dataBinding;
    private RecyclerAdapter upcomingExamsListAdapter;
    private ListofExams upComingExamsList;
    private Map<String,Object> examListRequestData = new HashMap<>();
    private Database mDB;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBar();
        mDB = new Database(getActivity());
        setHasOptionsMenu(false);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dataBinding = DataBindingUtil.bind(inflater.inflate(R.layout.premium_exam,null,false));
        dataBinding.setFragment(this);
        examListRequestData.put(ApiConstants.BRANCH_ID,PrefUtils.getExamIdDetailsfromSP(getActivity(),ApiConstants.BRANCH_ID));
        examListRequestData.put(ApiConstants.USER_ID,PrefUtils.getExamIdDetailsfromSP(getActivity(),ApiConstants.USER_ID));
        getExamsList(examListRequestData);
        return dataBinding.getRoot();

    }

    private void initViews(ListofExams.ListOfScheduledExamsBean examBean) {
        dataBinding.examDate.setText(Utilities.returnDatefromString(examBean.getExam_date()).get(Calendar.DAY_OF_MONTH)+"");
        dataBinding.examDay.setText(Utilities.returnDatefromString(examBean.getExam_date()).get(Calendar.DAY_OF_WEEK)+"");
        dataBinding.examTitle.setText(examBean.getTitle());
        dataBinding.duration.setText(examBean.getDuration()+" Minutes");
        dataBinding.topicsRelatedTo.setText(examBean.getTopics_covered());
        dataBinding.topicName.setText(examBean.getTitle());
        dataBinding.numberOfQuestions.setText(examBean.getNumber_of_questions()+" Questions");
        dataBinding.numberOfMarks.setText(examBean.getTotal_marks()+" Marks");

        PrefUtils.writeExamIdDetaisinSP(getActivity(),PrefUtils.getInstance(getActivity()),ApiConstants.EXAM_ID,examBean.getExam_manualID());
    }

    private void setUpcomingExams() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        dataBinding.recyclerView.setLayoutManager(mLayoutManager);
        dataBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());

        DividerItemDecoration verticalDecoration = new DividerItemDecoration(dataBinding.recyclerView.getContext(),
                DividerItemDecoration.HORIZONTAL);
        Drawable verticalDivider = ContextCompat.getDrawable(getActivity(), R.drawable.vertical_divider);
        verticalDecoration.setDrawable(verticalDivider);
        dataBinding.recyclerView.addItemDecoration(verticalDecoration);

        DividerItemDecoration horizontalDecoration = new DividerItemDecoration(dataBinding.recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        Drawable horizontalDivider = ContextCompat.getDrawable(getActivity(), R.drawable.horizontal_divider);
        horizontalDecoration.setDrawable(horizontalDivider);
        dataBinding.recyclerView.addItemDecoration(horizontalDecoration);
        upcomingExamsListAdapter = new RecyclerAdapter(getActivity(),upComingExamsList ,this);
        dataBinding.recyclerView.setAdapter(upcomingExamsListAdapter);

    }

    private void setToolBar() {

        ((DashBoardActivity) getActivity())
                .setToolbarTitle("Sandeep","Premium Exams");
    }


    public void startExam(View v){

        HashMap<String , Object> params = new HashMap<>();
        params.put(ApiConstants.EXAM_ID ,PrefUtils.getDetailsfromSP(getActivity(),ApiConstants.EXAM_ID));
        startTest(params);

    }


    private void getExamsList(Map<String,Object> params){
        new RetrofitRequestHandler(getActivity()).examsList(RequestConstants.REQ_EXAMS_LIST, params, new RetrofitAPIRequests.ResponseListener<ListofExams>() {
            @Override
            public void onSuccess(int requestId, Headers headers, ListofExams response) {
                if(response.isIs_success()){
                    upComingExamsList = response;
                    setUpcomingExams();
                }else {
                    QuestDialog.showOkDialog(getActivity(),response.getErrorCode() +"",response.getErrorMessage());
                }
            }

            @Override
            public void onFailure(int requestId, Throwable error) {
                Log.d(TAG,error.toString());
            }
        });
    }


    @Override
    public void onClick(ListofExams.ListOfScheduledExamsBean examsBean) {

        initViews(examsBean);
    }

    public void startTest(Map<String,Object> params){
        new RetrofitRequestHandler(getActivity()).startExam(RequestConstants.REQ_START_EXAM,
                params, new RetrofitAPIRequests.ResponseListener<StartExamModel>() {
                    @Override
                    public void onSuccess(int requestId, Headers headers, StartExamModel response) {
                        if(response.isIsSuccess()){
                            Bundle b = new Bundle();
                            b.putString(ApiConstants.EXAM_ID , PrefUtils.getDetailsfromSP(getActivity(),ApiConstants.EXAM_ID));
                            getActivity().getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.fl_container,QuestionFragment.getInstance(b))
                                    .commit();
                            mDB.deleteQuestionsListFromTable(mDB);
                            mDB.insertQuestionsintoTable(mDB,response ,PrefUtils.getDetailsfromSP(getActivity(),ApiConstants.EXAM_ID));

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


