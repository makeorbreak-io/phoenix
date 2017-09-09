package model;

import dto.SolutionDTO;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;
import util.LocalDateTimeConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class Solution implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long pk;

    @Version
    private Long version;

    private Long quizPk;

    private String email;
    private byte rightAnswers;
    private byte wrongAnswers;

    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime solvedOn;

    public Solution() {
    }

    public Long getPk() {
        return pk;
    }

    public void setPk(Long pk) {
        this.pk = pk;
    }

    public Long getQuizPk() {
        return quizPk;
    }

    public void setQuizPk(Long quizPk) {
        this.quizPk = quizPk;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public LocalDateTime getSolvedOn() {
        return solvedOn;
    }

    public void setSolvedOn(LocalDateTime solvedOn) {
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

    public SolutionDTO toDTO(){
        return new SolutionDTO(this.pk, this.quizPk, this.email, this.rightAnswers, this.wrongAnswers);
    }
}
