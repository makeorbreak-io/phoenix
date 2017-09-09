package controller;

import dto.AnswerDTO;
import model.Answer;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repositories.AnswerRepository;

import javax.persistence.NoResultException;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by Rafael Santos on 09-09-2017.
 */
@RestController
@RequestMapping("/answers")
public class AnswerController {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<AnswerDTO>> findAll(){
        AnswerRepository repo = new AnswerRepository();

        List<AnswerDTO> quizList = new LinkedList<>();
        for(Answer answer : repo.findAll()){
            quizList.add(answer.toDTO());
        }
        return new ResponseEntity<>(quizList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{answerPk}")
    public ResponseEntity<AnswerDTO> findOne(@PathVariable Long answerPk ) {
        try {
            AnswerRepository repo = new AnswerRepository();

            AnswerDTO answerDTO = repo.findOne(answerPk).get().toDTO();

            return new ResponseEntity<>(answerDTO, HttpStatus.OK);
        }catch (NoResultException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<AnswerDTO> add(@RequestBody Answer answer) {
        try {
            if(answer.getPk() != null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            AnswerRepository repo = new AnswerRepository();
            answer = repo.save(answer);

            return new ResponseEntity<>(answer.toDTO(), HttpStatus.CREATED);

        }catch (DataIntegrityViolationException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<AnswerDTO> update(@RequestBody Answer answer) {
        try {
            AnswerRepository repo = new AnswerRepository();
            Answer newAnswer = repo.findOne(answer.getPk()).get();

            newAnswer.setPk(answer.getPk());
            newAnswer.setAnswer(answer.getAnswer());
            newAnswer.setRightAnswer(answer.isRightAnswer());

            newAnswer = repo.save(newAnswer);

            return new ResponseEntity<>(newAnswer.toDTO(), HttpStatus.OK);

        }catch (DataIntegrityViolationException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
