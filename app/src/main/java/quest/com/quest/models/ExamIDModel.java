package quest.com.quest.models;

/**
 * Created by skumbam on 6/18/17.
 */

public class ExamIDModel {

    private boolean isSuccess;
    private String errorCode;
    private String errorMessage;
    private int examId;

    public ExamIDModel(boolean isSuccess, String errorCode, String errorMessage, int examId) {
        this.isSuccess = isSuccess;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.examId = examId;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }
}
