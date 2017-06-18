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

    private String hasImage;
    private String option1Image;
    private String option2Image;
    private String option3Image;

    public AttemptedQuestionModel(String questionNumber, String examId, String optionA, String optionB, String optionC, String optionD, int attemptedAnswer,
                                 int correctAnswer, int timeTakentoAttempt, String hasImage, String option1Image, String option2Image, String option3Image,
                                  String option4Image, String examTitle, int examDuration, int totalMarks, int negativeMarks, String criticality, int answerMark) {
        this.questionNumber = questionNumber;
        this.examId = examId;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.attemptedAnswer = attemptedAnswer;
        this.correctAnswer = correctAnswer;
        this.timeTakentoAttempt = timeTakentoAttempt;
        this.hasImage = hasImage;
        this.option1Image = option1Image;
        this.option2Image = option2Image;
        this.option3Image = option3Image;
        this.option4Image = option4Image;
        this.examTitle = examTitle;
        this.examDuration = examDuration;
        this.totalMarks = totalMarks;
        this.negativeMarks = negativeMarks;
        this.criticality = criticality;
        this.answerMark = answerMark;
    }

    public String getHasImage() {
        return hasImage;
    }

    public void setHasImage(String hasImage) {
        this.hasImage = hasImage;
    }

    public String getOption1Image() {
        return option1Image;
    }

    public void setOption1Image(String option1Image) {
        this.option1Image = option1Image;
    }

    public String getOption2Image() {
        return option2Image;
    }

    public void setOption2Image(String option2Image) {
        this.option2Image = option2Image;
    }

    public String getOption3Image() {
        return option3Image;
    }

    public void setOption3Image(String option3Image) {
        this.option3Image = option3Image;
    }

    public String getOption4Image() {
        return option4Image;
    }

    public void setOption4Image(String option4Image) {
        this.option4Image = option4Image;
    }

    private String option4Image;

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



    public void setCriticality(String criticality) {
        this.criticality = criticality;

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
        dest.writeString(this.questionNumber);
        dest.writeString(this.examId);
        dest.writeString(this.optionA);
        dest.writeString(this.optionB);
        dest.writeString(this.optionC);
        dest.writeString(this.optionD);
        dest.writeInt(this.attemptedAnswer);
        dest.writeInt(this.correctAnswer);
        dest.writeInt(this.timeTakentoAttempt);
        dest.writeString(this.hasImage);
        dest.writeString(this.option1Image);
        dest.writeString(this.option2Image);
        dest.writeString(this.option3Image);
        dest.writeString(this.option4Image);
        dest.writeString(this.examTitle);
        dest.writeInt(this.examDuration);
        dest.writeInt(this.totalMarks);
        dest.writeInt(this.negativeMarks);
        dest.writeString(this.criticality);
        dest.writeInt(this.answerMark);
    }

    protected AttemptedQuestionModel(Parcel in) {
        this.questionNumber = in.readString();
        this.examId = in.readString();
        this.optionA = in.readString();
        this.optionB = in.readString();
        this.optionC = in.readString();
        this.optionD = in.readString();
        this.attemptedAnswer = in.readInt();
        this.correctAnswer = in.readInt();
        this.timeTakentoAttempt = in.readInt();
        this.hasImage = in.readString();
        this.option1Image = in.readString();
        this.option2Image = in.readString();
        this.option3Image = in.readString();
        this.option4Image = in.readString();
        this.examTitle = in.readString();
        this.examDuration = in.readInt();
        this.totalMarks = in.readInt();
        this.negativeMarks = in.readInt();
        this.criticality = in.readString();
        this.answerMark = in.readInt();
    }

    public static final Creator<AttemptedQuestionModel> CREATOR = new Creator<AttemptedQuestionModel>() {
        @Override
        public AttemptedQuestionModel createFromParcel(Parcel source) {
            return new AttemptedQuestionModel(source);
        }

        @Override
        public AttemptedQuestionModel[] newArray(int size) {
            return new AttemptedQuestionModel[size];
        }
    };
}
