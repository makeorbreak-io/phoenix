import java.util.LinkedList;

public class Quiz {

    private LinkedList<Question> questions;
    private Dificulty dificulty;
    private Subject subject;
    private Course course;

    public Quiz() {
        questions = new LinkedList<>();
    }
}
