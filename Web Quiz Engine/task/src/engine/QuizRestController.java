package engine;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@RestController
public class QuizRestController {
    private Map<Integer, PostQuiz> map = new HashMap<Integer, PostQuiz>();

    @PostMapping("/api/quizzes")
    public Quiz putQuiz(@RequestBody PostQuiz quiz) {
        Quiz newQuiz = new Quiz(map.size() + 1, quiz.getTitle(), quiz.getText(), quiz.getOptions());
        this.map.put(newQuiz.getId(), quiz);

        return newQuiz;
    }

    @GetMapping("/api/quizzes/{id}")
    public Quiz getQuiz(@PathVariable int id) {
        if (this.map.containsKey(id)) {
            PostQuiz quiz = this.map.get(id);
            Quiz returnQuiz = new Quiz(id, quiz.getTitle(), quiz.getText(), quiz.getOptions());
            return returnQuiz;
        } else {
            throw new QuizNotFound();
        }
    }

    @GetMapping("/api/quizzes")
    public ArrayList<Quiz> getQuizzes() {
        ArrayList<Quiz> quizArr = new ArrayList<Quiz>();

        this.map.forEach((key, value) -> quizArr.add(new Quiz(key, value.getTitle(), value.getText(), value.getOptions())));
        return quizArr;
    }

    @PostMapping("/api/quizzes/{id}/solve")
    public ResponseQuiz checkAnswerQuiz(@PathVariable int id, @RequestParam int answer ) {
        if (this.map.containsKey(id)) {
            if (this.map.get(id).getAnswer() == answer) {
                return new ResponseQuiz(true, "Congratulations, you're right!");
            } else {
                return new ResponseQuiz(false, "Wrong answer! Please, try again.");
            }
        } else  {
            throw new QuizNotFound();
        }
    }

    @ExceptionHandler(QuizNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleException() {
    }
}
