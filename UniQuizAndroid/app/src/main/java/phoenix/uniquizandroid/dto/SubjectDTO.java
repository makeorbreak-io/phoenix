package phoenix.uniquizandroid.dto;

/**
 * Created by Rafael Santos on 08-09-2017.
 */
public class SubjectDTO {

    private Long pk;
    private String subjectName;

    public SubjectDTO(Long pk, String subjectName){
        this.setPk(pk);
        this.setSubjectName(subjectName);
    }

    public SubjectDTO(){

    }

    public Long getPk() {
        return pk;
    }

    public void setPk(Long pk) {
        this.pk = pk;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
