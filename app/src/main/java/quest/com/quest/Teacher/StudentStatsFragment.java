package quest.com.quest.Teacher;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Headers;
import quest.com.quest.Adapters.StudentStatusReportAdapter;
import quest.com.quest.NetworkUtils.ApiConstants;
import quest.com.quest.NetworkUtils.RequestConstants;
import quest.com.quest.NetworkUtils.RetrofitAPIRequests;
import quest.com.quest.NetworkUtils.RetrofitRequestHandler;
import quest.com.quest.R;
import quest.com.quest.Utils.PrefUtils;
import quest.com.quest.activities.DashBoardActivity;
import quest.com.quest.databinding.StudentStatusFragmentBinding;
import quest.com.quest.dialog.QuestDialog;
import quest.com.quest.models.ListofExams;

/**
 * Created by kumbh on 28-03-2017.
 */

public class StudentStatsFragment extends Fragment implements  StudentStatusReportAdapter.PreviousExamReportData{
    private StudentStatusFragmentBinding dataBinding;
    private RecyclerView gridView;
    private StudentStatusReportAdapter adapter;
    private Map<String ,Object> requestData;
    private ListofExams modelData ;



    public static StudentStatsFragment getInstance(Activity activity, Bundle data){
       StudentStatsFragment fragment = new StudentStatsFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dataBinding = DataBindingUtil.bind(inflater.inflate(R.layout.student_status_fragment,container,false));
        gridView = dataBinding.viewStatusLayout;

        gridView.setLayoutManager(new GridLayoutManager(getActivity(),4));
        setToolBar();
        return dataBinding.getRoot();
    }


    @Override
    public void onStart() {
        super.onStart();
        requestData = new HashMap<>();
        requestData.put(ApiConstants.USER_ID , PrefUtils.getExamIdDetailsfromSP(getActivity(),ApiConstants.USER_ID));
        requestData.put(ApiConstants.BRANCH_ID , PrefUtils.getExamIdDetailsfromSP(getActivity() , ApiConstants.BRANCH_ID));
        getListofStudentResultExams(requestData);

    }

    private void setToolBar() {
        ((TeacherDashBoardActivity) getActivity())
                .setToolbarTitle("","Previous Exams");
    }



    private void getListofStudentResultExams(Map<String , Object> params) {
        new RetrofitRequestHandler(getActivity()).pastExamList(RequestConstants.REQ_PAST_EXAMS, params,
                new RetrofitAPIRequests.ResponseListener<ListofExams>() {
                    @Override
                    public void onSuccess(int requestId, Headers headers, final ListofExams response) {
                        if(response.isIs_success()){
                            adapter = new StudentStatusReportAdapter(getActivity(),
                                    response,R.layout.rank_badge_layout , StudentStatsFragment.this);
                            gridView.setAdapter(adapter);
                             modelData = response;




                        }else {
                            QuestDialog.showOkDialog(getActivity(),response.getErrorCode()+"",response.getErrorMessage());
                        }
                    }

                    @Override
                    public void onFailure(int requestId, Throwable error) {

                    }
                });
    }

    @Override
    public void reportOnClick(int position) {
        Bundle b = new Bundle();
        b.putString(ApiConstants.EXAM_ID,modelData.getListOfScheduledExams().get(position).getExam_manualID());
        getActivity().getSupportFragmentManager()
                .beginTransaction().replace(R.id.teacher_frame,AnswerDetailsFragment.getInstance(getActivity(),b))
                .commit();
    }
}
