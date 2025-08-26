import greenfoot.*;
import java.util.*;

public class BeginnerLevel extends GameLevel {
    
    public BeginnerLevel() {
        super("Beginner Explorer", 1, 300, Color.GREEN);
    }
    
    @Override
    protected void initializeStates() {
        addStates("California", "Texas", "Florida", "New York", "Illinois", 
                 "Pennsylvania", "Ohio", "Georgia", "North Carolina", "Michigan",
                 "New Jersey", "Virginia", "Washington", "Arizona", "Massachusetts");
    }
    
    @Override
    public Question generateQuestion(List<String> remainingStates) {
        List<String> levelStates = new ArrayList<>();
        for (String state : remainingStates) {
            if (availableStates.contains(state)) {
                levelStates.add(state);
            }
        }
        
        if (levelStates.isEmpty()) return null;
        
        String selectedState = levelStates.get(Greenfoot.getRandomNumber(levelStates.size()));
        return new StateNameQuestion(selectedState, availableStates);
    }
    
    @Override
    public int getMaxLives() {
        return 5;
    }
    
    @Override
    public String getLevelDescription() {
        return "Learn the names and locations of major US states. Perfect for beginners!";
    }
}