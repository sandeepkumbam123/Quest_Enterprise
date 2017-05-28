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
import quest.com.quest.activities.DashBoardActivity;
import quest.com.quest.databinding.PremiumExamBinding;
import quest.com.quest.dialog.QuestDialog;
import quest.com.quest.models.ListofExams;

/**
 * Created by skumbam on 03-03-2017.
 */

public class PremiumExamsFragment extends Fragment {
    public static final String TAG = PremiumExamsFragment.class.getSimpleName();
private  PremiumExamBinding dataBinding;
    private RecyclerAdapter upcomingExamsListAdapter;
    private ListofExams upComingExamsList;
    private Map<String,String> examListRequestData = new HashMap<>();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBar();
        setHasOptionsMenu(false);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dataBinding = DataBindingUtil.bind(inflater.inflate(R.layout.premium_exam,null,false));
        dataBinding.setFragment(this);
        examListRequestData.put(ApiConstants.BRANCH_ID,String.valueOf(4));
        examListRequestData.put(ApiConstants.EXAM_ID,String.valueOf(1));
        getExamsList(examListRequestData);
        return dataBinding.getRoot();

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
        upcomingExamsListAdapter = new RecyclerAdapter(getActivity(),upComingExamsList);
        dataBinding.recyclerView.setAdapter(upcomingExamsListAdapter);

    }

    private void setToolBar() {

        ((DashBoardActivity) getActivity())
                .setToolbarTitle("Sandeep","Premium Exams");
    }


    public void startExam(View v){
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_container,new QuestionFragment())
                .commit();
    }


    private void getExamsList(Map<String,String> params){
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
}
