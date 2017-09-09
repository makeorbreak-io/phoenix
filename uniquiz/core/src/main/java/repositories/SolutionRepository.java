package repositories;

import model.Solution;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Rafael Santos on 08-09-2017.
 */
public class SolutionRepository extends BaseRepository<Solution, Long> {

    public Solution findLatestByQuizAndEmail(Long quizPk, String email){
        Map<String, Object> m = new HashMap<>();
        m.put("a", quizPk);
        m.put("b", email);
        List<Solution> solutionList = match("e.quizPk=:a AND e.email=:b", m);

        Solution latestSolution = solutionList.get(0);

        for(Solution solution : solutionList){
            if(solution.getSolvedOn().isAfter(latestSolution.getSolvedOn())){
                latestSolution = solution;
            }
        }

        return latestSolution;
    }

}
