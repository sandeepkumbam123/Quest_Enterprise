package quest.com.quest.models;

/**
 * Created by kumbh on 27-05-2017.
 */

public class AttemptedQuestionModel {
    private String questionNumber;
    private String examId;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private int attemptedAnswer;

    public AttemptedQuestionModel(String questionNumber, String examId,
                                  String optionA, String optionB, String optionC,
                                  String optionD, int attemptedAnswer, int correctAnswer,
                                  int timeTakentoAttempt) {
        this.questionNumber = questionNumber;
        this.examId = examId;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.attemptedAnswer = attemptedAnswer;
        this.correctAnswer = correctAnswer;
        this.timeTakentoAttempt = timeTakentoAttempt;
    }

    public String getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(String questionNumber) {
        this.questionNumber = questionNumber;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
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

    public int getAttemptedAnswer() {
        return attemptedAnswer;
    }

    public void setAttemptedAnswer(int attemptedAnswer) {
        this.attemptedAnswer = attemptedAnswer;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getTimeTakentoAttempt() {
        return timeTakentoAttempt;
    }

    public void setTimeTakentoAttempt(int timeTakentoAttempt) {
        this.timeTakentoAttempt = timeTakentoAttempt;
    }

    private int correctAnswer;
    private int timeTakentoAttempt;


}
