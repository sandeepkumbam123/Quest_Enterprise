package quest.com.quest.NetworkUtils;

import java.util.Map;

import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.http.Body;


public interface RetrofitAPIRequests {

    Call<Object> loginUser(int requestID, @Body Map<String, Object> params, RetrofitAPIRequests.ResponseListener responseListener);

    Call<Object> forgotPassword(int requestID, @Body Map<String, Object> params, RetrofitAPIRequests.ResponseListener responseListener);

    Call<Object> enableExam(int requestID, @Body Map<String, Object> params, RetrofitAPIRequests.ResponseListener responseListener);

    Call<Object> disableExam(int requestID, @Body Map<String, Object> params, RetrofitAPIRequests.ResponseListener responseListener);

    Call<Object> startExam(int requestID, @Body Map<String, Object> params, RetrofitAPIRequests.ResponseListener responseListener);

    Call<Object> submitExam(int requestID, @Body Map<String, Object> params, RetrofitAPIRequests.ResponseListener responseListener);

    Call<Object> examsList(int requestID, @Body Map<String, Object> params, RetrofitAPIRequests.ResponseListener responseListener);

    Call<Object> pastExamList(int requestID , @Body Map<String , Object> params , ResponseListener responseListener);

    Call<Object> pastExamResult(int requestID , @Body Map<String , Object> params , ResponseListener responseListener);

    Call<Object> studentResult(int requestID , @Body Map<String , Object> params , ResponseListener responseListener);

    Call<Object> getExamId(int requestID , @Body Map<String , Object> params , ResponseListener responseListener);

    interface ResponseListener<T> {
        void onSuccess(int requestId, Headers headers, T response);

        void onFailure(int requestId, Throwable error);
    }
}
