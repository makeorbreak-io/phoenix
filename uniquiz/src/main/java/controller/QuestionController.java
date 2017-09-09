package controller;

import dto.QuestionDTO;
import model.Question;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repositories.QuestionRepository;

import javax.persistence.NoResultException;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by Rafael Santos on 09-09-2017.
 */
@RestController
@RequestMapping("/questions")
public class QuestionController {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<QuestionDTO>> findAll(){
        QuestionRepository repo = new QuestionRepository();

        List<QuestionDTO> questionList = new LinkedList<>();
        for(Question question : repo.findAll()){
            questionList.add(question.toDTO());
        }
        return new ResponseEntity<>(questionList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{questionPk}")
    public ResponseEntity<QuestionDTO> findOne(@PathVariable Long questionPk) {
        try {
            QuestionRepository repo = new QuestionRepository();

            QuestionDTO questionDTO = repo.findOne(questionPk).get().toDTO();

            return new ResponseEntity<>(questionDTO, HttpStatus.OK);
        }catch (NoResultException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<QuestionDTO> add(@RequestBody Question question) {
        try {
            if(question.getPk() != null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            QuestionRepository repo = new QuestionRepository();
            question = repo.save(question);

            return new ResponseEntity<>(question.toDTO(), HttpStatus.CREATED);

        }catch (DataIntegrityViolationException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<QuestionDTO> update(@RequestBody Question question) {
        try {
            QuestionRepository repo = new QuestionRepository();
            Question newQuestion = repo.findOne(question.getPk()).get();

            newQuestion.setPk(question.getPk());
            newQuestion.setAnswers(question.getAnswers());
            newQuestion.setQuestion(question.getQuestion());

            newQuestion = repo.save(newQuestion);

            return new ResponseEntity<>(newQuestion.toDTO(), HttpStatus.OK);

        }catch (DataIntegrityViolationException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
