package quest.com.quest.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import quest.com.quest.R;
import quest.com.quest.databinding.FragmentQuestiongroupBinding;

/**
 * Created by skumbam on 08-03-2017.
 */

public class QuestionTagFragment extends Fragment {

private FragmentQuestiongroupBinding mBinding;


    public static QuestionTagFragment getInstance(){
        QuestionTagFragment fragment = new QuestionTagFragment();
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mBinding = DataBindingUtil.bind(inflater.inflate(R.layout.fragment_questiongroup,container,false));


        return mBinding.getRoot();
    }
}
