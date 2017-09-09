package controller;

import dto.CourseDTO;
import model.Course;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repositories.CourseRepository;

import javax.persistence.NoResultException;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by Rafael Santos on 09-09-2017.
 */
@RestController
@RequestMapping("/courses")
public class CourseController {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CourseDTO>> findAll(){
        CourseRepository repo = new CourseRepository();

        List<CourseDTO> courseList = new LinkedList<>();
        for(Course course : repo.findAll()){
            courseList.add(course.toDTO());
        }
        return new ResponseEntity<>(courseList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{coursePk}")
    public ResponseEntity<CourseDTO> findOne(@PathVariable Long coursePk) {
        try {
            CourseRepository repo = new CourseRepository();

            CourseDTO courseDTO = repo.findOne(coursePk).get().toDTO();

            return new ResponseEntity<>(courseDTO, HttpStatus.OK);
        }catch (NoResultException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CourseDTO> add(@RequestBody Course course) {
        try {
            if(course.getPk() != null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            CourseRepository repo = new CourseRepository();
            course = repo.save(course);

            return new ResponseEntity<>(course.toDTO(), HttpStatus.CREATED);

        }catch (DataIntegrityViolationException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<CourseDTO> update(@RequestBody Course course) {
        try {
            CourseRepository repo = new CourseRepository();
            Course newCourse = repo.findOne(course.getPk()).get();

            newCourse.setPk(course.getPk());
            newCourse.setCourseName(course.getCourseName());
            newCourse.setFieldName(course.getFieldName());
            newCourse.setFieldPk(course.getFieldPk());

            newCourse = repo.save(newCourse);

            return new ResponseEntity<>(newCourse.toDTO(), HttpStatus.OK);

        }catch (DataIntegrityViolationException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
