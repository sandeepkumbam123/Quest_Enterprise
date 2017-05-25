package quest.com.quest.models;

import com.google.gson.Gson;

/**
 * Created by kumbh on 25-05-2017.
 */

public class ExamStatusModel {


    /**
     * is_success : true
     * ErrorCode : null
     * ErrorMessage : null
     */

    private boolean is_success;
    private int ErrorCode;
    private String ErrorMessage;

    public static ExamStatusModel objectFromData(String str) {

        return new Gson().fromJson(str, ExamStatusModel.class);
    }

    public boolean isIs_success() {
        return is_success;
    }

    public void setIs_success(boolean is_success) {
        this.is_success = is_success;
    }

    public Object getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(int ErrorCode) {
        this.ErrorCode = ErrorCode;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(String ErrorMessage) {
        this.ErrorMessage = ErrorMessage;
    }
}
