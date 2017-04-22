package quest.com.quest.models;

/**
 * Created by kumbh on 22-03-2017.
 */

public class CreatedExamsModel {


    private String examTitle;
    private String classOfExam;
    private String dateofExam;

    public   CreatedExamsModel(String examTitle,String classOfExam,String dateofExam){
        this.examTitle = examTitle;
        this.classOfExam = classOfExam;
        this.dateofExam = dateofExam;
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
