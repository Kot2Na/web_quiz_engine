package engine;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseQuiz {
    private boolean success;
    private String feedback;
}
