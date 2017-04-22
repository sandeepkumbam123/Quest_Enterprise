package quest.com.quest.Teacher;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v17.leanback.widget.HorizontalGridView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import quest.com.quest.Adapters.GridAdapter;
import quest.com.quest.R;
import quest.com.quest.databinding.FragmentCreatedExamsBinding;
import quest.com.quest.models.CreatedExamsModel;

import static android.view.View.GONE;

/**
 * Created by kumbh on 02-04-2017.
 */

public class StudentPreviousExamsFragment extends Fragment implements View.OnClickListener,GridAdapter.examClick {
    private HorizontalGridView mGrid;
    //    private GridView mGrid;
    private FragmentCreatedExamsBinding fragmentCreatedExamsBinding;
    private GridAdapter adapter;
    private List<CreatedExamsModel> examsCreated;

    public  static StudentPreviousExamsFragment getInstance(Activity activity , Bundle bundle){
        StudentPreviousExamsFragment fragment = new StudentPreviousExamsFragment();
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentCreatedExamsBinding = DataBindingUtil.bind(inflater.inflate( R.layout.fragment_created_exams,container,false));
        mGrid = fragmentCreatedExamsBinding.examsGridView;


        return fragmentCreatedExamsBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        fragmentCreatedExamsBinding.tvCreateExam.setVisibility(GONE);
        examsCreated = new ArrayList<>();
        for(int i=0;i<25;i++){
            CreatedExamsModel model = new CreatedExamsModel("Physics","10th","Apr 01,2017");
            examsCreated.add(model);
        }

        adapter = new GridAdapter(getActivity(),examsCreated,this);

        mGrid.setAdapter(adapter);
        mGrid.setNumRows(1);
      /*  mGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.teacher_frame,AnswerDetailsFragment.getInstance(getActivity(),new Bundle()));
            }
        });*/
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onCreatedExamClick(int position) {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.teacher_frame,AnswerDetailsFragment.getInstance(getActivity(),new Bundle()))
                .commit();
    }

    private void createExam(View v){
        //open webView page content of the screen
    }

}
