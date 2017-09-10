import model.*;
import repositories.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by fabiolourenco on 09/09/17.
 */
public class DemoBootstrap {

    public static void main(String[] args) {
        new DemoBootstrap().execute();
    }

    public void execute() {

        //CREATE USERS
        UserRepository userRepository = new UserRepository();

        userRepository.save(new User("test", "test", "test", "phoenixmakeorbreak@test.com"));


        //CREATE FIELDS
        FieldRepository fieldRepository = new FieldRepository();

        Field field1 = new Field("Engenharia");
        Field field2 = new Field("Ciências");
        Field field3 = new Field("Medicina");
        Field field4 = new Field("Economia");
        Field field5 = new Field("Agricultura");
        Field field6 = new Field("Artes");
        Field field7 = new Field("Educação");
        Field field8 = new Field("Direito");
        Field field9 = new Field("Humanidades");
        Field field10 = new Field("Desporto");

        field1 = fieldRepository.save(field1);
        field2 = fieldRepository.save(field2);
        field3 = fieldRepository.save(field3);
        field4 = fieldRepository.save(field4);
        field5 = fieldRepository.save(field5);
        field6 = fieldRepository.save(field6);
        field7 = fieldRepository.save(field7);
        field8 = fieldRepository.save(field8);
        field9 = fieldRepository.save(field9);
        field10 = fieldRepository.save(field10);


        //CREATE COURSE
        CourseRepository courseRepository = new CourseRepository();

        Course course1 = new Course("Informática", field1.getFieldName(), field1.getPk());
        Course course2 = new Course("Civil", field1.getFieldName(), field1.getPk());
        Course course3 = new Course("Mecânica", field1.getFieldName(), field1.getPk());

        Course course4 = new Course("Biologia", field2.getFieldName(), field2.getPk());
        Course course5 = new Course("Física", field2.getFieldName(), field2.getPk());
        Course course6 = new Course("Química", field2.getFieldName(), field2.getPk());

        Course course7 = new Course("Enfermagem", field3.getFieldName(), field3.getPk());
        Course course8 = new Course("Fisioterapia", field3.getFieldName(), field3.getPk());
        Course course9 = new Course("Medicina", field3.getFieldName(), field3.getPk());

        Course course10 = new Course("Contabilidade", field4.getFieldName(), field4.getPk());
        Course course11 = new Course("Economia", field4.getFieldName(), field4.getPk());
        Course course12 = new Course("Finanças", field4.getFieldName(), field4.getPk());

        course1 = courseRepository.save(course1);
        course2 = courseRepository.save(course2);
        course3 = courseRepository.save(course3);
        course4 = courseRepository.save(course4);
        course5 = courseRepository.save(course5);
        course6 = courseRepository.save(course6);
        course7 = courseRepository.save(course7);
        course8 = courseRepository.save(course8);
        course9 = courseRepository.save(course9);
        course10 = courseRepository.save(course10);
        course11 = courseRepository.save(course11);
        course12 = courseRepository.save(course12);


        //CREATE SUBJECTS
        SubjectRepository subjectRepository = new SubjectRepository();

        Subject subject1 = new Subject("Gestão");
        Subject subject2 = new Subject("Álgebra");
        Subject subject3 = new Subject("Grafos");
        Subject subject4 = new Subject("Design de Software");
        Subject subject5 = new Subject("Princípios da Computação");

        subject1 = subjectRepository.save(subject1);
        subject2 = subjectRepository.save(subject2);
        subject3 = subjectRepository.save(subject3);
        subject4 = subjectRepository.save(subject4);
        subject5 = subjectRepository.save(subject5);


        //CREATE QUIZZES
        QuizRepository quizRepository = new QuizRepository();

        Quiz quiz = new Quiz(Difficulty.NORMAL, subject5.getPk(), subject5.getSubjectName(),
                course1.getPk(), course1.getCourseName(), "Princípios da computação I");

        List<Question> questionList = new ArrayList<>();

        Question question1 = new Question("Durante a execução de um processo o que pode acontecer?");

        List<Answer> answerList1 = new LinkedList<>();
        answerList1.add(new Answer("O processo emitir um pedido IO, e consequentemente ser colocado numa fila de um IO device, passando para o estado de ready.", false));
        answerList1.add(new Answer("O tempo que o escalonador tinha atribuÌdo ao processo (time slice) terminar e consequentemente ser colocado na fila dos ready.", true));
        answerList1.add(new Answer("O processo pode criar um novo processo, ficando à espera que ele termine e consequentemente ser colocado na fila dos ready.", false));
        answerList1.add(new Answer("O processo pode ser removido da UCP em consequÍncia duma interrupção passando sempre para o estado de waiting.", false));

        question1.setAnswers(answerList1);
        questionList.add(question1);

        Question question2 = new Question("Relativamente aos métodos de escalonamento FCFS e Round-Robin pode-se dizer que:");

        List<Answer> answerList2 = new LinkedList<>();
        answerList2.add(new Answer("O Round-Robin favorece a multiprogramação.", true));
        answerList2.add(new Answer("Favorecem os processos IOBound.", false));
        answerList2.add(new Answer("O FCFS tem uma implementação mais complexa.", false));
        answerList2.add(new Answer("O Round-Robin favorece os processos mais prioritários.", false));

        question2.setAnswers(answerList2);
        questionList.add(question2);

        quiz.setQuestions(questionList);

        quizRepository.save(quiz);
    }
}
