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

    private int ErrorCode;
    private String ErrorMessage;
    private List<QuestionModel> QuestionsList;

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


public int getErrorCode() {
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

public List<QuestionModel> getQuestionsList() {
        return QuestionsList;
        }

public void setQuestionsList(List<QuestionModel> QuestionsList) {
        this.QuestionsList = QuestionsList;
        }

public class QuestionModel {
    private String questionId;
    private String examId;
    private String questionNumber;
    private String question;



    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private int correctAnswer;
    private int answerMark;
    private int negativeMark;

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public String getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(String questionNumber) {
        this.questionNumber = questionNumber;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getAnswerMark() {
        return answerMark;
    }

    public void setAnswerMark(int answerMark) {
        this.answerMark = answerMark;
    }

    public int getNegativeMark() {
        return negativeMark;
    }

    public void setNegativeMark(int negativeMark) {
        this.negativeMark = negativeMark;
    }
}
}