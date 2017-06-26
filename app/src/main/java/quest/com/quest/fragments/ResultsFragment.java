package quest.com.quest.fragments;

import android.databinding.DataBindingUtil;
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
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import quest.com.quest.Adapters.FastestAnswerModelAdapter;
import quest.com.quest.R;
import quest.com.quest.SqliteDb.Database;
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
    private Database mDB;
    private FastestAnswerModelAdapter fastestAnswerModelAdapter;

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
        mDB = new Database(getActivity());
        mDB.deleteQuestionsListFromTable(mDB);
        setToolBar();
        setHasOptionsMenu(false);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dataBinding = DataBindingUtil.bind(inflater.inflate(R.layout.result_analysis,null,false));
        dataBinding.setFragment(this);
        if(getArguments().getParcelable(RESULT_DATA)!= null)
            resultModel = getArguments().getParcelable(RESULT_DATA);
        mOverallStatsGraph = dataBinding.pieChart;
        addDatatoGraph();
        return dataBinding.getRoot();
    }



    private  void addDatatoGraph(){
        ArrayList<Entry> mPieChartData = new ArrayList<>();
        if(resultModel!= null) {

            mPieChartData.add(new Entry(resultModel.getNumberofCorrectAnswers(), 0));
            mPieChartData.add(new Entry(resultModel.getNumberofAttemptedAnswers() - resultModel.getNumberofCorrectAnswers(), 1));
            mPieChartData.add(new Entry(resultModel.getTotalQuestions() - resultModel.getNumberofAttemptedAnswers(), 2));

        }

        PieDataSet dataset = new PieDataSet(mPieChartData, " status of Answers");
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);


        ArrayList<String> mXlabels = new ArrayList<String>();
        mXlabels.add("Correct");
        mXlabels.add("Incorrect");
        mXlabels.add("Not Attempted");


        PieData data = new PieData(mXlabels , dataset);

        mOverallStatsGraph.setData(data);

        mOverallStatsGraph.setDescription("Result Analysis");

        if(resultModel!=null){
            dataBinding.tvExamtitle.setText(resultModel.getExamTitle());
            dataBinding.timeSpent.setText(Utilities.returnTime(resultModel.getTimeTakentoAttempt()));
            dataBinding.obtainedMarks.setText(resultModel.getObtainedMarks()+" / "+resultModel.getExamTotalMarks());
            int percentage = resultModel.getObtainedMarks()*100/resultModel.getExamTotalMarks();
            dataBinding.percentageOfMarks.setText(""+percentage);
            dataBinding.correctAnswers.setText(resultModel.getNumberofCorrectAnswers()+"");
            dataBinding.inCorrectAnswer.setText(""+(resultModel.getNumberofAttemptedAnswers()-resultModel.getNumberofCorrectAnswers()));
            dataBinding.tvSkippedAnswer.setText((resultModel.getTotalQuestions()-resultModel.getNumberofAttemptedAnswers())+"");

            fastestAnswerModelAdapter = new FastestAnswerModelAdapter(getActivity(),resultModel.getFastestAttemptedAnswers());
            dataBinding.fastestQuestions.setAdapter(fastestAnswerModelAdapter);

        }
    }
    private void setToolBar() {

        ((DashBoardActivity) getActivity()).setToolbarTitle("Sandeep","Results");
    }


    public void getSolutions(View v){
        Toast.makeText(getActivity(), "Will be available later", Toast.LENGTH_SHORT).show();
    }

}
