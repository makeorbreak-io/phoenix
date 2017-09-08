package dto;

import java.time.LocalDateTime;

/**
 * Created by Rafael Santos on 08-09-2017.
 */
public class SolutionDTO {

    private Long pk;
    private Long quizPk;
    private String username;
    private byte rightAnswers;
    private byte wrongAnswers;
    private LocalDateTime solvedOn;

    public SolutionDTO(Long pk, Long quizPk, String username,
                        byte rightAnswers, byte wrongAnswers, LocalDateTime solvedOn){
        this.setPk(pk);
        this.setQuizPk(quizPk);
        this.setUsername(username);
        this.setRightAnswers(rightAnswers);
        this.setWrongAnswers(wrongAnswers);
        this.setSolvedOn(solvedOn);
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}
