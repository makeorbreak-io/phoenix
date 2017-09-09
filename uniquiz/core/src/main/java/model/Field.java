package model;

import dto.FieldDTO;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by fabiolourenco on 08/09/17.
 */
@Entity
public class Field implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long pk;

    @Version
    private Long version;

    private String fieldName;

    public Field() {
    }

    public Field(String fieldName) {
        this.fieldName = fieldName;
    }

    public Long getPk() {
        return pk;
    }

    public void setPk(Long pk) {
        this.pk = pk;
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

        Field field = (Field) o;

        return fieldName != null ? fieldName.equalsIgnoreCase(field.fieldName) : false;
    }

    @Override
    public int hashCode() {
        return fieldName != null ? fieldName.hashCode() : 0;
    }

    public FieldDTO toDTO(){
        return new FieldDTO(this.pk, this.fieldName);
    }
}
