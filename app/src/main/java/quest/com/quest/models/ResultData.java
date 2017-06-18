package quest.com.quest.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private int timeTakentoAttempt;
    private List<FastestAnswersModel> fastestAttemptedAnswers;
    private Map<Integer , Integer> listofAnswersAttempted ;

    public ResultData(String examId, String examTitle, int examDuration, int examTotalMarks,
                      int obtainedMarks, int numberofAttemptedAnswers, int numberofCorrectAnswers,
                      String subject, List<FastestAnswersModel> fastestAttemptedAnswers , int totalQuestions ,
                      Map<Integer , Integer> listofAnswersAttempted , int timeTakentoAttempt) {
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
        this.listofAnswersAttempted =listofAnswersAttempted;
        this.timeTakentoAttempt = timeTakentoAttempt;
    }

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

    public int getTimeTakentoAttempt() {
        return timeTakentoAttempt;
    }

    public void setTimeTakentoAttempt(int timeTakentoAttempt) {
        this.timeTakentoAttempt = timeTakentoAttempt;
    }

    public Map<Integer, Integer> getListofAnswersAttempted() {
        return listofAnswersAttempted;
    }

    public void setListofAnswersAttempted(Map<Integer, Integer> listofAnswersAttempted) {
        this.listofAnswersAttempted = listofAnswersAttempted;
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
        dest.writeString(this.examId);
        dest.writeString(this.examTitle);
        dest.writeInt(this.examDuration);
        dest.writeInt(this.examTotalMarks);
        dest.writeInt(this.obtainedMarks);
        dest.writeInt(this.numberofAttemptedAnswers);
        dest.writeInt(this.numberofCorrectAnswers);
        dest.writeInt(this.totalQuestions);
        dest.writeString(this.subject);
        dest.writeInt(this.timeTakentoAttempt);
        dest.writeInt(this.listofAnswersAttempted.size());
        for (Map.Entry<Integer, Integer> entry : this.listofAnswersAttempted.entrySet()) {
            dest.writeValue(entry.getKey());
            dest.writeValue(entry.getValue());
        }
    }

    protected ResultData(Parcel in) {
        this.examId = in.readString();
        this.examTitle = in.readString();
        this.examDuration = in.readInt();
        this.examTotalMarks = in.readInt();
        this.obtainedMarks = in.readInt();
        this.numberofAttemptedAnswers = in.readInt();
        this.numberofCorrectAnswers = in.readInt();
        this.totalQuestions = in.readInt();
        this.subject = in.readString();
        this.timeTakentoAttempt = in.readInt();
        this.fastestAttemptedAnswers = new ArrayList<FastestAnswersModel>();
        in.readList(this.fastestAttemptedAnswers, FastestAnswersModel.class.getClassLoader());
        int listofAnswersAttemptedSize = in.readInt();
        this.listofAnswersAttempted = new HashMap<Integer, Integer>(listofAnswersAttemptedSize);
        for (int i = 0; i < listofAnswersAttemptedSize; i++) {
            Integer key = (Integer) in.readValue(Integer.class.getClassLoader());
            Integer value = (Integer) in.readValue(Integer.class.getClassLoader());
            this.listofAnswersAttempted.put(key, value);
        }
    }

    public static final Creator<ResultData> CREATOR = new Creator<ResultData>() {
        @Override
        public ResultData createFromParcel(Parcel source) {
            return new ResultData(source);
        }

        @Override
        public ResultData[] newArray(int size) {
            return new ResultData[size];
        }
    };
}
