package phoenix.uniquizandroid.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Rafael Santos on 08-09-2017.
 */
public class QuestionDTO implements Serializable{

    private Long pk;
    private String question;
    private List<AnswerDTO> answers;

    public QuestionDTO(Long pk, String question, List<AnswerDTO> answers){
        this.setPk(pk);
        this.setQuestion(question);
        this.setAnswers(answers);
    }

    public Long getPk() {
        return pk;
    }

    public void setPk(Long pk) {
        this.pk = pk;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<AnswerDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerDTO> answers) {
        this.answers = answers;
    }
}
