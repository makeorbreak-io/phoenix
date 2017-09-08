package controller;

import dto.QuizDTO;
import model.Quiz;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repositories.QuizRepository;

import javax.persistence.NoResultException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by fabiolourenco on 08/09/17.
 */
@RestController
@RequestMapping("/quizzes")
public class QuizController {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<QuizDTO>> findAll(){
        try{
            QuizRepository repo = new QuizRepository();

            List<QuizDTO> quizList = new LinkedList<>();
            for(Quiz quiz : repo.findAll()){
                quizList.add(quiz.toDTO());
            }
            return new ResponseEntity<>(quizList, HttpStatus.OK);
        }catch (NoResultException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{quizPk}")
    public ResponseEntity<QuizDTO> findOne(@PathVariable Long quizPk ) {
        try {
            QuizRepository repo = new QuizRepository();

            QuizDTO quiz = repo.findOne(quizPk).get().toDTO();

            return new ResponseEntity<>(quiz, HttpStatus.OK);
        }catch (NoResultException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<QuizDTO> add(@RequestBody Quiz quiz) {
        try {
            QuizRepository repo = new QuizRepository();
            quiz = repo.save(quiz);

            return new ResponseEntity<>(quiz.toDTO(), HttpStatus.CREATED);

        }catch (DataIntegrityViolationException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<QuizDTO> update(@RequestBody Quiz quiz) {
        try {
            QuizRepository repo = new QuizRepository();
            quiz = repo.save(quiz);

            return new ResponseEntity<>(quiz.toDTO(), HttpStatus.OK);

        }catch (DataIntegrityViolationException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
