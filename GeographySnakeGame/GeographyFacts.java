import java.util.*;

public class GeographyFacts {
    private static final Map<String, String> STATE_FACTS = createStateFacts();
    private static final Map<String, String> CAPITAL_HINTS = createCapitalHints();
    private static final Map<String, String> LOCATION_CLUES = createLocationClues();
    
    public static String getStateFact(String stateName) {
        return STATE_FACTS.getOrDefault(stateName, "This is " + stateName);
    }
    
    public static String getCapitalHint(String stateName) {
        return CAPITAL_HINTS.getOrDefault(stateName, "Think about the capital city");
    }
    
    public static String getLocationClue(String stateName) {
        return LOCATION_CLUES.getOrDefault(stateName, "Look at the map location");
    }
    
    private static Map<String, String> createStateFacts() {
        Map<String, String> facts = new HashMap<>();
        facts.put("California", "HINT: The Golden State - largest by population");
        facts.put("Texas", "HINT: The Lone Star State - second largest");
        facts.put("Florida", "HINT: The Sunshine State - southeastern peninsula");
        facts.put("New York", "HINT: The Empire State - contains NYC");
        facts.put("Alaska", "HINT: The Last Frontier - largest state, bought from Russia");
        facts.put("Hawaii", "HINT: The Aloha State - island chain in Pacific");
        facts.put("Colorado", "HINT: The Centennial State - Rocky Mountains");
        facts.put("Nevada", "HINT: The Silver State - desert with Las Vegas");
        facts.put("Maine", "HINT: The Pine Tree State - northeastern lobster state");
        facts.put("Georgia", "HINT: The Peach State - southeastern state");
        facts.put("Illinois", "HINT: The Prairie State - home to Chicago");
        facts.put("Michigan", "HINT: The Great Lakes State - shaped like a mitten");
        facts.put("Ohio", "HINT: The Buckeye State - heart of America");
        facts.put("Pennsylvania", "HINT: The Keystone State - Liberty Bell location");
        facts.put("Virginia", "HINT: The Old Dominion - first colony");
        facts.put("Washington", "HINT: The Evergreen State - Pacific Northwest");
        facts.put("Arizona", "HINT: The Grand Canyon State - desert southwest");
        facts.put("Louisiana", "HINT: The Pelican State - Mississippi River delta");
        facts.put("Montana", "HINT: Big Sky Country - northern plains");
        facts.put("Wyoming", "HINT: The Cowboy State - Yellowstone location");
        return facts;
    }
    
    private static Map<String, String> createCapitalHints() {
        Map<String, String> hints = new HashMap<>();
        hints.put("California", "HINT: Not LA or SF, but starts with 'S'");
        hints.put("Texas", "HINT: Keep it weird! Music city of the south");
        hints.put("New York", "HINT: Not NYC - upstate city on Hudson River");
        hints.put("Illinois", "HINT: Not Chicago - named after president");
        hints.put("Florida", "HINT: Not Miami - sounds like 'tall has sea'");
        hints.put("Washington", "HINT: Not Seattle - named after Greek mountain");
        hints.put("Pennsylvania", "HINT: Not Philadelphia - means 'Harris's town'");
        hints.put("Michigan", "HINT: Not Detroit - sounds like 'landing'");
        hints.put("Alaska", "HINT: Not Anchorage - French for 'young'");
        hints.put("Nevada", "HINT: Not Las Vegas - named after Kit Carson");
        hints.put("Colorado", "HINT: Mile High City");
        hints.put("Arizona", "HINT: Valley of the Sun");
        hints.put("Georgia", "HINT: Hotlanta's real name");
        hints.put("Louisiana", "HINT: Red stick in French");
        hints.put("Montana", "HINT: Sounds like 'hell in a'");
        hints.put("Maine", "HINT: August is a month and a city");
        return hints;
    }
    
    private static Map<String, String> createLocationClues() {
        Map<String, String> clues = new HashMap<>();
        clues.put("California", "CLUE: West coast, touches Pacific Ocean");
        clues.put("Florida", "CLUE: Southeast peninsula, touches both oceans");
        clues.put("Texas", "CLUE: Large southern state, touches Mexico");
        clues.put("Alaska", "CLUE: Northwest, separated from mainland");
        clues.put("Hawaii", "CLUE: Islands in Pacific, far from mainland");
        clues.put("Maine", "CLUE: Northeast corner, touches Atlantic");
        clues.put("Washington", "CLUE: Northwest corner, touches Pacific");
        clues.put("Montana", "CLUE: Northern border, touches Canada");
        clues.put("North Dakota", "CLUE: Northern plains, touches Canada");
        clues.put("Minnesota", "CLUE: Northern, land of 10,000 lakes");
        clues.put("Wisconsin", "CLUE: Great Lakes region, cheese state");
        clues.put("Michigan", "CLUE: Great Lakes, two peninsulas");
        clues.put("New York", "CLUE: Northeast, touches Great Lakes");
        clues.put("Vermont", "CLUE: Northeast, Green Mountains");
        clues.put("New Hampshire", "CLUE: Northeast, White Mountains");
        clues.put("Massachusetts", "CLUE: Northeast coast, Cape Cod");
        return clues;
    }
    
    public static String getRegionHint(String regionName) {
        switch(regionName) {
            case "Northeast":
                return "HINT: Original 13 colonies area - cold winters, fall colors";
            case "Southeast":
                return "HINT: Warm climate, humid summers, historical plantations";
            case "Midwest":
                return "HINT: Great Plains, corn belt, Great Lakes region";
            case "Southwest":
                return "HINT: Desert climate, Spanish influence, oil and cattle";
            case "West":
                return "HINT: Mountains, Pacific coast, gold rush history";
            default:
                return "Think about climate and geography";
        }
    }
}