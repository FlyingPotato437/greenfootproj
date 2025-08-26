import greenfoot.*;
import java.util.*;

public class RegionQuestion extends Question {
    private static final Map<String, String> STATE_REGIONS = createRegionsMap();
    private static final List<String> ALL_REGIONS = Arrays.asList(
        "Northeast", "Southeast", "Midwest", "Southwest", "West"
    );
    
    public RegionQuestion(String stateName, List<String> allStates) {
        super(GeographyFacts.getLocationClue(stateName) + " (Which region?)", 
              STATE_REGIONS.get(stateName), BASE_POINTS + 3);
        generateChoices();
    }
    
    @Override
    public void generateChoices() {
        choices.clear();
        addChoice(correctAnswer);
        
        for (String region : ALL_REGIONS) {
            if (!region.equals(correctAnswer)) {
                addChoice(region);
            }
        }
        
        shuffleChoices();
    }
    
    @Override
    public String getQuestionType() {
        return "Geographic Region";
    }
    
    @Override
    public Color getDisplayColor() {
        return Color.ORANGE;
    }
    
    private static Map<String, String> createRegionsMap() {
        Map<String, String> regions = new HashMap<>();
        
        String[] northeast = {"Connecticut", "Maine", "Massachusetts", "New Hampshire", 
                             "Rhode Island", "Vermont", "New Jersey", "New York", "Pennsylvania"};
        String[] southeast = {"Delaware", "Florida", "Georgia", "Maryland", "North Carolina", 
                             "South Carolina", "Virginia", "West Virginia", "Kentucky", 
                             "Tennessee", "Alabama", "Mississippi", "Arkansas", "Louisiana"};
        String[] midwest = {"Illinois", "Indiana", "Iowa", "Kansas", "Michigan", "Minnesota", 
                           "Missouri", "Nebraska", "North Dakota", "Ohio", "South Dakota", "Wisconsin"};
        String[] southwest = {"Arizona", "New Mexico", "Oklahoma", "Texas"};
        String[] west = {"Alaska", "California", "Colorado", "Hawaii", "Idaho", "Montana", 
                        "Nevada", "Oregon", "Utah", "Washington", "Wyoming"};
        
        for (String state : northeast) regions.put(state, "Northeast");
        for (String state : southeast) regions.put(state, "Southeast");
        for (String state : midwest) regions.put(state, "Midwest");
        for (String state : southwest) regions.put(state, "Southwest");
        for (String state : west) regions.put(state, "West");
        
        return regions;
    }
}