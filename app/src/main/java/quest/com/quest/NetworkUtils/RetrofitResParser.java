package quest.com.quest.NetworkUtils;


import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;


import java.lang.reflect.Type;

import quest.com.quest.models.LoginResponseModel;

@SuppressWarnings("unchecked")
public class RetrofitResParser <T> {

    public T parseResponse(int requestId, String response) {
        switch (requestId) {
            case RequestConstants.REQ_LOGIN_USER:
                return parseLoginResponse(response);
            case RequestConstants.REQ_FORGOT_PASSWORD:
                    return parseforgotPasswordResponse(response);
            default: return (T) response;

        }

    }


    private T parseLoginResponse(String response) {
        LoginResponseModel modelData = null;
         modelData = LoginResponseModel.objectFromData(response);
        return (T) modelData;
    }

    /*private T parseNavigationCategories(String response) {
       *//* JSONObject json ;
        List<CategoriesGoupModel> allUserses = null;
        try {
            json = new JSONObject(response);
            JSONArray jsonarray = json.getJSONArray("ParentCategories");
            Type listType = new TypeToken<ArrayList<CategoriesGoupModel>>() {
            }.getType();
            allUserses =
                    new GsonBuilder().create().fromJson(jsonarray.toString(), listType);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return (T) allUserses;*//*
        return null;
    }*/

    private T parseforgotPasswordResponse(String response) {
        /*JSONObject json;
        List<Categories> allUserses = null;
        try {
            json = new JSONObject(response);
            JSONArray jsonarray = json.getJSONArray("ParentCategories");
            Type listType = new TypeToken<ArrayList<Categories>>() {
            }.getType();
            allUserses =
                    new GsonBuilder().create().fromJson(jsonarray.toString(), listType);
        } catch (JSONException e) {
            e.printStackTrace();
        }
*/
        return null;
    }
}