package quest.com.quest.NetworkUtils;


import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;


import java.lang.reflect.Type;

import quest.com.quest.models.ExamStatusModel;
import quest.com.quest.models.ListofExams;
import quest.com.quest.models.LoginResponseModel;

@SuppressWarnings("unchecked")
public class RetrofitResParser <T> {

    public T parseResponse(int requestId, String response) {
        switch (requestId) {
            case RequestConstants.REQ_LOGIN_USER:
                return parseLoginResponse(response);
            case RequestConstants.REQ_FORGOT_PASSWORD:
                    return parseforgotPasswordResponse(response);
            case RequestConstants.REQ_ENABLE_EXAM:
                return parseEnableExamResponse(response);
            case RequestConstants.REQ_DISABLE_EXAM:
                return parseDisableExamResponse(response);
            case RequestConstants.REQ_START_EXAM:
                return parseStartExam(response);
            case RequestConstants.REQ_SUBMIT_EXAM:
                return parseSubmitExam(response);
            case RequestConstants.REQ_EXAMS_LIST:
                return parseExamsList(response);
            default: return (T) response;

        }

    }

    private T parseExamsList(String response) {
        ListofExams examsList = ListofExams.objectFromData(response);
     return (T) examsList;
    }


    private T parseLoginResponse(String response) {
        LoginResponseModel modelData = null;
         modelData = LoginResponseModel.objectFromData(response);
        return (T) modelData;
    }


    private T parseforgotPasswordResponse(String response) {

        return null;
    }


    private T parseEnableExamResponse(String response){
        ExamStatusModel enableResponse = ExamStatusModel.objectFromData(response);
        return (T) enableResponse;
    }

    private T parseDisableExamResponse(String response){
        ExamStatusModel disableResponse = ExamStatusModel.objectFromData(response);

        return (T) disableResponse;
    }

    private T parseStartExam(String response){
        return null;
    }

    private T parseSubmitExam(String response){
        return null;
    }
}