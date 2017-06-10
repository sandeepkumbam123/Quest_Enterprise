package quest.com.quest.models;

/**
 * Created by kumbh on 10-06-2017.
 */
/*[
  {
    "ListOfScheduledExams": null,
    "is_success": false,
    "ErrorCode": "404",
    "ErrorMessage": "Only Student/Teacher can access"
  }
]*/

public class PastExamsModel {

    private ListofExams.ListOfScheduledExamsBean scheduledExamsBean;
    private boolean mSuccess ;
    private String mErrorCode;
    private String mErrorMessage;

    public PastExamsModel(ListofExams.ListOfScheduledExamsBean scheduledExamsBean, boolean mSuccess, String mErrorCode, String mErrorMessage) {
        this.scheduledExamsBean = scheduledExamsBean;
        this.mSuccess = mSuccess;
        this.mErrorCode = mErrorCode;
        this.mErrorMessage = mErrorMessage;
    }


    public ListofExams.ListOfScheduledExamsBean getScheduledExamsBean() {
        return scheduledExamsBean;
    }

    public void setScheduledExamsBean(ListofExams.ListOfScheduledExamsBean scheduledExamsBean) {
        this.scheduledExamsBean = scheduledExamsBean;
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
