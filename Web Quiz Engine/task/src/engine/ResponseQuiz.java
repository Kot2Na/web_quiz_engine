package engine;


import lombok.*;

@Data
public class ResponseQuiz {
    @NonNull private boolean success;
    @NonNull private String feedback;
}
