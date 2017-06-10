package quest.com.quest.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kumbh on 10-06-2017.
 */

public class PreviousExamsListModel implements Parcelable {

    /**
     * exam_manualID : EXAM2017-1
     * title :
     * critical_level : Easy
     * total_marks : 20
     * pass_percentage : 10
     * exam_date : 2017-05-31 00:00:00
     * negative_mark : 0
     * duration : 3600
     * note : Exam level note
     * subjects : [{"subjectID":1,"class_names_classID":1,"subject":"branch one class one subject one","created_by":null,"created_at":"2017-05-20 19:54:55","deleted_at":null,"updated_at":"2017-05-20 20:21:56"},{"subjectID":2,"class_names_classID":1,"subject":"branch one class one subject two","created_by":null,"created_at":"2017-05-20 20:32:25","deleted_at":null,"updated_at":"2017-05-20 21:02:45"}]
     * chapters : [{"chapterID":1,"subjects_subjectID":1,"chapter":"branch one class one subject one  chapter one","createdby":null,"created_at":null,"deleted_at":null,"updated_at":null},{"chapterID":3,"subjects_subjectID":1,"chapter":"branch one class one subject one  chapter two","createdby":null,"created_at":null,"deleted_at":null,"updated_at":null}]
     * question_list : [{"questionID":1,"subjects_subjectID":1,"chapters_chapterID":1,"syllabuses_syllabuseID":1,"class_names_classID":1,"question":"question1","mark":1,"critical_level":"1","is_image":"no","createdby":null,"created_at":null,"deleted_at":"2017-05-31 11:35:39","updated_at":"2017-05-31 10:00:49","question_optionsID":1,"questions_questionID":1,"option1":"o1","is_option1_image":"no","option2":"o2","is_option2_image":"no","option3":"o3","is_option3_image":"no","option4":"o4","is_option4_image":"no","answer":"option1","notes":"notessss","user_anser":"option4"},{"questionID":2,"subjects_subjectID":1,"chapters_chapterID":1,"syllabuses_syllabuseID":1,"class_names_classID":1,"question":"questiona","mark":2,"critical_level":"2","is_image":"no","createdby":null,"created_at":null,"deleted_at":null,"updated_at":"2017-05-31 10:01:18","question_optionsID":2,"questions_questionID":2,"option1":"a112","is_option1_image":"no","option2":"a223","is_option2_image":"no","option3":"a334","is_option3_image":"no","option4":"a445","is_option4_image":"no","answer":"option3","notes":"nnnnn12341","user_anser":"option3"}]
     * isSuccess : true
     * ErrorCode : null
     * ErrorMessage : null
     */

    private String exam_manualID;
    private String title;
    private String critical_level;
    private int total_marks;
    private int pass_percentage;
    private String exam_date;
    private int negative_mark;
    private int duration;
    private String note;
    private boolean isSuccess;
    private String ErrorCode;
    private String ErrorMessage;
    private List<SubjectsBean> subjects;
    private List<ChaptersBean> chapters;
    private List<QuestionListBean> question_list;

    public PreviousExamsListModel(String exam_manualID, String title, String critical_level, int total_marks, int pass_percentage, String exam_date,
                                  int negative_mark, int duration, String note, boolean isSuccess,
                                  String errorCode, String errorMessage, List<SubjectsBean> subjects,
                                  List<ChaptersBean> chapters, List<QuestionListBean> question_list) {
        this.exam_manualID = exam_manualID;
        this.title = title;
        this.critical_level = critical_level;
        this.total_marks = total_marks;
        this.pass_percentage = pass_percentage;
        this.exam_date = exam_date;
        this.negative_mark = negative_mark;
        this.duration = duration;
        this.note = note;
        this.isSuccess = isSuccess;
        ErrorCode = errorCode;
        ErrorMessage = errorMessage;
        this.subjects = subjects;
        this.chapters = chapters;
        this.question_list = question_list;
    }

    public static PreviousExamsListModel objectFromData(String str) {

        return new Gson().fromJson(str, PreviousExamsListModel.class);
    }

    public String getExam_manualID() {
        return exam_manualID;
    }

