package model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by fabiolourenco on 09/09/17.
 */
@Embeddable
public class UserStatistics implements Serializable{
    private static final long serialVersionUID = 1L;

    private long totalRightAnswers;
    private long totalWrongAnswers;
    private long totalQuizzesSolved;
    private long totalQuizzesPassed;

    public UserStatistics() {
    }

    public UserStatistics(long totalRightAnswers, long totalWrongAnswers, long totalQuizzesSolved, long totalQuizzesPassed) {
        this.totalRightAnswers = totalRightAnswers;
        this.totalWrongAnswers = totalWrongAnswers;
        this.totalQuizzesSolved = totalQuizzesSolved;
        this.totalQuizzesPassed = totalQuizzesPassed;
    }

    public long getTotalRightAnswers() {
        return totalRightAnswers;
    }

    public void setTotalRightAnswers(long totalRightAnswers) {
        this.totalRightAnswers = totalRightAnswers;
    }

    public long getTotalWrongAnswers() {
        return totalWrongAnswers;
    }

    public void setTotalWrongAnswers(long totalWrongAnswers) {
        this.totalWrongAnswers = totalWrongAnswers;
    }

    public long getTotalQuizzesSolved() {
        return totalQuizzesSolved;
    }

    public void setTotalQuizzesSolved(long totalQuizzesSolved) {
        this.totalQuizzesSolved = totalQuizzesSolved;
    }

    public long getTotalQuizzesPassed() {
        return totalQuizzesPassed;
    }

    public void setTotalQuizzesPassed(long totalQuizzesPassed) {
        this.totalQuizzesPassed = totalQuizzesPassed;
    }

    public void addWrongAnswers(long value){
        this.totalWrongAnswers += value;
    }

    public void addRightAnswers(long value){
        this.totalRightAnswers += value;
    }

    public void incrementSolvedQuiz(boolean passed){
        this.totalQuizzesSolved++;
        if(passed) this.totalQuizzesPassed++;
    }
}
