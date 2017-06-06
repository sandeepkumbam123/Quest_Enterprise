package quest.com.quest.NetworkUtils;


import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;


public interface GraysAPIRequests {

    /*Retrofit Post annotation with our URL*/

    @POST(Constants.LOGIN_URL)
    Call<Object> loginUser(@Body Map<String, Object> jsonObj);


    @POST(Constants.FORGOT_PASSWORD)
    @Headers("Content-Type:application/json")
    Call<Object> forgotPassword(@Body Map<String, Object> jsonObj/*, @Header("Cookie") String cookie*/);
    //Call<Object> checkLogin(@Body Map<String,String> jsonObj);


    @POST(Constants.ENABLE_EXAM)
    Call<Object> enableExam(@Body Map<String,Object> jsonObj);

    @POST(Constants.DISABLE_EXAM)
    Call<Object> disableExam(@Body Map<String,Object> jsonObj);

    @POST(Constants.START_EXAM)
    Call<Object> startExam(@Body Map<String,Object> jsonObj);

    @POST(Constants.SUBMIT_EXAM)
    Call<Object> submitExam(@Body Map<String,Object> jsonObj);

    @POST(Constants.EXAMS)
    Call<Object> examsList(@Body Map<String,Object> jsonObj);


}
