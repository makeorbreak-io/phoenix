import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;

/**
 * Created by fabiolourenco on 08/09/17.
 */
@Entity
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long pk;

    @Version
    private Long version;

    private String question;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "QUESTION_PK")
    private LinkedList<Answer> answers;

    public Question() {
        answers = new LinkedList<>();
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

    public LinkedList<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(LinkedList<Answer> answers) {
        this.answers = answers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        return pk != null ? pk.equals(question.pk) : question.pk == null;
    }

    @Override
    public int hashCode() {
        return pk != null ? pk.hashCode() : 0;
    }
}
