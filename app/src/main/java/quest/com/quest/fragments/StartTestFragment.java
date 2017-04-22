package quest.com.quest.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import quest.com.quest.R;
import quest.com.quest.activities.DashBoardActivity;
import quest.com.quest.databinding.StartTestBinding;
import quest.com.quest.fragments.QuestionFragment;


/**
 * Created by skumbam on 03-03-2017.
 */

public class StartTestFragment extends Fragment {

    private StartTestBinding dataBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBar();
        setHasOptionsMenu(false);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        dataBinding= DataBindingUtil.bind(inflater.inflate(R.layout.start_test,null,false));
        dataBinding.setFragment(this);
        return dataBinding.getRoot();
    }


    private void setToolBar() {

        ((DashBoardActivity) getActivity()).setToolbarTitle("Sandeep","Take Exam");
    }
    public void startTest(View v){
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_container,new QuestionFragment())
                .commit();
    }


}
