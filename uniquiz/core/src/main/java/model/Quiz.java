package model;

import dto.QuestionDTO;
import dto.QuizDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Quiz implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long pk;

    @Version
    private Long version;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "QUIZ_PK")
    private List<Question> questions;

    private Difficulty difficulty;

    private Long subjectPk;
    private String subjectName;

    private Long coursePk;
    private String courseName;

    private String title;

    private long popularityCounter;

    public Quiz() {
        questions = new LinkedList<>();
    }

    public Quiz(Difficulty difficulty, Long subjectPk, String subjectName, Long coursePk, String courseName, String title) {
        this.difficulty = difficulty;
        this.subjectPk = subjectPk;
        this.subjectName = subjectName;
        this.coursePk = coursePk;
        this.courseName = courseName;
        this.title = title;
        this.questions = new LinkedList<>();
    }

    public Long getPk() {
        return pk;
    }

    public void setPk(Long pk) {
        this.pk = pk;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Long getSubjectPk() {
        return subjectPk;
    }

    public void setSubjectPk(Long subjectPk) {
        this.subjectPk = subjectPk;
    }

    public Long getCoursePk() {
        return coursePk;
    }

    public void setCoursePk(Long coursePk) {
        this.coursePk = coursePk;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubjectName(){
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public long getPopularityCounter() {
        return popularityCounter;
    }

    public void setPopularityCounter(long popularityCounter) {
        this.popularityCounter = popularityCounter;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void incrementPopularity(){
        this.popularityCounter++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Quiz quiz = (Quiz) o;

        return pk != null ? pk.equals(quiz.pk) : quiz.pk == null;
    }

    @Override
    public int hashCode() {
        return pk != null ? pk.hashCode() : 0;
    }

    public QuizDTO toDTO(){
        List<QuestionDTO> questions = new LinkedList<>();
        for(Question q : this.questions){
            questions.add(q.toDTO());
        }
        return new QuizDTO(this.pk, questions, difficulty.name(), this.subjectPk, this.subjectName, this.coursePk, this.courseName, this.title, popularityCounter);
    }
}
