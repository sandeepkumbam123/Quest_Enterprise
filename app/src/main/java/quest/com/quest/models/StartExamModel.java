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


    public StartExamModel(int duration, int totalMarks,
                          String examTitle, int negativeMarks, String critical_level, boolean isSuccess, int errorCode, String errorMessage,
                          List<QuestionModel> questionsList) {
        Duration = duration;
        TotalMarks = totalMarks;
        ExamTitle = examTitle;
        NegativeMarks = negativeMarks;
        Critical_level = critical_level;
        this.isSuccess = isSuccess;
        ErrorCode = errorCode;
        ErrorMessage = errorMessage;
        QuestionsList = questionsList;
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

    public  static class QuestionModel {
        private int questionId;
        private int examId;
        private int questionNumber;
        private String question;


        private String criticallity;
        private String isImage;
        private int subjectId;
        private  int chapterId;
        private int classId;
        private String notes;

        public    QuestionModel(int questionId, int examId, int questionNumber, String question, String criticallity, String isImage,
                             int subjectId, int chapterId, int classId, String notes, String createAt, String updatedAt, String deletedAt,
                             String updatedBy, String optionA, String optionB, String optionC, String optionD, String hasOption1Image,
                             String hasOption2Image, String hasOption3Image, String hasOption4Image, int correctAnswer, String  answer,
                             int negativeMark) {
            this.questionId = questionId;
            this.examId = examId;
            this.questionNumber = questionNumber;
            this.question = question;
            this.criticallity = criticallity;
            this.isImage = isImage;
            this.subjectId = subjectId;
            this.chapterId = chapterId;
            this.classId = classId;
            this.notes = notes;
            this.createAt = createAt;
            this.updatedAt = updatedAt;
            this.deletedAt = deletedAt;
            this.updatedBy = updatedBy;
            this.optionA = optionA;
            this.optionB = optionB;
            this.optionC = optionC;
            this.optionD = optionD;
            this.hasOption1Image = hasOption1Image;
            this.hasOption2Image = hasOption2Image;
            this.hasOption3Image = hasOption3Image;
            this.hasOption4Image = hasOption4Image;
            this.correctAnswer = answer;
            this.negativeMark = negativeMark;
        }

        public String getCriticallity() {

            return criticallity;
        }

        public void setCriticallity(String criticallity) {
            this.criticallity = criticallity;
        }

        public String isImage() {
            return isImage;
        }

        public void setImage(String image) {
            isImage = image;
        }

        public int getSubjectId() {
            return subjectId;
        }

        public void setSubjectId(int subjectId) {
            this.subjectId = subjectId;
        }

        public int getChapterId() {
            return chapterId;
        }

        public void setChapterId(int chapterId) {
            this.chapterId = chapterId;
        }

        public int getClassId() {
            return classId;
        }

        public void setClassId(int classId) {
            this.classId = classId;
        }

        public String getNotes() {
            return notes;
        }

        public void setNotes(String notes) {
            this.notes = notes;
        }

        public String getCreateAt() {
            return createAt;
        }

        public void setCreateAt(String createAt) {
            this.createAt = createAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getDeletedAt() {
            return deletedAt;
        }

        public void setDeletedAt(String deletedAt) {
            this.deletedAt = deletedAt;
        }

        public String getUpdatedBy() {
            return updatedBy;
        }

        public void setUpdatedBy(String updatedBy) {
            this.updatedBy = updatedBy;
        }

        public String isHasOption1Image() {
            return hasOption1Image;
        }

        public void setHasOption1Image(String hasOption1Image) {
            this.hasOption1Image = hasOption1Image;
        }

        public String isHasOption2Image() {
            return hasOption2Image;
        }

        public void setHasOption2Image(String hasOption2Image) {
            this.hasOption2Image = hasOption2Image;
        }

        public String isHasOption3Image() {
            return hasOption3Image;
        }

        public void setHasOption3Image(String hasOption3Image) {
            this.hasOption3Image = hasOption3Image;
        }

        public String isHasOption4Image() {
            return hasOption4Image;
        }

        public void setHasOption4Image(String hasOption4Image) {
            this.hasOption4Image = hasOption4Image;
        }

        private String createAt;
        private String updatedAt;
        private String deletedAt;
        private String updatedBy;


        private String optionA;
        private String optionB;
        private String optionC;
        private String optionD;
        private String hasOption1Image;
        private String hasOption2Image;
        private String hasOption3Image;
        private String hasOption4Image;
        private String correctAnswer;
        private int answerMark;
        private int negativeMark;

        public int getQuestionId() {
            return questionId;
        }

        public void setQuestionId(int questionId) {
            this.questionId = questionId;
        }

        public int getExamId() {
            return examId;
        }

        public void setExamId(int examId) {
            this.examId = examId;
        }

        public int getQuestionNumber() {
            return questionNumber;
        }

        public void setQuestionNumber(int questionNumber) {
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

        public String getCorrectAnswer() {
            return correctAnswer;
        }

        public void setCorrectAnswer(String correctAnswer) {
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