package phoenix.uniquizandroid.dto;


import java.io.Serializable;

/**
 * Created by Rafael Santos on 08-09-2017.
 */
public class AnswerDTO implements Serializable{

    private Long pk;
    private String answer;
    private boolean rightAnswer;

    public AnswerDTO(Long pk, String answer, boolean rightAnswer){
        this.setPk(pk);
        this.setAnswer(answer);
        this.setRightAnswer(rightAnswer);
    }

    public Long getPk() {
        return pk;
    }

    public void setPk(Long pk) {
        this.pk = pk;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(boolean rightAnswer) {
        this.rightAnswer = rightAnswer;
    }
}
