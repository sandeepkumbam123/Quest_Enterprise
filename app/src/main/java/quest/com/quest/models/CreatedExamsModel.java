package quest.com.quest.models;

/**
 * Created by kumbh on 22-03-2017.
 */

public class CreatedExamsModel {


    private String examTitle;
    private String classOfExam;
    private String dateofExam;
    private String examId;

    public   CreatedExamsModel(String examTitle,String classOfExam,String dateofExam , String examId){
        this.examTitle = examTitle;
        this.classOfExam = classOfExam;
        this.dateofExam = dateofExam;
        this.examId = examId ;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public String getExamTitle() {
        return examTitle;
    }

    public void setExamTitle(String examTitle) {
        this.examTitle = examTitle;
    }

    public String getClassOfExam() {
        return classOfExam;
    }

    public void setClassOfExam(String classOfExam) {
        this.classOfExam = classOfExam;
    }

    public String getDateofExam() {
        return dateofExam;
    }

    public void setDateofExam(String dateofExam) {
        this.dateofExam = dateofExam;
    }
}
