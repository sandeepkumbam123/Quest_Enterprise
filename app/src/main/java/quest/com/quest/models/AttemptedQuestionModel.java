package quest.com.quest.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by kumbh on 27-05-2017.
 */

public class AttemptedQuestionModel implements Parcelable {
    private String questionNumber;
    private String examId;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private int attemptedAnswer;
    private int correctAnswer;
    private int timeTakentoAttempt;

    private String examTitle;
    private int examDuration;
    private int totalMarks;
    private int negativeMarks;
    private String criticality;
    private int answerMark;

    public int getAnswerMark() {
        return answerMark;
    }

    public void setAnswerMark(int answerMark) {
        this.answerMark = answerMark;
    }

    protected AttemptedQuestionModel(Parcel in) {
        questionNumber = in.readString();
        examId = in.readString();
        optionA = in.readString();
        optionB = in.readString();
        optionC = in.readString();
        optionD = in.readString();
        attemptedAnswer = in.readInt();
        correctAnswer = in.readInt();
        timeTakentoAttempt = in.readInt();
        examTitle = in.readString();
        examDuration = in.readInt();
        totalMarks = in.readInt();
        negativeMarks = in.readInt();
        criticality = in.readString();
    }

    public static final Creator<AttemptedQuestionModel> CREATOR = new Creator<AttemptedQuestionModel>() {
        @Override
        public AttemptedQuestionModel createFromParcel(Parcel in) {
            return new AttemptedQuestionModel(in);
        }

        @Override
        public AttemptedQuestionModel[] newArray(int size) {
            return new AttemptedQuestionModel[size];
        }
    };


    public AttemptedQuestionModel(int timeTakentoAttempt , int attemptedAnswer ){
        this.timeTakentoAttempt = timeTakentoAttempt;
        this.attemptedAnswer = attemptedAnswer;

    }

    public String getExamTitle() {
        return examTitle;
    }

    public void setExamTitle(String examTitle) {
        this.examTitle = examTitle;
    }

    public int getExamDuration() {
        return examDuration;
    }

    public void setExamDuration(int examDuration) {
        this.examDuration = examDuration;
    }

    public int getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
    }

    public int getNegativeMarks() {
        return negativeMarks;
    }

    public void setNegativeMarks(int negativeMarks) {
        this.negativeMarks = negativeMarks;
    }

    public String getCriticality() {
        return criticality;
    }

    public AttemptedQuestionModel(String questionNumber, String examId, String optionA, String optionB,
                                  String optionC, String optionD, int attemptedAnswer, int correctAnswer,
                                  int timeTakentoAttempt,
                                  String examTitle, int examDuration, int totalMarks,
                                  int negativeMarks, String criticality , int answerMark) {
        this.questionNumber = questionNumber;
        this.examId = examId;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.attemptedAnswer = attemptedAnswer;
        this.correctAnswer = correctAnswer;
        this.timeTakentoAttempt = timeTakentoAttempt;
        this.examTitle = examTitle;
        this.examDuration = examDuration;
        this.totalMarks = totalMarks;
        this.negativeMarks = negativeMarks;
        this.criticality = criticality;
        this.answerMark = answerMark;
    }

    public void setCriticality(String criticality) {
        this.criticality = criticality;

    }

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(questionNumber);
        dest.writeString(examId);
        dest.writeString(optionA);
        dest.writeString(optionB);
        dest.writeString(optionC);
        dest.writeString(optionD);
        dest.writeInt(attemptedAnswer);
        dest.writeInt(correctAnswer);
        dest.writeInt(timeTakentoAttempt);
        dest.writeString(examTitle);
        dest.writeInt(examDuration);
        dest.writeInt(totalMarks);
        dest.writeInt(negativeMarks);
        dest.writeString(criticality);
    }
}
