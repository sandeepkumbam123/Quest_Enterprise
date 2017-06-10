package quest.com.quest.models;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kumbh on 25-05-2017.
 */

public class ListofExams {

    public ListofExams(boolean is_success, int errorCode, String errorMessage, List<ListOfScheduledExamsBean> listOfScheduledExams) {
        this.is_success = is_success;
        ErrorCode = errorCode;
        ErrorMessage = errorMessage;
        ListOfScheduledExams = listOfScheduledExams;
    }

    /**
     * ListOfScheduledExams : [{"exam_manualID":"EXAM2017-1","class":"first year","class_id":1,"title":"","duration":3600,"exam_status":"true","number_of_questions":2,"total_marks":20,"topics_covered":"1,3","exam_date":"2017-05-31 00:00:00","usernote":"Exam level note","subjects":[{"subjectID":1,"class_names_classID":1,"subject":"branch one class one subject one","created_by":null,"created_at":"2017-05-20 19:54:55","deleted_at":"2017-05-20 20:21:56","updated_at":"2017-05-20 20:21:56"},{"subjectID":2,"class_names_classID":1,"subject":"branch one class one subject two","created_by":null,"created_at":"2017-05-20 20:32:25","deleted_at":null,"updated_at":"2017-05-20 21:02:45"}],"chapters":[{"chapterID":1,"subjects_subjectID":1,"chapter":"branch one class one subject one  chapter one","createdby":null,"created_at":null,"deleted_at":null,"updated_at":null},{"chapterID":3,"subjects_subjectID":1,"chapter":"branch one class one subject one  chapter two","createdby":null,"created_at":null,"deleted_at":null,"updated_at":null}]},{"exam_manualID":"EXAM2017-2","class":"first year","class_id":1,"title":"","duration":3600,"exam_status":"true","number_of_questions":2,"total_marks":20,"topics_covered":"1,3","exam_date":"2017-05-31 00:00:00","usernote":"Exam level note-2","subjects":[{"subjectID":1,"class_names_classID":1,"subject":"branch one class one subject one","created_by":null,"created_at":"2017-05-20 19:54:55","deleted_at":"2017-05-20 20:21:56","updated_at":"2017-05-20 20:21:56"},{"subjectID":2,"class_names_classID":1,"subject":"branch one class one subject two","created_by":null,"created_at":"2017-05-20 20:32:25","deleted_at":null,"updated_at":"2017-05-20 21:02:45"}],"chapters":[{"chapterID":1,"subjects_subjectID":1,"chapter":"branch one class one subject one  chapter one","createdby":null,"created_at":null,"deleted_at":null,"updated_at":null},{"chapterID":3,"subjects_subjectID":1,"chapter":"branch one class one subject one  chapter two","createdby":null,"created_at":null,"deleted_at":null,"updated_at":null}]}]
     * is_success : true
     * ErrorCode : null
     * ErrorMessage : null
     */

    private boolean is_success;
    private int ErrorCode;
    private String ErrorMessage;
    private List<ListOfScheduledExamsBean> ListOfScheduledExams;

    public static ListofExams objectFromData(String str) {

        return new Gson().fromJson(str, ListofExams.class);
    }

    public boolean isIs_success() {
        return is_success;
    }

