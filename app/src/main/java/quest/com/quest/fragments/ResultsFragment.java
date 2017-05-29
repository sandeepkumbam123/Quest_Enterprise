package quest.com.quest.fragments;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
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

import java.util.ArrayList;

import quest.com.quest.R;
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
}
