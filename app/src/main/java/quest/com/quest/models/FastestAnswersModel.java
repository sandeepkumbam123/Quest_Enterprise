package quest.com.quest.models;

/**
 * Created by kumbh on 30-05-2017.
 */

public class FastestAnswersModel {

    private String questionNumber;
    private int timeDuration;
    private String question;
    private int answer;

    public FastestAnswersModel(String questionNumber, int timeDuration, String question , int answer) {
        this.questionNumber = questionNumber;
        this.timeDuration = timeDuration;
        this.question = question;
        this.answer = answer;
    }

    public String getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(String questionNumber) {
        this.questionNumber = questionNumber;
    }

    public int getTimeDuration() {
        return timeDuration;
    }

    public void setTimeDuration(int timeDuration) {
        this.timeDuration = timeDuration;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }
}