    public void setIs_success(boolean is_success) {
        this.is_success = is_success;
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

    public List<ListOfScheduledExamsBean> getListOfScheduledExams() {
        return ListOfScheduledExams;
    }

    public void setListOfScheduledExams(List<ListOfScheduledExamsBean> ListOfScheduledExams) {
        this.ListOfScheduledExams = ListOfScheduledExams;
    }

    public static class ListOfScheduledExamsBean {

        public ListOfScheduledExamsBean(String exam_manualID, String classX, int class_id, String title, int duration, boolean exam_status,
                                        int number_of_questions,
                                        int total_marks, String topics_covered, String exam_date, String usernote, List<SubjectsBean> subjects,
                                        List<ChaptersBean> chapters , int examID) {
            this.exam_manualID = exam_manualID;
            this.classX = classX;
            this.class_id = class_id;
            this.title = title;
            this.duration = duration;
            this.exam_status = exam_status;
            this.number_of_questions = number_of_questions;
            this.total_marks = total_marks;
            this.topics_covered = topics_covered;
            this.exam_date = exam_date;
            this.usernote = usernote;
            this.subjects = subjects;
            this.chapters = chapters;
            this.examID = examID;
        }

        /**
         * exam_manualID : EXAM2017-1
         * class : first year
         * class_id : 1
         * title :
         * duration : 3600
         * exam_status : true
         * number_of_questions : 2
         * total_marks : 20
         * topics_covered : 1,3
         * exam_date : 2017-05-31 00:00:00
         * usernote : Exam level note
         * subjects : [{"subjectID":1,"class_names_classID":1,"subject":"branch one class one subject one","created_by":null,"created_at":"2017-05-20 19:54:55","deleted_at":"2017-05-20 20:21:56","updated_at":"2017-05-20 20:21:56"},{"subjectID":2,"class_names_classID":1,"subject":"branch one class one subject two","created_by":null,"created_at":"2017-05-20 20:32:25","deleted_at":null,"updated_at":"2017-05-20 21:02:45"}]
         * chapters : [{"chapterID":1,"subjects_subjectID":1,"chapter":"branch one class one subject one  chapter one","createdby":null,"created_at":null,"deleted_at":null,"updated_at":null},{"chapterID":3,"subjects_subjectID":1,"chapter":"branch one class one subject one  chapter two","createdby":null,"created_at":null,"deleted_at":null,"updated_at":null}]
         */

        private int examID;

        public int getExamID() {
            return examID;
        }

        public void setExamID(int examID) {
            this.examID = examID;
        }

        public boolean isExam_status() {
            return exam_status;
        }

        private String exam_manualID;
        @SerializedName("class")
        private String classX;
        private int class_id;
        private String title;
        private int duration;
        private boolean exam_status;
        private int number_of_questions;
        private int total_marks;
        private String topics_covered;
        private String exam_date;
        private String usernote;
        private List<SubjectsBean> subjects;
        private List<ChaptersBean> chapters;

        public static ListOfScheduledExamsBean objectFromData(String str) {

            return new Gson().fromJson(str, ListOfScheduledExamsBean.class);
        }

        public String getExam_manualID() {
            return exam_manualID;
        }

        public void setExam_manualID(String exam_manualID) {
            this.exam_manualID = exam_manualID;
        }

        public String getClassX() {
            return classX;
        }

        public void setClassX(String classX) {
            this.classX = classX;
        }

        public int getClass_id() {
            return class_id;
        }

        public void setClass_id(int class_id) {
            this.class_id = class_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public boolean getExam_status() {
            return exam_status;
        }

        public void setExam_status(boolean exam_status) {
            this.exam_status = exam_status;
        }

        public int getNumber_of_questions() {
            return number_of_questions;
        }

        public void setNumber_of_questions(int number_of_questions) {
            this.number_of_questions = number_of_questions;
        }

        public int getTotal_marks() {
            return total_marks;
        }

        public void setTotal_marks(int total_marks) {
            this.total_marks = total_marks;
        }

        public String getTopics_covered() {
            return topics_covered;
        }

        public void setTopics_covered(String topics_covered) {
            this.topics_covered = topics_covered;
        }

        public String getExam_date() {
            return exam_date;
        }

        public void setExam_date(String exam_date) {
            this.exam_date = exam_date;
        }

        public String getUsernote() {
            return usernote;
        }

        public void setUsernote(String usernote) {
            this.usernote = usernote;
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

        public static class SubjectsBean {
            public SubjectsBean(int subjectID, int class_names_classID,
                                String subject, String created_by, String created_at, String deleted_at, String updated_at) {
                this.subjectID = subjectID;
                this.class_names_classID = class_names_classID;
                this.subject = subject;
                this.created_by = created_by;
                this.created_at = created_at;
                this.deleted_at = deleted_at;
                this.updated_at = updated_at;
            }

            /**
             * subjectID : 1
             * class_names_classID : 1
             * subject : branch one class one subject one
             * created_by : null
             * created_at : 2017-05-20 19:54:55
             * deleted_at : 2017-05-20 20:21:56
             * updated_at : 2017-05-20 20:21:56
             */

            private int subjectID;
            private int class_names_classID;
            private String subject;
            private String created_by;
            private String created_at;
            private String deleted_at;
            private String updated_at;

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
            public ChaptersBean(int chapterID, int subjects_subjectID, String chapter,
                                String createdby, String created_at, String deleted_at, String updated_at) {
                this.chapterID = chapterID;
                this.subjects_subjectID = subjects_subjectID;
                this.chapter = chapter;
                this.createdby = createdby;
                this.created_at = created_at;
                this.deleted_at = deleted_at;
                this.updated_at = updated_at;
            }

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
            private String createdby;
            private String created_at;
            private String deleted_at;
            private String updated_at;

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
        }
    }
}