    public void setExam_manualID(String exam_manualID) {
        this.exam_manualID = exam_manualID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCritical_level() {
        return critical_level;
    }

    public void setCritical_level(String critical_level) {
        this.critical_level = critical_level;
    }

    public int getTotal_marks() {
        return total_marks;
    }

    public void setTotal_marks(int total_marks) {
        this.total_marks = total_marks;
    }

    public int getPass_percentage() {
        return pass_percentage;
    }

    public void setPass_percentage(int pass_percentage) {
        this.pass_percentage = pass_percentage;
    }

    public String getExam_date() {
        return exam_date;
    }

    public void setExam_date(String exam_date) {
        this.exam_date = exam_date;
    }

    public int getNegative_mark() {
        return negative_mark;
    }

    public void setNegative_mark(int negative_mark) {
        this.negative_mark = negative_mark;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(String ErrorCode) {
        this.ErrorCode = ErrorCode;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(String ErrorMessage) {
        this.ErrorMessage = ErrorMessage;
    }

    public List<SubjectsBean> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectsBean> subjects) {
        this.subjects = subjects;
    }

    public List<ChaptersBean> getChapters() {
        return chapters;
    }

    public void setChapters(List<ChaptersBean> chapters) {
        this.chapters = chapters;
    }

    public List<QuestionListBean> getQuestion_list() {
        return question_list;
    }

    public void setQuestion_list(List<QuestionListBean> question_list) {
        this.question_list = question_list;
    }

    public static class SubjectsBean {
        /**
         * subjectID : 1
         * class_names_classID : 1
         * subject : branch one class one subject one
         * created_by : null
         * created_at : 2017-05-20 19:54:55
         * deleted_at : null
         * updated_at : 2017-05-20 20:21:56
         */

        private int subjectID;
        private int class_names_classID;
        private String subject;
        private String created_by;
        private String created_at;
        private String deleted_at;
        private String updated_at;

        public SubjectsBean(int subjectID, int class_names_classID, String subject, String created_by, String created_at, String deleted_at, String updated_at) {
            this.subjectID = subjectID;
            this.class_names_classID = class_names_classID;
            this.subject = subject;
            this.created_by = created_by;
            this.created_at = created_at;
            this.deleted_at = deleted_at;
            this.updated_at = updated_at;
        }

        public static SubjectsBean objectFromData(String str) {

            return new Gson().fromJson(str, SubjectsBean.class);
        }

        public int getSubjectID() {
            return subjectID;
        }

        public void setSubjectID(int subjectID) {
            this.subjectID = subjectID;
        }

        public int getClass_names_classID() {
            return class_names_classID;
        }

        public void setClass_names_classID(int class_names_classID) {
            this.class_names_classID = class_names_classID;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getCreated_by() {
            return created_by;
        }

        public void setCreated_by(String created_by) {
            this.created_by = created_by;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getDeleted_at() {
            return deleted_at;
        }

        public void setDeleted_at(String deleted_at) {
            this.deleted_at = deleted_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }
    }

    public static class ChaptersBean {
        /**
         * chapterID : 1
         * subjects_subjectID : 1
         * chapter : branch one class one subject one  chapter one
         * createdby : null
         * created_at : null
         * deleted_at : null
         * updated_at : null
         */

        private int chapterID;
        private int subjects_subjectID;
        private String chapter;
        private Object createdby;
        private Object created_at;
        private Object deleted_at;
        private Object updated_at;

        public ChaptersBean(int chapterID, int subjects_subjectID, String chapter, Object createdby, Object created_at, Object deleted_at, Object updated_at) {
            this.chapterID = chapterID;
            this.subjects_subjectID = subjects_subjectID;
            this.chapter = chapter;
            this.createdby = createdby;
            this.created_at = created_at;
            this.deleted_at = deleted_at;
            this.updated_at = updated_at;
        }

        public static ChaptersBean objectFromData(String str) {

            return new Gson().fromJson(str, ChaptersBean.class);
        }

        public int getChapterID() {
            return chapterID;
        }

        public void setChapterID(int chapterID) {
            this.chapterID = chapterID;
        }

        public int getSubjects_subjectID() {
            return subjects_subjectID;
        }

        public void setSubjects_subjectID(int subjects_subjectID) {
            this.subjects_subjectID = subjects_subjectID;
        }

        public String getChapter() {
            return chapter;
        }

        public void setChapter(String chapter) {
            this.chapter = chapter;
        }

        public Object getCreatedby() {
            return createdby;
        }

        public void setCreatedby(Object createdby) {
            this.createdby = createdby;
        }

        public Object getCreated_at() {
            return created_at;
        }

        public void setCreated_at(Object created_at) {
            this.created_at = created_at;
        }

        public Object getDeleted_at() {
            return deleted_at;
        }

        public void setDeleted_at(Object deleted_at) {
            this.deleted_at = deleted_at;
        }

        public Object getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(Object updated_at) {
            this.updated_at = updated_at;
        }
    }

    public static class QuestionListBean {
        /**
         * questionID : 1
         * subjects_subjectID : 1
         * chapters_chapterID : 1
         * syllabuses_syllabuseID : 1
         * class_names_classID : 1
         * question : question1
         * mark : 1
         * critical_level : 1
         * is_image : no
         * createdby : null
         * created_at : null
         * deleted_at : 2017-05-31 11:35:39
         * updated_at : 2017-05-31 10:00:49
         * question_optionsID : 1
         * questions_questionID : 1
         * option1 : o1
         * is_option1_image : no
         * option2 : o2
         * is_option2_image : no
         * option3 : o3
         * is_option3_image : no
         * option4 : o4
         * is_option4_image : no
         * answer : option1
         * notes : notessss
         * user_anser : option4
         */

        private int questionID;
        private int subjects_subjectID;
        private int chapters_chapterID;
        private int syllabuses_syllabuseID;
        private int class_names_classID;
        private String question;
        private int mark;
        private String critical_level;
        private String is_image;
        private String createdby;
        private String created_at;
        private String deleted_at;
        private String updated_at;
        private int question_optionsID;
        private int questions_questionID;
        private String option1;
        private String is_option1_image;
        private String option2;
        private String is_option2_image;
        private String option3;
        private String is_option3_image;
        private String option4;
        private String is_option4_image;
        private String answer;
        private String notes;
        private String user_anser;

        public QuestionListBean(int questionID, int subjects_subjectID, int chapters_chapterID, int syllabuses_syllabuseID, int class_names_classID,
                                String question, int mark, String critical_level, String is_image, String createdby, String created_at, String deleted_at,
                                String updated_at, int question_optionsID, int questions_questionID, String option1, String is_option1_image,
                                String option2, String is_option2_image,
                                String option3, String is_option3_image, String option4, String is_option4_image, String answer,
                                String notes, String user_anser) {
            this.questionID = questionID;
            this.subjects_subjectID = subjects_subjectID;
            this.chapters_chapterID = chapters_chapterID;
            this.syllabuses_syllabuseID = syllabuses_syllabuseID;
            this.class_names_classID = class_names_classID;
            this.question = question;
            this.mark = mark;
            this.critical_level = critical_level;
            this.is_image = is_image;
            this.createdby = createdby;
            this.created_at = created_at;
            this.deleted_at = deleted_at;
            this.updated_at = updated_at;
            this.question_optionsID = question_optionsID;
            this.questions_questionID = questions_questionID;
            this.option1 = option1;
            this.is_option1_image = is_option1_image;
            this.option2 = option2;
            this.is_option2_image = is_option2_image;
            this.option3 = option3;
            this.is_option3_image = is_option3_image;
            this.option4 = option4;
            this.is_option4_image = is_option4_image;
            this.answer = answer;
            this.notes = notes;
            this.user_anser = user_anser;
        }

        public static QuestionListBean objectFromData(String str) {

            return new Gson().fromJson(str, QuestionListBean.class);
        }

        public int getQuestionID() {
            return questionID;
        }

        public void setQuestionID(int questionID) {
            this.questionID = questionID;
        }

        public int getSubjects_subjectID() {
            return subjects_subjectID;
        }

        public void setSubjects_subjectID(int subjects_subjectID) {
            this.subjects_subjectID = subjects_subjectID;
        }

        public int getChapters_chapterID() {
            return chapters_chapterID;
        }

        public void setChapters_chapterID(int chapters_chapterID) {
            this.chapters_chapterID = chapters_chapterID;
        }

        public int getSyllabuses_syllabuseID() {
            return syllabuses_syllabuseID;
        }

        public void setSyllabuses_syllabuseID(int syllabuses_syllabuseID) {
            this.syllabuses_syllabuseID = syllabuses_syllabuseID;
        }

        public int getClass_names_classID() {
            return class_names_classID;
        }

        public void setClass_names_classID(int class_names_classID) {
            this.class_names_classID = class_names_classID;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public int getMark() {
            return mark;
        }

        public void setMark(int mark) {
            this.mark = mark;
        }

        public String getCritical_level() {
            return critical_level;
        }

        public void setCritical_level(String critical_level) {
            this.critical_level = critical_level;
        }

        public String getIs_image() {
            return is_image;
        }

        public void setIs_image(String is_image) {
            this.is_image = is_image;
        }

        public String getCreatedby() {
            return createdby;
        }

        public void setCreatedby(String createdby) {
            this.createdby = createdby;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getDeleted_at() {
            return deleted_at;
        }

        public void setDeleted_at(String deleted_at) {
            this.deleted_at = deleted_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public int getQuestion_optionsID() {
            return question_optionsID;
        }

        public void setQuestion_optionsID(int question_optionsID) {
            this.question_optionsID = question_optionsID;
        }

        public int getQuestions_questionID() {
            return questions_questionID;
        }

        public void setQuestions_questionID(int questions_questionID) {
            this.questions_questionID = questions_questionID;
        }

        public String getOption1() {
            return option1;
        }

        public void setOption1(String option1) {
            this.option1 = option1;
        }

        public String getIs_option1_image() {
            return is_option1_image;
        }

        public void setIs_option1_image(String is_option1_image) {
            this.is_option1_image = is_option1_image;
        }

        public String getOption2() {
            return option2;
        }

        public void setOption2(String option2) {
            this.option2 = option2;
        }

        public String getIs_option2_image() {
            return is_option2_image;
        }

        public void setIs_option2_image(String is_option2_image) {
            this.is_option2_image = is_option2_image;
        }

        public String getOption3() {
            return option3;
        }

        public void setOption3(String option3) {
            this.option3 = option3;
        }

        public String getIs_option3_image() {
            return is_option3_image;
        }

        public void setIs_option3_image(String is_option3_image) {
            this.is_option3_image = is_option3_image;
        }

        public String getOption4() {
            return option4;
        }

        public void setOption4(String option4) {
            this.option4 = option4;
        }

        public String getIs_option4_image() {
            return is_option4_image;
        }

        public void setIs_option4_image(String is_option4_image) {
            this.is_option4_image = is_option4_image;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public String getNotes() {
            return notes;
        }

        public void setNotes(String notes) {
            this.notes = notes;
        }

        public String getUser_anser() {
            return user_anser;
        }

        public void setUser_anser(String user_anser) {
            this.user_anser = user_anser;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.exam_manualID);
        dest.writeString(this.title);
        dest.writeString(this.critical_level);
        dest.writeInt(this.total_marks);
        dest.writeInt(this.pass_percentage);
        dest.writeString(this.exam_date);
        dest.writeInt(this.negative_mark);
        dest.writeInt(this.duration);
        dest.writeString(this.note);
        dest.writeByte(this.isSuccess ? (byte) 1 : (byte) 0);
        dest.writeString(this.ErrorCode);
        dest.writeString(this.ErrorMessage);
        dest.writeList(this.subjects);
        dest.writeList(this.chapters);
        dest.writeList(this.question_list);
    }

    protected PreviousExamsListModel(Parcel in) {
        this.exam_manualID = in.readString();
        this.title = in.readString();
        this.critical_level = in.readString();
        this.total_marks = in.readInt();
        this.pass_percentage = in.readInt();
        this.exam_date = in.readString();
        this.negative_mark = in.readInt();
        this.duration = in.readInt();
        this.note = in.readString();
        this.isSuccess = in.readByte() != 0;
        this.ErrorCode = in.readString();
        this.ErrorMessage = in.readString();
        this.subjects = new ArrayList<SubjectsBean>();
        in.readList(this.subjects, SubjectsBean.class.getClassLoader());
        this.chapters = new ArrayList<ChaptersBean>();
        in.readList(this.chapters, ChaptersBean.class.getClassLoader());
        this.question_list = new ArrayList<QuestionListBean>();
        in.readList(this.question_list, QuestionListBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<PreviousExamsListModel> CREATOR = new Parcelable.Creator<PreviousExamsListModel>() {
        @Override
        public PreviousExamsListModel createFromParcel(Parcel source) {
            return new PreviousExamsListModel(source);
        }

        @Override
        public PreviousExamsListModel[] newArray(int size) {
            return new PreviousExamsListModel[size];
        }
    };
}
