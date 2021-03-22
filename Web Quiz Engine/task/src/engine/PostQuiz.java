package engine;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostQuiz {
    private String title;
    private String text;
    private String[] options;
    private int answer;
}
