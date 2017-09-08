import org.eclipse.persistence.jpa.jpql.parser.DateTime;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Solution implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long pk;

    @Version
    private Long version;

    private Quiz quiz;
    private User user;
    private byte rightAnswers;
    private byte wrongAnswers;
    private DateTime solvedOn;

    public Solution() {
    }

    public Long getPk() {
        return pk;
    }

    public void setPk(Long pk) {
        this.pk = pk;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public byte getRightAnswers() {
        return rightAnswers;
    }

    public void setRightAnswers(byte rightAnswers) {
        this.rightAnswers = rightAnswers;
    }

    public byte getWrongAnswers() {
        return wrongAnswers;
    }

    public void setWrongAnswers(byte wrongAnswers) {
        this.wrongAnswers = wrongAnswers;
    }

    public DateTime getSolvedOn() {
        return solvedOn;
    }

    public void setSolvedOn(DateTime solvedOn) {
        this.solvedOn = solvedOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Solution solution = (Solution) o;

        return pk != null ? pk.equals(solution.pk) : solution.pk == null;
    }

    @Override
    public int hashCode() {
        return pk != null ? pk.hashCode() : 0;
    }
}
