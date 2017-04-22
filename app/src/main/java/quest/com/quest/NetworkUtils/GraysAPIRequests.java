package quest.com.quest.NetworkUtils;


import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;


public interface GraysAPIRequests {

    /*Retrofit Post annotation with our URL*/

    @POST(Constants.LOGIN_URL)
    Call<Object> loginUser(@Body Map<String, String> jsonObj);


    @POST(Constants.FORGOT_PASSWORD)
    @Headers("Content-Type:application/json")
    Call<Object> forgotPassword(@Body Map<String, String> jsonObj/*, @Header("Cookie") String cookie*/);
    //Call<Object> checkLogin(@Body Map<String,String> jsonObj);






}
