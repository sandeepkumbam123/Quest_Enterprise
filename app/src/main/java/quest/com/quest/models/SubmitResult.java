package quest.com.quest.models;

/**
 * Created by kumbh on 10-06-2017.
 */
/*[
  {
    "is_success": true,
    "ErrorCode": "200",
    "ErrorMessage": "The result saved successfully"
  }
]*/
public class SubmitResult {

    private boolean mSuccess;
    private String mErrorCode;
    private String mErrorMessage;

    public SubmitResult(boolean mSuccess, String mErrorCode, String mErrorMessage) {
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
