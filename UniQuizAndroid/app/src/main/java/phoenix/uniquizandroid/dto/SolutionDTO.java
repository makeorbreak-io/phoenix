package phoenix.uniquizandroid.dto;


/**
 * Created by Rafael Santos on 08-09-2017.
 */
public class SolutionDTO {

    private Long pk;
    private Long quizPk;
    private String email;
    private byte rightAnswers;
    private byte wrongAnswers;

    public SolutionDTO(Long pk, Long quizPk, String email,
                        byte rightAnswers, byte wrongAnswers){
        this.setPk(pk);
        this.setQuizPk(quizPk);
        this.setEmail(email);
        this.setRightAnswers(rightAnswers);
        this.setWrongAnswers(wrongAnswers);
    }
    public SolutionDTO(Long quizPk, String email,
                       byte rightAnswers, byte wrongAnswers){
        this.setQuizPk(quizPk);
        this.setEmail(email);
        this.setRightAnswers(rightAnswers);
        this.setWrongAnswers(wrongAnswers);
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

}
