package engine;

public class ResponseQuiz {
    private boolean success;
    private String feedback;

    public ResponseQuiz (boolean success, String feedback) {
        this.success = success;
        this.feedback = feedback;
    }

    public boolean getSuccess() {
        return success;
    }
    public String getFeedback() {
        return feedback;
    }
}
