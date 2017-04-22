package quest.com.quest.Teacher;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import quest.com.quest.R;
import quest.com.quest.databinding.SearchStudentDataBinding;

/**
 * Created by kumbh on 26-03-2017.
 */

public class SearchStudentFragment extends Fragment {

    SearchStudentDataBinding dataBinding;
    private  boolean isOnlyOneExam = false;
    public static SearchStudentFragment getInstance(Activity activity , Bundle bundle){
        SearchStudentFragment fragment = new SearchStudentFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dataBinding= DataBindingUtil.bind(inflater.inflate(R.layout.search_student_data,container,false));
        dataBinding.setFragment(this);

        return dataBinding.getRoot();
    }


    public void searchStudentData(View v){
        if(isOnlyOneExam){
              getActivity().getSupportFragmentManager().beginTransaction()
                      .replace(R.id.teacher_frame,AnswerDetailsFragment.getInstance(getActivity(),new Bundle()))
                      .commit();
        }
        else {
           getActivity().getSupportFragmentManager().beginTransaction()
                   .replace(R.id.teacher_frame,StudentStatsFragment.getInstance(getActivity(),new Bundle()))
                   .commit();
        }
    }
}
