package quest.com.quest.Teacher;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Headers;
import quest.com.quest.Adapters.GridPreviousExamsAdapter;
import quest.com.quest.NetworkUtils.ApiConstants;
import quest.com.quest.NetworkUtils.RequestConstants;
import quest.com.quest.NetworkUtils.RetrofitAPIRequests;
import quest.com.quest.NetworkUtils.RetrofitRequestHandler;
import quest.com.quest.R;
import quest.com.quest.Utils.PrefUtils;
import quest.com.quest.databinding.SearchStudentDataBinding;
import quest.com.quest.dialog.QuestDialog;
import quest.com.quest.models.PreviousExamsListModel;

/**
 * Created by kumbh on 26-03-2017.
 */

public class SearchStudentFragment extends Fragment{

    private static final String TAG = SearchStudentFragment.class.getSimpleName();

    SearchStudentDataBinding dataBinding;
    private  boolean isOnlyOneExam = false;
    private Map<String , Object> requestData;
    public static SearchStudentFragment getInstance(Activity activity , Bundle bundle){
        SearchStudentFragment fragment = new SearchStudentFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dataBinding= DataBindingUtil.bind(inflater.inflate(R.layout.search_student_data,container,false));
        dataBinding.setFragment(this);
        setToolBar();
        return dataBinding.getRoot();
    }


    public void searchStudentData(View v){
     Bundle b = new Bundle();
        int studentId = Integer.parseInt(dataBinding.inputStudentId.getText().toString().trim());
        String examId = dataBinding.inputExamId.getText().toString().trim();
        int teacherId = PrefUtils.getExamIdDetailsfromSP(getActivity(), ApiConstants.USER_ID);

        b.putInt(ApiConstants.STUDENT_ID , studentId);
        b.putString(ApiConstants.EXAM_ID , examId);
        b.putInt(ApiConstants.CURRENT_USR_ID , teacherId);

        /*if(isOnlyOneExam){
              getActivity().getSupportFragmentManager().beginTransaction()
                      .replace(R.id.teacher_frame,AnswerDetailsFragment.getInstance(getActivity(),new Bundle()))
                      .commit();
        }
        else {*/
           getActivity().getSupportFragmentManager().beginTransaction()
                   .replace(R.id.teacher_frame,StudentPreviousExamsFragment.getInstance(getActivity(),b))
                   .commit();
//        }
    }
    private void setToolBar() {

        ((TeacherDashBoardActivity) getActivity())
                .setToolbarTitle("Sandeep","Search Student");
    }

    /*private void getPreviousExamsDetails(Map<String , Object> params){
        new RetrofitRequestHandler(getActivity()).pastExamList(RequestConstants.REQ_PAST_EXAMS, params, new RetrofitAPIRequests.ResponseListener<List<PreviousExamsListModel>>() {
            @Override
            public void onSuccess(int requestId, Headers headers, List<PreviousExamsListModel> response) {
                if(response.get(0).isIsSuccess()){
                    updateAdapter(response);

                }else{
                    QuestDialog.showOkDialog(getActivity(),String.valueOf(response.get(0).getErrorCode()),response.get(0).getErrorMessage());
                }
            }

            @Override
            public void onFailure(int requestId, Throwable error) {
                Log.d(TAG, error.toString());
            }
        });
    }*/


   /* @Override
    public void examData(PreviousExamsListModel bean) {


        Bundle b = new Bundle();
        b.putParcelable(PreviousAnswersFragment.ANSWERS_MODEL ,bean);
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.teacher_frame,PreviousAnswersFragment.getInstance(getActivity(),new Bundle()))
                .commit();

    }*/


   /* private void getStudentDat(Map<String , Object> params){
        new RetrofitRequestHandler(getActivity()).studentResult(RequestConstants.REQ_STUDENT_PAST_EXAMS, params, new RetrofitAPIRequests.ResponseListener() {
            @Override
            public void onSuccess(int requestId, Headers headers, Object response) {

            }

            @Override
            public void onFailure(int requestId, Throwable error) {

            }
        });
    }*/
}
