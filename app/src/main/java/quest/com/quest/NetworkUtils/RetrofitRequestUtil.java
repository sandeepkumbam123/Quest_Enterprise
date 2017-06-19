package quest.com.quest.NetworkUtils;

import android.content.Context;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Headers;
import okhttp3.HttpUrl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;


public class RetrofitRequestUtil {
    private static final String TAG = RetrofitRequestUtil.class.getSimpleName();

    Context context;

    public interface OnRetrofitReqListener {
        void onResponse(int requestId, okhttp3.Headers responseHeaders, String response);

        void onErrorResponse(int requestId, Throwable t);

    }

    public RetrofitRequestUtil(Context context) {
        this.context = context;
    }


    public void retrofitEnqueueCall(final int requestID, String url, String method, HashMap<String,String> requestHeaders, @Body Map<String, Object> params, final OnRetrofitReqListener mReqListener) {
        Call<Object> call = getCallObject(requestID, params,requestHeaders);
        HttpUrl postUrl = call.request().url();
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, retrofit2.Response<Object> response) {
                //int statusCode = response.code();
                try {
                    String res = new Gson().toJson(response.body());
                    //JSONObject jsonRes = new JSONObject(res);
                    Headers headers = response.headers();
                    mReqListener.onResponse(requestID, headers, res);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                // Log error here since request failed
                mReqListener.onErrorResponse(requestID, t);
            }
        });


    }

    private Call<Object> getCallObject(int requestID, @Body Map<String, Object> params, HashMap<String,String> requestHeaders) {

        GraysAPIRequests apiService = RetrofitClient.getClient().create(GraysAPIRequests.class);
        Call<Object> call = null;
        switch (requestID) {
            case RequestConstants.REQ_LOGIN_USER:
                call = apiService.loginUser(params);
                break;
            case RequestConstants.REQ_FORGOT_PASSWORD:
                call = apiService.forgotPassword(params);
                break;

            case RequestConstants.REQ_ENABLE_EXAM:
                call = apiService.enableExam(params);
                break;
            case RequestConstants.REQ_DISABLE_EXAM:
                call = apiService.disableExam(params);
                break;
            case RequestConstants.REQ_START_EXAM:
                call = apiService.startExam(params);
                break;
            case RequestConstants.REQ_SUBMIT_EXAM:
                call = apiService.submitExam(params);
                break;
            case RequestConstants.REQ_EXAMS_LIST:
                call = apiService.examsList(params);
                break;
            case RequestConstants.REQ_PAST_EXAMS:
                call = apiService.pastExamsList(params);
                break;
            case RequestConstants.REQ_PAST_EXAMS_RESULT:
                call = apiService.pastExamsResult(params);
                break;
            case RequestConstants.REQ_STUDENT_PAST_EXAMS:
                call = apiService.studentExamResult(params);
                break;

            case RequestConstants.REQ_GET_EXAM_ID:
                call = apiService.getExamID(params);
                 break;

        }
        return call;
    }

}
