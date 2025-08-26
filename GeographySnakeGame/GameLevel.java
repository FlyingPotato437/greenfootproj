import greenfoot.*;
import java.util.*;

public abstract class GameLevel {
    protected String levelName;
    protected int difficultyMultiplier;
    protected int timeLimit;
    protected Color levelColor;
    protected List<String> availableStates;
    
    protected GameLevel(String levelName, int difficultyMultiplier, int timeLimit, Color levelColor) {
        this.levelName = levelName;
        this.difficultyMultiplier = difficultyMultiplier;
        this.timeLimit = timeLimit;
        this.levelColor = levelColor;
        this.availableStates = new ArrayList<>();
        initializeStates();
    }
    
    protected abstract void initializeStates();
    public abstract Question generateQuestion(List<String> remainingStates);
    public abstract int getMaxLives();
    public abstract String getLevelDescription();
    
    public final String getLevelName() {
        return levelName;
    }
    
    public final int getDifficultyMultiplier() {
        return difficultyMultiplier;
    }
    
    public final int getTimeLimit() {
        return timeLimit;
    }
    
    public final Color getLevelColor() {
        return levelColor;
    }
    
    public final List<String> getAvailableStates() {
        return new ArrayList<>(availableStates);
    }
    
    protected final void addState(String stateName) {
        if (!availableStates.contains(stateName)) {
            availableStates.add(stateName);
        }
    }
    
    protected final void addStates(String... stateNames) {
        for (String state : stateNames) {
            addState(state);
        }
    }
}