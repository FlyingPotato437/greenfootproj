import greenfoot.*;
import java.util.*;

public class CapitalQuestion extends Question {
    private static final Map<String, String> STATE_CAPITALS = createCapitalsMap();
    private List<String> allStates;
    
    public CapitalQuestion(String stateName, List<String> allStates) {
        super(GeographyFacts.getCapitalHint(stateName) + " (Capital of " + stateName + ")", 
              STATE_CAPITALS.get(stateName), BASE_POINTS + 5);
        this.allStates = new ArrayList<>(allStates);
        generateChoices();
    }
    
    @Override
    public void generateChoices() {
        choices.clear();
        addChoice(correctAnswer);
        
        List<String> otherCapitals = new ArrayList<>();
        for (String state : allStates) {
            if (!state.equals(getStateFromCapital(correctAnswer))) {
                String capital = STATE_CAPITALS.get(state);
                if (capital != null && !capital.equals(correctAnswer)) {
                    otherCapitals.add(capital);
                }
            }
        }
        
        Collections.shuffle(otherCapitals);
        int numDecoys = Math.min(3, otherCapitals.size());
        for (int i = 0; i < numDecoys; i++) {
            addChoice(otherCapitals.get(i));
        }
        
        shuffleChoices();
    }
    
    @Override
    public String getQuestionType() {
        return "State Capital";
    }
    
    @Override
    public Color getDisplayColor() {
        return Color.GREEN;
    }
    
    private String getStateFromCapital(String capital) {
        for (Map.Entry<String, String> entry : STATE_CAPITALS.entrySet()) {
            if (entry.getValue().equals(capital)) {
                return entry.getKey();
            }
        }
        return null;
    }
    
    private static Map<String, String> createCapitalsMap() {
        Map<String, String> capitals = new HashMap<>();
        capitals.put("Alabama", "Montgomery");
        capitals.put("Alaska", "Juneau");
        capitals.put("Arizona", "Phoenix");
        capitals.put("Arkansas", "Little Rock");
        capitals.put("California", "Sacramento");
        capitals.put("Colorado", "Denver");
        capitals.put("Connecticut", "Hartford");
        capitals.put("Delaware", "Dover");
        capitals.put("Florida", "Tallahassee");
        capitals.put("Georgia", "Atlanta");
        capitals.put("Hawaii", "Honolulu");
        capitals.put("Idaho", "Boise");
        capitals.put("Illinois", "Springfield");
        capitals.put("Indiana", "Indianapolis");
        capitals.put("Iowa", "Des Moines");
        capitals.put("Kansas", "Topeka");
        capitals.put("Kentucky", "Frankfort");
        capitals.put("Louisiana", "Baton Rouge");
        capitals.put("Maine", "Augusta");
        capitals.put("Maryland", "Annapolis");
        capitals.put("Massachusetts", "Boston");
        capitals.put("Michigan", "Lansing");
        capitals.put("Minnesota", "Saint Paul");
        capitals.put("Mississippi", "Jackson");
        capitals.put("Missouri", "Jefferson City");
        capitals.put("Montana", "Helena");
        capitals.put("Nebraska", "Lincoln");
        capitals.put("Nevada", "Carson City");
        capitals.put("New Hampshire", "Concord");
        capitals.put("New Jersey", "Trenton");
        capitals.put("New Mexico", "Santa Fe");
        capitals.put("New York", "Albany");
        capitals.put("North Carolina", "Raleigh");
        capitals.put("North Dakota", "Bismarck");
        capitals.put("Ohio", "Columbus");
        capitals.put("Oklahoma", "Oklahoma City");
        capitals.put("Oregon", "Salem");
        capitals.put("Pennsylvania", "Harrisburg");
        capitals.put("Rhode Island", "Providence");
        capitals.put("South Carolina", "Columbia");
        capitals.put("South Dakota", "Pierre");
        capitals.put("Tennessee", "Nashville");
        capitals.put("Texas", "Austin");
        capitals.put("Utah", "Salt Lake City");
        capitals.put("Vermont", "Montpelier");
        capitals.put("Virginia", "Richmond");
        capitals.put("Washington", "Olympia");
        capitals.put("West Virginia", "Charleston");
        capitals.put("Wisconsin", "Madison");
        capitals.put("Wyoming", "Cheyenne");
        return capitals;
    }
}