package quest.com.quest.Teacher;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import quest.com.quest.Adapters.StudentStatusReportAdapter;
import quest.com.quest.R;
import quest.com.quest.databinding.StudentStatusFragmentBinding;
import quest.com.quest.models.CreatedExamsModel;

/**
 * Created by kumbh on 28-03-2017.
 */

public class StudentStatsFragment extends Fragment {
    private StudentStatusFragmentBinding dataBinding;
    private GridView gridView;
    private StudentStatusReportAdapter adapter;
    private List<CreatedExamsModel> modelList;
    public static StudentStatsFragment getInstance(Activity activity, Bundle data){
        return new StudentStatsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dataBinding = DataBindingUtil.bind(inflater.inflate(R.layout.student_status_fragment,container,false));
        gridView = dataBinding.viewStatusLayout;
        return dataBinding.getRoot();
    }


    @Override
    public void onStart() {
        super.onStart();
        modelList = new ArrayList<>();
        for(int i =0;i<25;i++){
            CreatedExamsModel model = new CreatedExamsModel("Physics","IconA","March 05,2017");
            modelList.add(model);
        }

        adapter = new StudentStatusReportAdapter(getActivity(),modelList,R.layout.rank_badge_layout);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction().replace(R.id.teacher_frame,AnswerDetailsFragment.getInstance(getActivity(),new Bundle()))
                        .commit();
            }
        });
    }
}
