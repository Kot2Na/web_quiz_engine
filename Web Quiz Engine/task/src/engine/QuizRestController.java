package engine;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class QuizRestController {
    @GetMapping("/api/quiz")
    public Quiz getQuiz() {
        String[] options = new String[] {"Robot","Tea leaf","Cup of coffee","Bug"};

        return new Quiz("The Java Logo", "What is depicted on the Java logo?", options);
    }

    @PostMapping("/api/quiz")
    public ResponseQuiz checkAnswerQuiz(@RequestParam int answer) {
        if (answer == 2) {
            return new ResponseQuiz(true, "Congratulations, you're right!");
        } else {
            return new ResponseQuiz(false, "Wrong answer! Please, try again.");
        }
    }
}
