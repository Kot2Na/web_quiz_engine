package engine;

import org.apache.tomcat.util.digester.ArrayStack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;


@RestController
public class QuizRestController {
    //private Map<Integer, Quiz> map = new HashMap<Integer, Quiz>();
    @Autowired
    private QuizRepository quizRepository;

    @PostMapping("/api/quizzes")
    public Quiz putQuiz(@Valid @RequestBody Quiz quiz) {
        Quiz newQuiz;
        System.out.println(this.quizRepository.equals(null));
        if (quiz.getAnswer() != null) {
            newQuiz = new Quiz((int) this.quizRepository.count() + 1, quiz.getTitle(), quiz.getText(), quiz.getOptions(), quiz.getAnswer());
        }
        else {
            newQuiz = new Quiz((int) this.quizRepository.count() + 1, quiz.getTitle(), quiz.getText(), quiz.getOptions(), new Integer[]{});
        }
        this.quizRepository.save(newQuiz);

        //this.map.put(newQuiz.getId(), newQuiz);

        return newQuiz;
    }

    @GetMapping("/api/quizzes/{id}")
    public Quiz getQuiz(@PathVariable int id) {
        //if (this.map.containsKey(id)) {
        if (quizRepository.existsById(id)) {
            Quiz quiz = quizRepository.findById(id).get();
            //Quiz quiz = this.map.get(id);
            Quiz returnQuiz = new Quiz(id, quiz.getTitle(), quiz.getText(), quiz.getOptions(), quiz.getAnswer());
            return returnQuiz;
        } else {
            throw new QuizNotFound();
        }
    }

    @GetMapping("/api/quizzes")
    public ArrayList<Quiz> getQuizzes() {
        ArrayList<Quiz> quizArr = new ArrayList<Quiz>();

        quizRepository.findAll().forEach(value -> quizArr.add(new Quiz(value.getId(), value.getTitle(), value.getText(), value.getOptions(), value.getAnswer())));
        //this.map.forEach((key, value) -> quizArr.add(new Quiz(key, value.getTitle(), value.getText(), value.getOptions(), value.getAnswer())));
        return quizArr;
    }

    @PostMapping("/api/quizzes/{id}/solve")
    public ResponseQuiz checkAnswerQuiz(@RequestBody Answer answer, @PathVariable int id) {
        if (quizRepository.existsById(id)) {
            if (Set.of(quizRepository.findById(id).get().getAnswer()).equals(Set.of(answer.getAnswer()))) {
                return new ResponseQuiz(true, "Congratulations, you're right!");
            } else {
                return new ResponseQuiz(false, "Wrong answer! Please, try again.");
            }
        } else {
            throw new QuizNotFound();
        }

        /*
        if (this.map.containsKey(id)) {
            if (Set.of(this.map.get(id).getAnswer()).equals(Set.of(answer.getAnswer()))) {
                return new ResponseQuiz(true, "Congratulations, you're right!");
            } else {
                return new ResponseQuiz(false, "Wrong answer! Please, try again.");
            }
        } else  {
            throw new QuizNotFound();
        }
        */
    }

    @ExceptionHandler(QuizNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleException() {
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleValidationExceptions() {
    }

    public void setQuizRepository(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }
}
