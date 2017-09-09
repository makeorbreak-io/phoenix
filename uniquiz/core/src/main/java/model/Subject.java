package model;

import dto.SubjectDTO;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by fabiolourenco on 08/09/17.
 */
@Entity
public class Subject implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long pk;

    @Version
    private Long version;

    private String subjectName;

    public Subject() {
    }

    public Subject(String subjectName) {
        this.subjectName = subjectName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subject subject = (Subject) o;

        return pk != null ? pk.equals(subject.pk) : subject.pk == null;
    }

    @Override
    public int hashCode() {
        return pk != null ? pk.hashCode() : 0;
    }

    public SubjectDTO toDTO(){
        return new SubjectDTO(this.pk, this.subjectName);
    }
}
