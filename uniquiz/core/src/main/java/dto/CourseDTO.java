package dto;

/**
 * Created by Rafael Santos on 08-09-2017.
 */
public class CourseDTO {

    private Long pk;
    private String courseName;
    private String fieldName;
    private Long fieldPk;

    public CourseDTO(Long pk, String courseName,
                        String fieldName, Long fieldPk){
        this.setPk(pk);
        this.setCourseName(courseName);
        this.setFieldName(fieldName);
        this.setFieldPk(fieldPk);
    }

    public CourseDTO() {
    }

    public Long getPk() {
        return pk;
    }

    public void setPk(Long pk) {
        this.pk = pk;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Long getFieldPk() {
        return fieldPk;
    }

    public void setFieldPk(Long fieldPk) {
        this.fieldPk = fieldPk;
    }
}
