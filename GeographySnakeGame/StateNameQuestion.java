import greenfoot.*;
import java.util.*;

public class StateNameQuestion extends Question {
    private List<String> allStates;
    
    public StateNameQuestion(String stateName, List<String> allStates) {
        super(GeographyFacts.getStateFact(stateName), stateName, BASE_POINTS);
        this.allStates = new ArrayList<>(allStates);
        generateChoices();
    }
    
    @Override
    public void generateChoices() {
        choices.clear();
        addChoice(correctAnswer);
        
        List<String> otherStates = new ArrayList<>(allStates);
        otherStates.remove(correctAnswer);
        Collections.shuffle(otherStates);
        
        int numDecoys = Math.min(3, otherStates.size());
        for (int i = 0; i < numDecoys; i++) {
            addChoice(otherStates.get(i));
        }
        
        shuffleChoices();
    }
    
    @Override
    public String getQuestionType() {
        return "State Name";
    }
    
    @Override
    public Color getDisplayColor() {
        return Color.BLUE;
    }
}