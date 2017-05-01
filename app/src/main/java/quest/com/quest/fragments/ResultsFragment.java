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

/**
 * Created by skumbam on 03-03-2017.
 */

public class ResultsFragment extends Fragment {

private ResultAnalysisBinding dataBinding;
    private PieChart mOverallStatsGraph;


    public static ResultsFragment getInstance(){
        ResultsFragment fragment = new ResultsFragment();
        return fragment;
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
        mPieChartData.add(new PieEntry(4, 0));
        mPieChartData.add(new PieEntry(1, 1));
        mPieChartData.add(new PieEntry(2, 2));


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
        Toast.makeText(getActivity(), "Waiting for the screens", Toast.LENGTH_SHORT).show();
    }
}
