package quest.com.quest.Teacher;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v17.leanback.widget.HorizontalGridView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Headers;
import quest.com.quest.Adapters.GridAdapter;
import quest.com.quest.Adapters.GridPreviousExamsAdapter;
import quest.com.quest.NetworkUtils.ApiConstants;
import quest.com.quest.NetworkUtils.Constants;
import quest.com.quest.NetworkUtils.RequestConstants;
import quest.com.quest.NetworkUtils.RetrofitAPIRequests;
import quest.com.quest.NetworkUtils.RetrofitRequestHandler;
import quest.com.quest.R;
import quest.com.quest.Utils.PrefUtils;
import quest.com.quest.databinding.FragmentCreatedExamsBinding;
import quest.com.quest.dialog.QuestDialog;
import quest.com.quest.models.CreatedExamsModel;
import quest.com.quest.models.ListofExams;
import quest.com.quest.models.PreviousExamsListModel;

import static android.view.View.GONE;

/**
 * Created by kumbh on 02-04-2017.
 */

public class StudentPreviousExamsFragment extends Fragment implements View.OnClickListener,GridPreviousExamsAdapter.PreviousExamclick {

    private static final String TAG = StudentPreviousExamsFragment.class.getSimpleName();
    private RecyclerView mGrid;
    private FragmentCreatedExamsBinding fragmentCreatedExamsBinding;
    private GridPreviousExamsAdapter adapter;
    private Map<String , Object> pastExamsRequestData ;

    public  static StudentPreviousExamsFragment getInstance(Activity activity , Bundle bundle){
        StudentPreviousExamsFragment fragment = new StudentPreviousExamsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentCreatedExamsBinding = DataBindingUtil.bind(inflater.inflate( R.layout.fragment_created_exams,container,false));
        mGrid = fragmentCreatedExamsBinding.examsGridView;
        mGrid.setLayoutManager(new GridLayoutManager(getActivity(),4));
        setToolBar();

        return fragmentCreatedExamsBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        fragmentCreatedExamsBinding.tvCreateExam.setVisibility(GONE);

        int currentUserId = getArguments().getInt(ApiConstants.CURRENT_USR_ID) == 0 ? 0 : getArguments().getInt(ApiConstants.CURRENT_USR_ID);
       String examID = getArguments().getString(ApiConstants.EXAM_ID) == null ? "" : getArguments().getString(ApiConstants.EXAM_ID);
        int userID = getArguments().getInt(ApiConstants.STUDENT_ID) == 0 ? 0 : getArguments().getInt(ApiConstants.STUDENT_ID);

        pastExamsRequestData = new HashMap<>();
        pastExamsRequestData.put(ApiConstants.STUDENT_ID ,userID);
        pastExamsRequestData.put(ApiConstants.EXAM_ID,examID);
        pastExamsRequestData.put(ApiConstants.CURRENT_USR_ID,currentUserId);
        getPreviousExamsDetails(pastExamsRequestData);
    }

    @Override
    public void onClick(View v) {

    }


    private void setToolBar() {

        ((TeacherDashBoardActivity) getActivity())
                .setToolbarTitle("Sandeep","Student Data");
    }


    private void getPreviousExamsDetails(Map<String , Object> params){
        new RetrofitRequestHandler(getActivity()).pastExamResult(RequestConstants.REQ_PAST_EXAMS_RESULT, params, new RetrofitAPIRequests.ResponseListener<List<PreviousExamsListModel>>() {
            @Override
            public void onSuccess(int requestId, Headers headers, List<PreviousExamsListModel> response) {
                if(response.get(0).isIsSuccess()){
                    if(response.size()==1){
                        Bundle b = new Bundle();
                        b.putParcelable(PreviousAnswersFragment.ANSWERS_MODEL ,response.get(0));
                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.teacher_frame,PreviousAnswersFragment.getInstance(getActivity(),b))
                                .commit();
                    }else {
                        updateAdapter(response);
                    }

                }else{
                    QuestDialog.showOkDialog(getActivity(),String.valueOf(response.get(0).getErrorCode()),response.get(0).getErrorMessage());
                }
            }

            @Override
            public void onFailure(int requestId, Throwable error) {
                Log.d(TAG, error.toString());
            }
        });
    }

    private void updateAdapter(List<PreviousExamsListModel> response) {

        adapter = new GridPreviousExamsAdapter(getActivity(),response,this);
        mGrid.setAdapter(adapter);
    }

    @Override
    public void examData(PreviousExamsListModel bean) {


        Bundle b = new Bundle();
        b.putParcelable(PreviousAnswersFragment.ANSWERS_MODEL ,bean);
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.teacher_frame,PreviousAnswersFragment.getInstance(getActivity(),b))
                .commit();

    }
}
