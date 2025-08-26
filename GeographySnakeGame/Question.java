import greenfoot.*;
import java.util.*;

public abstract class Question {
    protected String questionText;
    protected String correctAnswer;
    protected List<String> choices;
    protected int points;
    protected static final int BASE_POINTS = 10;
    
    protected Question(String questionText, String correctAnswer, int points) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.points = points;
        this.choices = new ArrayList<>();
    }
    
    public abstract void generateChoices();
    public abstract String getQuestionType();
    public abstract Color getDisplayColor();
    
    public final String getQuestionText() {
        return questionText;
    }
    
    public final boolean isCorrect(String answer) {
        return correctAnswer.equalsIgnoreCase(answer.trim());
    }
    
    public final int getPoints() {
        return points;
    }
    
    public final List<String> getChoices() {
        return new ArrayList<>(choices);
    }
    
    public final String getCorrectAnswer() {
        return correctAnswer;
    }
    
    protected final void addChoice(String choice) {
        if (!choices.contains(choice)) {
            choices.add(choice);
        }
    }
    
    protected final void shuffleChoices() {
        Collections.shuffle(choices);
    }
}