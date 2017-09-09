package model;

import dto.CourseDTO;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by fabiolourenco on 08/09/17.
 */
@Entity
public class Course implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long pk;

    @Version
    private Long version;

    private String courseName;

    private String fieldName;
    private Long fieldPk;

    public Course() {
    }

    public Course(String courseName, String fieldName, Long fieldPk) {
        this.courseName = courseName;
        this.fieldName = fieldName;
        this.fieldPk = fieldPk;
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

    public Long getFieldPk() {
        return fieldPk;
    }

    public void setFieldPk(Long fieldPk) {
        this.fieldPk = fieldPk;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        return courseName != null ? courseName.equalsIgnoreCase(course.courseName) : false;
    }

    @Override
    public int hashCode() {
        return courseName != null ? courseName.hashCode() : 0;
    }

    public CourseDTO toDTO(){
        return new CourseDTO(this.pk, this.courseName, this.fieldName, this.fieldPk);
    }
}
