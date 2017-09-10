package repositories;

import model.Course;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Rafael Santos on 08-09-2017.
 */
public class CourseRepository extends BaseRepository<Course, Long> {
    public List<Course> findByField(Long fieldPk){
        Map<String, Object> m = new HashMap<>();
        m.put("a", fieldPk);
        return match("e.fieldPk=:a", m);
    }
}
