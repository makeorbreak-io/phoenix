package controller;

import dto.FieldDTO;
import model.Field;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repositories.FieldRepository;

import javax.persistence.NoResultException;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by Rafael Santos on 09-09-2017.
 */
@RestController
@RequestMapping("/fields")
public class FieldController {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<FieldDTO>> findAll(){
        FieldRepository repo = new FieldRepository();

        List<FieldDTO> fieldList = new LinkedList<>();
        for(Field field : repo.findAll()){
            fieldList.add(field.toDTO());
        }
        return new ResponseEntity<>(fieldList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{fieldPk}")
    public ResponseEntity<FieldDTO> findOne(@PathVariable Long fieldPk) {
        try {
            FieldRepository repo = new FieldRepository();

            FieldDTO fieldDTO = repo.findOne(fieldPk).get().toDTO();

            return new ResponseEntity<>(fieldDTO, HttpStatus.OK);
        }catch (NoResultException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<FieldDTO> add(@RequestBody Field field) {
        try {
            if(field.getPk() != null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            FieldRepository repo = new FieldRepository();
            field = repo.save(field);

            return new ResponseEntity<>(field.toDTO(), HttpStatus.CREATED);

        }catch (DataIntegrityViolationException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<FieldDTO> update(@RequestBody Field field) {
        try {
            FieldRepository repo = new FieldRepository();
            Field newField = repo.findOne(field.getPk()).get();

            newField.setPk(field.getPk());
            newField.setFieldName(field.getFieldName());

            newField = repo.save(newField);

            return new ResponseEntity<>(newField.toDTO(), HttpStatus.OK);

        }catch (DataIntegrityViolationException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
