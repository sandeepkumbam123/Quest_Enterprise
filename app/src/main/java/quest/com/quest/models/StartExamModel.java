package quest.com.quest.models;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by kumbh on 26-05-2017.
 */

public class StartExamModel {

    /**
     * QuestionsList : []
     * Duration : 3600
     * TotalMarks : 20
     * ExamTitle :
     * NegativeMarks : 0
     * Critical_level : Easy
     * isSuccess : true
     * ErrorCode : false
     * ErrorMessage : false
     */

    private int Duration;
    private int TotalMarks;
    private String ExamTitle;
    private int NegativeMarks;
    private String Critical_level;
    private boolean isSuccess;
    private boolean ErrorCode;
    private boolean ErrorMessage;
    private List<?> QuestionsList;

    public static StartExamModel objectFromData(String str) {

        return new Gson().fromJson(str, StartExamModel.class);
    }

    public int getDuration() {
        return Duration;
    }

    public void setDuration(int Duration) {
        this.Duration = Duration;
    }

    public int getTotalMarks() {
        return TotalMarks;
    }

    public void setTotalMarks(int TotalMarks) {
        this.TotalMarks = TotalMarks;
    }

    public String getExamTitle() {
        return ExamTitle;
    }

    public void setExamTitle(String ExamTitle) {
        this.ExamTitle = ExamTitle;
    }

    public int getNegativeMarks() {
        return NegativeMarks;
    }

    public void setNegativeMarks(int NegativeMarks) {
        this.NegativeMarks = NegativeMarks;
    }

    public String getCritical_level() {
        return Critical_level;
    }

    public void setCritical_level(String Critical_level) {
        this.Critical_level = Critical_level;
    }

    public boolean isIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public boolean isErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(boolean ErrorCode) {
        this.ErrorCode = ErrorCode;
    }

    public boolean isErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(boolean ErrorMessage) {
        this.ErrorMessage = ErrorMessage;
    }

    public List<?> getQuestionsList() {
        return QuestionsList;
    }

    public void setQuestionsList(List<?> QuestionsList) {
        this.QuestionsList = QuestionsList;
    }
}
