package repositories;

import model.Quiz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Rafael Santos on 08-09-2017.
 */
public class QuizRepository extends BaseRepository<Quiz, Long> {

    public List<Quiz> findByTitle(String title){
        Map<String, Object> m = new HashMap<>();
        m.put("a", "%"+title+"%");
        return match("e.title LIKE :a", m);
    }

    public List<Quiz> findBySubject(Long subjectPk){
        Map<String, Object> m = new HashMap<>();
        m.put("a", subjectPk);
        return match("e.subjectPk=:a", m);
    }
}
