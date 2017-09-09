package phoenix.uniquizandroid.dto;

/**
 * Created by Rafael Santos on 08-09-2017.
 */
public class SimplifiedQuizDTO {

    private Long pk;
    private String difficulty;
    private Long subjectPk;
    private String subjectName;
    private Long coursePk;
    private String title;

    public SimplifiedQuizDTO(Long pk, String difficulty, Long subjectPk,
                            String subjectName, Long coursePk, String title){
        this.setPk(pk);
        this.setDifficulty(difficulty);
        this.setSubjectPk(subjectPk);
        this.setSubjectName(subjectName);
        this.setCoursePk(coursePk);
        this.setTitle(title);
    }

    public Long getPk() {
        return pk;
    }

    public void setPk(Long pk) {
        this.pk = pk;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Long getSubjectPk() {
        return subjectPk;
    }

    public void setSubjectPk(Long subjectPk) {
        this.subjectPk = subjectPk;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Long getCoursePk() {
        return coursePk;
    }

    public void setCoursePk(Long coursePk) {
        this.coursePk = coursePk;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
