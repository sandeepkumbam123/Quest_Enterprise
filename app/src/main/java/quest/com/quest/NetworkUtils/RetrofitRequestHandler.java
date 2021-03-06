package quest.com.quest.NetworkUtils;

import android.content.Context;

import java.util.Map;

import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.http.Body;


@SuppressWarnings("unchecked")
public class RetrofitRequestHandler implements RetrofitAPIRequests {

    private Context mContext;
//Constructor
    public RetrofitRequestHandler(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public Call<Object> loginUser(int requestID, @Body Map<String, Object> params, final ResponseListener responseListener) {
        RetrofitRequestUtil.OnRetrofitReqListener reqListener = new RetrofitRequestUtil.OnRetrofitReqListener() {

            @Override
            public void onResponse(int requestId, Headers responseHeaders, String response) {
                responseListener.onSuccess(requestId, responseHeaders,new RetrofitResParser().parseResponse(requestId,response));
            }

            @Override
            public void onErrorResponse(int requestId, Throwable error) {
                responseListener.onFailure(requestId, error);

            }
        };
        new RetrofitRequestUtil(mContext).retrofitEnqueueCall(requestID, "url", "POST",null, params, reqListener);
        return null;
    }

    @Override
    public Call<Object> forgotPassword(int requestID, @Body Map<String, Object> params, final ResponseListener responseListener) {
        RetrofitRequestUtil.OnRetrofitReqListener reqListener = new RetrofitRequestUtil.OnRetrofitReqListener() {

            @Override
            public void onResponse(int requestId, Headers responseHeaders, String response) {
                responseListener.onSuccess(requestId, responseHeaders,new RetrofitResParser().parseResponse(requestId,response));
            }

            @Override
            public void onErrorResponse(int requestId, Throwable error) {
                responseListener.onFailure(requestId, error);

            }
        };
        new RetrofitRequestUtil(mContext).retrofitEnqueueCall(requestID, "url", "POST",null, params, reqListener);
        return null;
    }

    @Override
    public Call<Object> enableExam(int requestID, @Body Map<String, Object> params,final ResponseListener responseListener) {
        RetrofitRequestUtil.OnRetrofitReqListener reqListener = new RetrofitRequestUtil.OnRetrofitReqListener() {

            @Override
            public void onResponse(int requestId, Headers responseHeaders, String response) {
                responseListener.onSuccess(requestId, responseHeaders,new RetrofitResParser().parseResponse(requestId,response));
            }

            @Override
            public void onErrorResponse(int requestId, Throwable error) {
                responseListener.onFailure(requestId, error);

            }
        };
        new RetrofitRequestUtil(mContext).retrofitEnqueueCall(requestID, "url", "POST",null, params, reqListener);
        return null;
    }

    @Override
    public Call<Object> disableExam(int requestID, @Body Map<String, Object> params, final ResponseListener responseListener) {
        RetrofitRequestUtil.OnRetrofitReqListener reqListener = new RetrofitRequestUtil.OnRetrofitReqListener() {

            @Override
            public void onResponse(int requestId, Headers responseHeaders, String response) {
                responseListener.onSuccess(requestId, responseHeaders,new RetrofitResParser().parseResponse(requestId,response));
            }

            @Override
            public void onErrorResponse(int requestId, Throwable error) {
                responseListener.onFailure(requestId, error);

            }
        };
        new RetrofitRequestUtil(mContext).retrofitEnqueueCall(requestID, "url", "POST",null, params, reqListener);
        return null;
    }

    @Override
    public Call<Object> startExam(int requestID, @Body Map<String, Object> params, final ResponseListener responseListener) {
        RetrofitRequestUtil.OnRetrofitReqListener reqListener = new RetrofitRequestUtil.OnRetrofitReqListener() {

            @Override
            public void onResponse(int requestId, Headers responseHeaders, String response) {
                responseListener.onSuccess(requestId, responseHeaders,new RetrofitResParser().parseResponse(requestId,response));
            }

            @Override
            public void onErrorResponse(int requestId, Throwable error) {
                responseListener.onFailure(requestId, error);

            }
        };
        new RetrofitRequestUtil(mContext).retrofitEnqueueCall(requestID, "url", "POST",null, params, reqListener);
        return null;
    }

    @Override
    public Call<Object> submitExam(int requestID, @Body Map<String, Object> params, final ResponseListener responseListener) {
        RetrofitRequestUtil.OnRetrofitReqListener reqListener = new RetrofitRequestUtil.OnRetrofitReqListener() {

            @Override
            public void onResponse(int requestId, Headers responseHeaders, String response) {
                responseListener.onSuccess(requestId, responseHeaders,new RetrofitResParser().parseResponse(requestId,response));
            }

            @Override
            public void onErrorResponse(int requestId, Throwable error) {
                responseListener.onFailure(requestId, error);

            }
        };
        new RetrofitRequestUtil(mContext).retrofitEnqueueCall(requestID, "url", "POST",null, params, reqListener);
        return null;
    }

    @Override
    public Call<Object> examsList(int requestID, @Body Map<String, Object> params, final ResponseListener responseListener) {
        RetrofitRequestUtil.OnRetrofitReqListener reqListener = new RetrofitRequestUtil.OnRetrofitReqListener() {

            @Override
            public void onResponse(int requestId, Headers responseHeaders, String response) {
                responseListener.onSuccess(requestId, responseHeaders,new RetrofitResParser().parseResponse(requestId,response));
            }

            @Override
            public void onErrorResponse(int requestId, Throwable error) {
                responseListener.onFailure(requestId, error);

            }
        };
        new RetrofitRequestUtil(mContext).retrofitEnqueueCall(requestID, "url", "POST",null, params, reqListener);
        return null;
    }

    @Override
    public Call<Object> pastExamList(int requestID, @Body Map<String, Object> params, final ResponseListener responseListener) {
        RetrofitRequestUtil.OnRetrofitReqListener reqListener = new RetrofitRequestUtil.OnRetrofitReqListener() {

            @Override
            public void onResponse(int requestId, Headers responseHeaders, String response) {
                responseListener.onSuccess(requestId, responseHeaders,new RetrofitResParser().parseResponse(requestId,response));
            }

            @Override
            public void onErrorResponse(int requestId, Throwable error) {
                responseListener.onFailure(requestId, error);

            }
        };
        new RetrofitRequestUtil(mContext).retrofitEnqueueCall(requestID, "url", "POST",null, params, reqListener);
        return null;
    }

    @Override
    public Call<Object> pastExamResult(int requestID, @Body Map<String, Object> params, final ResponseListener responseListener) {
        RetrofitRequestUtil.OnRetrofitReqListener reqListener = new RetrofitRequestUtil.OnRetrofitReqListener() {

            @Override
            public void onResponse(int requestId, Headers responseHeaders, String response) {
                responseListener.onSuccess(requestId, responseHeaders,new RetrofitResParser().parseResponse(requestId,response));
            }

            @Override
            public void onErrorResponse(int requestId, Throwable error) {
                responseListener.onFailure(requestId, error);

            }
        };
        new RetrofitRequestUtil(mContext).retrofitEnqueueCall(requestID, "url", "POST",null, params, reqListener);
        return null;
    }

    @Override
    public Call<Object> studentResult(int requestID, @Body Map<String, Object> params, final ResponseListener responseListener) {
        RetrofitRequestUtil.OnRetrofitReqListener reqListener = new RetrofitRequestUtil.OnRetrofitReqListener() {

            @Override
            public void onResponse(int requestId, Headers responseHeaders, String response) {
                responseListener.onSuccess(requestId, responseHeaders,new RetrofitResParser().parseResponse(requestId,response));
            }

            @Override
            public void onErrorResponse(int requestId, Throwable error) {
                responseListener.onFailure(requestId, error);

            }
        };
        new RetrofitRequestUtil(mContext).retrofitEnqueueCall(requestID, "url", "POST",null, params, reqListener);
        return null;
    }

    @Override
    public Call<Object> getExamId(int requestID, @Body Map<String, Object> params, final ResponseListener responseListener) {
        RetrofitRequestUtil.OnRetrofitReqListener reqListener = new RetrofitRequestUtil.OnRetrofitReqListener() {

            @Override
            public void onResponse(int requestId, Headers responseHeaders, String response) {
                responseListener.onSuccess(requestId, responseHeaders,new RetrofitResParser().parseResponse(requestId,response));
            }

            @Override
            public void onErrorResponse(int requestId, Throwable error) {
                responseListener.onFailure(requestId, error);

            }
        };
        new RetrofitRequestUtil(mContext).retrofitEnqueueCall(requestID, "url", "POST",null, params, reqListener);
        return null;
    }


}
