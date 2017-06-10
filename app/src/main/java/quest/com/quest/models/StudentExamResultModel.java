package quest.com.quest.models;

/**
 * Created by kumbh on 10-06-2017.
 */
/*[
    {
        "is_success": false,
        "ErrorCode": "403",
        "ErrorMessage": "No Access for the current user"
    }
]*/

public class StudentExamResultModel {

    private boolean mSuccess;
    private String mErrorCode;
    private String mErrorMessage;

    public StudentExamResultModel(boolean mSuccess, String mErrorCode, String mErrorMessage) {
        this.mSuccess = mSuccess;
        this.mErrorCode = mErrorCode;
        this.mErrorMessage = mErrorMessage;
    }

    public boolean ismSuccess() {
        return mSuccess;
    }

    public void setmSuccess(boolean mSuccess) {
        this.mSuccess = mSuccess;
    }

    public String getmErrorCode() {
        return mErrorCode;
    }

    public void setmErrorCode(String mErrorCode) {
        this.mErrorCode = mErrorCode;
    }

    public String getmErrorMessage() {
        return mErrorMessage;
    }

    public void setmErrorMessage(String mErrorMessage) {
        this.mErrorMessage = mErrorMessage;
    }
}
