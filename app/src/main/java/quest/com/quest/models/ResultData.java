package quest.com.quest.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by kumbh on 30-05-2017.
 */

public class ResultData implements Parcelable{


    private String examId;
    private String examTitle;
    private int examDuration;
    private int examTotalMarks;
    private int obtainedMarks;
    private int numberofAttemptedAnswers;
    private int numberofCorrectAnswers;
    private int totalQuestions;
    private String subject;
    private List<FastestAnswersModel> fastestAttemptedAnswers;

    public ResultData(String examId, String examTitle, int examDuration, int examTotalMarks,
                      int obtainedMarks, int numberofAttemptedAnswers, int numberofCorrectAnswers,
                      String subject, List<FastestAnswersModel> fastestAttemptedAnswers , int totalQuestions) {
        this.examId = examId;
        this.examTitle = examTitle;
        this.examDuration = examDuration;
        this.examTotalMarks = examTotalMarks;
        this.obtainedMarks = obtainedMarks;
        this.numberofAttemptedAnswers = numberofAttemptedAnswers;
        this.numberofCorrectAnswers = numberofCorrectAnswers;
        this.subject = subject;
        this.fastestAttemptedAnswers = fastestAttemptedAnswers;
        this.totalQuestions = totalQuestions;
    }

    protected ResultData(Parcel in) {
        examId = in.readString();
        examTitle = in.readString();
        examDuration = in.readInt();
        examTotalMarks = in.readInt();
        obtainedMarks = in.readInt();
        numberofAttemptedAnswers = in.readInt();
        numberofCorrectAnswers = in.readInt();
        subject = in.readString();
    }

    public static final Creator<ResultData> CREATOR = new Creator<ResultData>() {
        @Override
        public ResultData createFromParcel(Parcel in) {
            return new ResultData(in);
        }

        @Override
        public ResultData[] newArray(int size) {
            return new ResultData[size];
        }
    };

    public String getExamTitle() {
        return examTitle;
    }

    public void setExamTitle(String examTitle) {
        this.examTitle = examTitle;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public int getExamDuration() {
        return examDuration;
    }

    public void setExamDuration(int examDuration) {
        this.examDuration = examDuration;
    }

    public int getExamTotalMarks() {
        return examTotalMarks;
    }

    public void setExamTotalMarks(int examTotalMarks) {
        this.examTotalMarks = examTotalMarks;
    }

    public int getObtainedMarks() {
        return obtainedMarks;
    }

    public void setObtainedMarks(int obtainedMarks) {
        this.obtainedMarks = obtainedMarks;
    }

    public int getNumberofAttemptedAnswers() {
        return numberofAttemptedAnswers;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public static Creator<ResultData> getCREATOR() {
        return CREATOR;
    }

    public void setNumberofAttemptedAnswers(int numberofAttemptedAnswers) {
        this.numberofAttemptedAnswers = numberofAttemptedAnswers;
    }

    public int getNumberofCorrectAnswers() {
        return numberofCorrectAnswers;
    }

    public void setNumberofCorrectAnswers(int numberofCorrectAnswers) {
        this.numberofCorrectAnswers = numberofCorrectAnswers;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<FastestAnswersModel> getFastestAttemptedAnswers() {
        return fastestAttemptedAnswers;
    }

    public void setFastestAttemptedAnswers(List<FastestAnswersModel> fastestAttemptedAnswers) {
        this.fastestAttemptedAnswers = fastestAttemptedAnswers;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(examId);
        dest.writeString(examTitle);
        dest.writeInt(examDuration);
        dest.writeInt(examTotalMarks);
        dest.writeInt(obtainedMarks);
        dest.writeInt(numberofAttemptedAnswers);
        dest.writeInt(numberofCorrectAnswers);
        dest.writeString(subject);
    }
}
