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
    public Call<Object> loginUser(int requestID, @Body Map<String, String> params, final ResponseListener responseListener) {
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
    public Call<Object> forgotPassword(int requestID, @Body Map<String, String> params, final ResponseListener responseListener) {
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

   /* @Override
    public Call<Object> checkLogin(int requestID, @Body Map<String, String> params, final RetrofitAPIRequests.ResponseListener responseListener) {

        RetrofitRequestUtil.OnRetrofitReqListener reqListener = new RetrofitRequestUtil.OnRetrofitReqListener() {

            @Override
            public void onResponse(int requestId, Headers responseHeaders, String response) {
                responseListener.onSuccess(requestId, responseHeaders,response);
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
    public Call<Object> getCategories(int requestID, @Body Map<String, String> params, final RetrofitAPIRequests.ResponseListener responseListener) {
        RetrofitRequestUtil.OnRetrofitReqListener reqListener = new RetrofitRequestUtil.OnRetrofitReqListener() {

            @Override
            public void onResponse(int requestId, Headers responseHeaders, String response) {
                responseListener.onSuccess(requestId,responseHeaders, new RetrofitResParser().parseResponse(requestId, response));
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
    public Call<Object> registerUser(int requestID, @Body Map<String, String> params, final RetrofitAPIRequests.ResponseListener responseListener) {

        RetrofitRequestUtil.OnRetrofitReqListener reqListener = new RetrofitRequestUtil.OnRetrofitReqListener() {

            @Override
            public void onResponse(int requestId, Headers responseHeaders, String response) {
                responseListener.onSuccess(requestId, responseHeaders,response);
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
    public Call<Object> getLoginStatus(int requestID, @Body Map<String, String> params, HashMap<String,String> requestHeaders, final ResponseListener responseListener) {
        RetrofitRequestUtil.OnRetrofitReqListener reqListener = new RetrofitRequestUtil.OnRetrofitReqListener() {

            @Override
            public void onResponse(int requestId, Headers responseHeaders, String response) {
                responseListener.onSuccess(requestId, responseHeaders,response);
            }

            @Override
            public void onErrorResponse(int requestId, Throwable error) {
                responseListener.onFailure(requestId, error);

            }
        };
        new RetrofitRequestUtil(mContext).retrofitEnqueueCall(requestID, "url", "POST",requestHeaders, params, reqListener);
        return null;
    }
*/
}
