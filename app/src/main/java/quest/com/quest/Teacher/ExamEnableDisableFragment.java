package quest.com.quest.Teacher;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Headers;
import quest.com.quest.NetworkUtils.ApiConstants;
import quest.com.quest.NetworkUtils.RequestConstants;
import quest.com.quest.NetworkUtils.RetrofitAPIRequests;
import quest.com.quest.NetworkUtils.RetrofitRequestHandler;
import quest.com.quest.R;
import quest.com.quest.Utils.PrefUtils;
import quest.com.quest.databinding.EnableDisableExamBinding;
import quest.com.quest.dialog.QuestDialog;
import quest.com.quest.models.ExamStatusModel;

/**
 * Created by kumbh on 02-04-2017.
 */

public class ExamEnableDisableFragment extends Fragment {
    private EnableDisableExamBinding dataBinding;
    private static Activity context;
    private int examId;


    public static ExamEnableDisableFragment getInstance(Activity activity, Bundle data) {
        context = activity;
        ExamEnableDisableFragment fragment = new ExamEnableDisableFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dataBinding = DataBindingUtil.bind(inflater.inflate(R.layout.enable_disable_exam, container, false));
        dataBinding.setFragment(this);
        setToolBar();

        return dataBinding.getRoot();
    }


    @Override
    public void onStart() {
        super.onStart();
        examId = getArguments().getInt(ApiConstants.EXAM_ID);

    }


    public void enableClick(View v) {
        Map<String,Object> enableExamRequestData = new HashMap<>();
        enableExamRequestData.put(ApiConstants.EXAM_ID,examId);
        enableExamRequestData.put(ApiConstants.USER_ID,PrefUtils.getExamIdDetailsfromSP(getActivity(),ApiConstants.USER_ID));
        enableExamRequestData.put(ApiConstants.BRANCH_ID,PrefUtils.getExamIdDetailsfromSP(getActivity(),ApiConstants.BRANCH_ID));
        enableExam(enableExamRequestData);
    }

    public void disableClick(View v) {
        Map<String,Object> disableExamRequestData = new HashMap<>();
        disableExamRequestData.put(ApiConstants.EXAM_ID,
                examId);
        disableExamRequestData.put(ApiConstants.USER_ID,PrefUtils.getExamIdDetailsfromSP(getActivity(),ApiConstants.USER_ID));
        disableExamRequestData.put(ApiConstants.BRANCH_ID,PrefUtils.getExamIdDetailsfromSP(getActivity(),ApiConstants.BRANCH_ID));
        disableExam(disableExamRequestData);
    }

    private void setToolBar() {

        ((TeacherDashBoardActivity) getActivity())
                .setToolbarTitle("Sandeep", "Exam Properties");
    }


    private void enableExam(Map<String, Object> params) {
        new RetrofitRequestHandler(getActivity()).enableExam(RequestConstants.REQ_ENABLE_EXAM, params,
                new RetrofitAPIRequests.ResponseListener<ExamStatusModel>() {
                    @Override
                    public void onSuccess(int requestId, Headers headers, ExamStatusModel response) {
                        if (response.isIs_success()) {
                            QuestDialog.showOkDialog(getActivity(),
                                    "Exam Status", "Exam has been Enabled successfully.");
                        } else {
                            QuestDialog.showOkDialog(getActivity(), "Exam Status",
                                    "Exam is currently not available for these operations.");

                            dataBinding.disableExam.setEnabled(true);
                            dataBinding.enableExam.setEnabled(false);
                        }
                    }

                    @Override
                    public void onFailure(int requestId, Throwable error) {

                    }
                });
    }


    private void disableExam(Map<String, Object> params) {
        new RetrofitRequestHandler(getActivity())
                .disableExam(RequestConstants.REQ_DISABLE_EXAM, params,
                        new RetrofitAPIRequests.ResponseListener<ExamStatusModel>() {
                            @Override
                            public void onSuccess(int requestId, Headers headers, ExamStatusModel response) {
                                if (response.isIs_success()) {
                                    QuestDialog.showOkDialog(getActivity(),
                                            "Exam Status", "Exam has been Disabled successfully.");

                                    dataBinding.disableExam.setEnabled(false);
                                    dataBinding.enableExam.setEnabled(true);
                                } else {
                                    QuestDialog.showOkDialog(getActivity(), "Exam Status",
                                            "Exam is currently not available for these operations.");
                                }
                            }


                            @Override
                            public void onFailure(int requestId, Throwable error) {

                            }
                        });
    }
}