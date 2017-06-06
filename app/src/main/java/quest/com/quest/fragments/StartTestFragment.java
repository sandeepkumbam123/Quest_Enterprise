package quest.com.quest.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Headers;
import quest.com.quest.NetworkUtils.ApiConstants;
import quest.com.quest.NetworkUtils.RequestConstants;
import quest.com.quest.NetworkUtils.RetrofitAPIRequests;
import quest.com.quest.NetworkUtils.RetrofitRequestHandler;
import quest.com.quest.R;
import quest.com.quest.SqliteDb.Database;
import quest.com.quest.Utils.PrefUtils;
import quest.com.quest.activities.DashBoardActivity;
import quest.com.quest.databinding.StartTestBinding;
import quest.com.quest.dialog.QuestDialog;
import quest.com.quest.models.StartExamModel;


/**
 * Created by skumbam on 03-03-2017.
 */

public class StartTestFragment extends Fragment {
    private static final String TAG = StartTestFragment.class.getSimpleName();
    private StartTestBinding dataBinding;
    private Database mDB;

    @Override
    public void onStart() {
        super.onStart();
        mDB = new Database(getActivity());
    }

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
        Map<String,Object> startTestRequestData = new HashMap<>();
        startTestRequestData.put(ApiConstants.EXAM_ID, dataBinding.inputEnterCode.getText().toString().trim()+"");
        startTest(startTestRequestData);
    }


    public void startTest(Map<String,Object> params){
        new RetrofitRequestHandler(getActivity()).startExam(RequestConstants.REQ_START_EXAM,
                params, new RetrofitAPIRequests.ResponseListener<StartExamModel>() {
                    @Override
                    public void onSuccess(int requestId, Headers headers, StartExamModel response) {
                        if(response.isIsSuccess()){

                            getActivity().getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.fl_container,new QuestionFragment())
                                    .commit();
                            mDB.deleteQuestionsListFromTable(mDB);
                            mDB.insertQuestionsintoTable(mDB,response);
                        }else {
                            QuestDialog.showOkDialog(getActivity(),
                                    response.getErrorCode()+"",response.getErrorMessage());
                        }
                    }

                    @Override
                    public void onFailure(int requestId, Throwable error) {
                        Log.d(TAG,error.toString());
                    }
                });
    }


}
