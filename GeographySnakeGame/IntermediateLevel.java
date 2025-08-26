import greenfoot.*;
import java.util.*;

public class IntermediateLevel extends GameLevel {
    
    public IntermediateLevel() {
        super("Geography Scholar", 2, 240, Color.BLUE);
    }
    
    @Override
    protected void initializeStates() {
        addStates("Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", 
                 "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", 
                 "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", 
                 "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", 
                 "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", 
                 "New Hampshire", "New Jersey", "New Mexico", "New York", 
                 "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", 
                 "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", 
                 "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", 
                 "West Virginia", "Wisconsin", "Wyoming");
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
        
        int questionType = Greenfoot.getRandomNumber(2);
        switch (questionType) {
            case 0: return new StateNameQuestion(selectedState, availableStates);
            case 1: return new RegionQuestion(selectedState, availableStates);
            default: return new StateNameQuestion(selectedState, availableStates);
        }
    }
    
    @Override
    public int getMaxLives() {
        return 4;
    }
    
    @Override
    public String getLevelDescription() {
        return "Master all 50 states and their regions. Intermediate challenge!";
    }
}