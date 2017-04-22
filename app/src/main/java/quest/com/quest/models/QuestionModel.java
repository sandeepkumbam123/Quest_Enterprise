package quest.com.quest.models;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import quest.com.quest.BR;


/**
 * Created by kumbh on 07-03-2017.
 */
public   class QuestionModel extends BaseObservable {

    public QuestionModel(String examSubject,int examQuestion,int totalQuestions,int questionDuration) {
    this.examSubject= examSubject;
        this.examQuestion = examQuestion;
        this.totalQuestions = totalQuestions;
        this.questionDuration = questionDuration;
    }

    private String examSubject;
    private int examQuestion;
    private int totalQuestions;
    private int questionDuration;
    private String examQuestionText;

    public String getExamQuestionText() {
        return examQuestionText;
    }

    public void setExamQuestionText(String examQuestionText) {
        this.examQuestionText = examQuestionText;
    }

    public String getExamOptionA() {
        return examOptionA;
    }

    public void setExamOptionA(String examOptionA) {
        this.examOptionA = examOptionA;
    }

    public String getExamOptionB() {
        return examOptionB;
    }

    public void setExamOptionB(String examOptionB) {
        this.examOptionB = examOptionB;
    }

    public String getExamOptionC() {
        return examOptionC;
    }

    public void setExamOptionC(String examOptionC) {
        this.examOptionC = examOptionC;
    }

    public String getExamOptionD() {
        return examOptionD;
    }

    public void setExamOptionD(String examOptionD) {
        this.examOptionD = examOptionD;
    }

    public int getExamCorrectAnswer() {
        return examCorrectAnswer;
    }

    public void setExamCorrectAnswer(int examCorrectAnswer) {
        this.examCorrectAnswer = examCorrectAnswer;
    }

    public boolean isQuestionHasImage() {
        return questionHasImage;
    }

    public void setQuestionHasImage(boolean questionHasImage) {
        this.questionHasImage = questionHasImage;
    }

    public QuestionModel(String examSubject, int examQuestion, int totalQuestions,
                         int questionDuration, String examQuestionText,
                         String examOptionA, String examOptionB, String examOptionC, String examOptionD,
                         int examCorrectAnswer, boolean questionHasImage) {
        this.examSubject = examSubject;
        this.examQuestion = examQuestion;
        this.totalQuestions = totalQuestions;
        this.questionDuration = questionDuration;
        this.examQuestionText = examQuestionText;
        this.examOptionA = examOptionA;
        this.examOptionB = examOptionB;
        this.examOptionC = examOptionC;
        this.examOptionD = examOptionD;
        this.examCorrectAnswer = examCorrectAnswer;
        this.questionHasImage = questionHasImage;
    }

    private String examOptionA;
    private String examOptionB;
    private String examOptionC;
    private String examOptionD;
    private int examCorrectAnswer;
    private boolean questionHasImage;



    @Bindable
    public String getExamSubject() {
        return examSubject;
    }

    public void setExamSubject(String examSubject) {
        this.examSubject = examSubject;
        notifyPropertyChanged(BR.examSubject);
    }

    @Bindable
    public int getExamQuestion() {
        return examQuestion;
    }


    public void setExamQuestion(int examQuestion) {
        this.examQuestion = examQuestion;
        notifyPropertyChanged(BR.examQuestion);
    }

    @Bindable
    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
        notifyPropertyChanged(BR.totalQuestions);
    }

    @Bindable
    public int getQuestionDuration() {
        return questionDuration;
    }

    public void setQuestionDuration(int questionDuration) {
        this.questionDuration = questionDuration;
        notifyPropertyChanged(BR.questionDuration);
    }
}
