package quest.com.quest.fragments;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Headers;
import quest.com.quest.NetworkUtils.ApiConstants;
import quest.com.quest.NetworkUtils.RequestConstants;
import quest.com.quest.NetworkUtils.RetrofitAPIRequests;
import quest.com.quest.NetworkUtils.RetrofitRequestHandler;
import quest.com.quest.R;
import quest.com.quest.Utils.PrefUtils;
import quest.com.quest.Utils.Utilities;
import quest.com.quest.activities.DashBoardActivity;
import quest.com.quest.databinding.ResultAnalysisBinding;
import quest.com.quest.models.ResultData;

/**
 * Created by skumbam on 03-03-2017.
 */

public class ResultsFragment extends Fragment {

private ResultAnalysisBinding dataBinding;
    private PieChart mOverallStatsGraph;
    private ResultData resultModel;
private static final String RESULT_DATA = "RESULT_DATA";

    public static ResultsFragment getInstance(ResultData model){
        ResultsFragment fragment = new ResultsFragment();
        Bundle b = new Bundle();
        b.putParcelable(RESULT_DATA,model);
        fragment.setArguments(b);
        return fragment;
    }


    @Override
    public void onStart() {
        super.onStart();
        if(getArguments().getParcelable(RESULT_DATA)!= null){
            resultModel = getArguments().getParcelable(RESULT_DATA);
            Map<String ,String> remoteRequestData = new HashMap<>();
            remoteRequestData.put(ApiConstants.STUDENT_ID,String.valueOf(PrefUtils.getExamIdDetailsfromSP(getActivity(),ApiConstants.USER_ID)));
            remoteRequestData.put(ApiConstants.IS_PASS ,String.valueOf(resultModel.getObtainedMarks()/resultModel.getExamTotalMarks() >= .35  ));
            remoteRequestData.put(ApiConstants.CREATED_AT, Utilities.returnDatefromMillis(System.currentTimeMillis()));
            remoteRequestData.put(ApiConstants.UPDATED_AT , Utilities.returnDatefromMillis(System.currentTimeMillis()));
            remoteRequestData.put(ApiConstants.EXAM_ID,resultModel.getExamId());
            remoteRequestData.put(ApiConstants.USer_ANSWER_DATA,resultModel.getListofAnswersAttempted().toString());
            submitDatatoRemote(remoteRequestData);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBar();
        setHasOptionsMenu(false);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dataBinding = DataBindingUtil.bind(inflater.inflate(R.layout.result_analysis,null,false));
        dataBinding.setFragment(this);

        mOverallStatsGraph = dataBinding.pieChart;
        addDatatoGraph();
        return dataBinding.getRoot();
    }



    private  void addDatatoGraph(){
        ArrayList<PieEntry> mPieChartData = new ArrayList<>();
        mPieChartData.add(new PieEntry(resultModel.getNumberofCorrectAnswers(), 0));
        mPieChartData.add(new PieEntry(resultModel.getNumberofAttemptedAnswers()-resultModel.getNumberofCorrectAnswers(), 1));
        mPieChartData.add(new PieEntry(resultModel.getTotalQuestions() - resultModel.getNumberofAttemptedAnswers(), 2));


        PieDataSet dataset = new PieDataSet(mPieChartData, "");
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);


        ArrayList<String> mXlabels = new ArrayList<String>();
        mXlabels.add("Correct");
        mXlabels.add("Incorrect");
        mXlabels.add("Not Attempted");
        mXlabels.add("4");
        mXlabels.add("5");

        PieData data = new PieData(/*mXlabels*/ dataset);

        mOverallStatsGraph.setData(data);
        mOverallStatsGraph.setContentDescription("Overall Statistics of the Result");
        mOverallStatsGraph.setEntryLabelColor(Color.BLACK);
    }
    private void setToolBar() {

        ((DashBoardActivity) getActivity()).setToolbarTitle("Sandeep","Results");
    }


    public void getSolutions(View v){
//        Toast.makeText(getActivity(), "Waiting for the screens", Toast.LENGTH_SHORT).show();
    }



    private void submitDatatoRemote(Map<String,String> params){
        new RetrofitRequestHandler(getActivity()).submitExam(RequestConstants.REQ_SUBMIT_EXAM, params,
                new RetrofitAPIRequests.ResponseListener() {
                    @Override
                    public void onSuccess(int requestId, Headers headers, Object response) {

                    }

                    @Override
                    public void onFailure(int requestId, Throwable error) {

                    }
                });
    }
}
