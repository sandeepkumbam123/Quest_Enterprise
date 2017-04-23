package quest.com.quest.Teacher;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import quest.com.quest.R;
import quest.com.quest.databinding.EnableDisableExamBinding;

/**
 * Created by kumbh on 02-04-2017.
 */

public class ExamEnableDisableFragment extends Fragment {
    private EnableDisableExamBinding dataBinding;


    public  static ExamEnableDisableFragment getInstance(Activity activity,Bundle data){
        return new ExamEnableDisableFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dataBinding = DataBindingUtil.bind(inflater.inflate(R.layout.enable_disable_exam,container,false));
        dataBinding.setFragment(this);
        setToolBar();
        return dataBinding.getRoot();
    }


    @Override
    public void onStart() {
        super.onStart();

    }


    public void enableClick(View v){
        Toast.makeText(getActivity(), "this exam is enabled", Toast.LENGTH_SHORT).show();
    }

    public void disableClick(View v){
        Toast.makeText(getActivity(), "This exam is disabled", Toast.LENGTH_SHORT).show();
    }
    private void setToolBar() {

        ((TeacherDashBoardActivity) getActivity())
                .setToolbarTitle("Sandeep","Exam Properties");
    }
}
