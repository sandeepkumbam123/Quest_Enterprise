package quest.com.quest.Teacher;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import quest.com.quest.NetworkUtils.ApiConstants;
import quest.com.quest.NetworkUtils.RequestConstants;
import quest.com.quest.NetworkUtils.RetrofitAPIRequests;
import quest.com.quest.NetworkUtils.RetrofitRequestHandler;
import quest.com.quest.R;
import quest.com.quest.Utils.PrefUtils;
import quest.com.quest.databinding.FragmentCreatedExamsBinding;
import quest.com.quest.dialog.QuestDialog;
import quest.com.quest.fragments.CreateExamFragment;
import quest.com.quest.models.CreatedExamsModel;
import quest.com.quest.models.ListofExams;


/**
 * Created by skumbam on 21-03-2017.
 */

public class CreatedExamsFragment  extends Fragment implements View.OnClickListener,GridAdapter.examClick{

    public static final String TAG = CreatedExamsFragment.class.getSimpleName();

    private RecyclerView mGrid;
    //    private GridView mGrid;
    private FragmentCreatedExamsBinding fragmentCreatedExamsBinding;
    private GridAdapter adapter;
    private List<CreatedExamsModel> examsCreated;
    private ListofExams upComingExamsList;
    private Map<String,Object> examListRequestData = new HashMap<>();

    public  static CreatedExamsFragment getInstance(Activity activity ,Bundle bundle){
        CreatedExamsFragment fragment = new CreatedExamsFragment();

        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentCreatedExamsBinding = DataBindingUtil.bind(inflater.inflate( R.layout.fragment_created_exams,container,false));
        mGrid = fragmentCreatedExamsBinding.examsGridView;

        mGrid.setLayoutManager(new GridLayoutManager(getActivity(),4));
        setToolBar();
        fragmentCreatedExamsBinding.tvCreateExam.setOnClickListener(this);
        return fragmentCreatedExamsBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        examsCreated = new ArrayList<>();
        examListRequestData.put(ApiConstants.BRANCH_ID,PrefUtils.getExamIdDetailsfromSP(getActivity(),ApiConstants.BRANCH_ID));
        examListRequestData.put(ApiConstants.USER_ID, PrefUtils.getExamIdDetailsfromSP(getActivity(), ApiConstants.USER_ID));
        getExamsList(examListRequestData);

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
        int id = v.getId();
        switch (id){
            case R.id.tv_create_Exam :
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.teacher_frame, CreateExamFragment.getInstance())
                        .commit();
                break;
        }
    }

    @Override
    public void onCreatedExamClick(int position) {
        Bundle b = new Bundle();
        b.putInt(ApiConstants.EXAM_ID , upComingExamsList.getListOfScheduledExams().get(position).getExamID());
        b.putString(ApiConstants.TITLE , upComingExamsList.getListOfScheduledExams().get(position).getTitle());
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.teacher_frame,ExamEnableDisableFragment.getInstance(getActivity(),b))
                .commit();
    }

    private void createExam(View v){

        //open webView page content of the screen
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.teacher_frame, CreateExamFragment.getInstance())
                .commit();
    }
    private void setToolBar() {

        ((TeacherDashBoardActivity) getActivity())
                .setToolbarTitle(PrefUtils.getDetailsfromSP(getActivity(),ApiConstants.USER_NAME),"Created Exams");
    }

    private void getExamsList(Map<String,Object> params){
        new RetrofitRequestHandler(getActivity()).examsList(RequestConstants.REQ_EXAMS_LIST, params, new RetrofitAPIRequests.ResponseListener<ListofExams>() {
            @Override
            public void onSuccess(int requestId, Headers headers, ListofExams response) {
                if(response.isIs_success()){
                    upComingExamsList = response;
                    setUpCreatedExams(upComingExamsList);
                }else {
                    QuestDialog.showOkDialog(getActivity(),response.getErrorCode() +"",response.getErrorMessage());
                }
            }

            @Override
            public void onFailure(int requestId, Throwable error) {
                Log.d(TAG,error.toString());
            }
        });
    }

    private void setUpCreatedExams(ListofExams examAvailable) {

       /* for(ListofExams.ListOfScheduledExamsBean examBean :examAvailable.getListOfScheduledExams()) {
                CreatedExamsModel model = new CreatedExamsModel(examBean.getSubjects().get(0).getSubject(), examBean.getClassX(),
                        ""+Utilities.returnDatefromString(examBean.getExam_date()),examBean.getExam_manualID());
                examsCreated.add(model);
        }*/

        adapter = new GridAdapter(getActivity(),examAvailable,this);

        mGrid.setAdapter(adapter);
    }
}
