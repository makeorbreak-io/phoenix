package phoenix.uniquizandroid.dto;


/**
 * Created by Rafael Santos on 08-09-2017.
 */
public class FieldDTO {

    private Long pk;
    private String fieldName;

    public FieldDTO(Long pk, String fieldName){
        this.setPk(pk);
        this.setFieldName(fieldName);
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
}
