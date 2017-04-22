package quest.com.quest.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import quest.com.quest.R;
import quest.com.quest.activities.DashBoardActivity;
import quest.com.quest.databinding.ResultAnalysisBinding;

/**
 * Created by skumbam on 03-03-2017.
 */

public class ResultsFragment extends Fragment {

private ResultAnalysisBinding dataBinding;

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
        return dataBinding.getRoot();
    }

    private void setToolBar() {

        ((DashBoardActivity) getActivity()).setToolbarTitle("Sandeep","Results");
    }


    public void getSolutions(View v){
        Toast.makeText(getActivity(), "Waiting for the screens", Toast.LENGTH_SHORT).show();
    }
}
