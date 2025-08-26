import greenfoot.*;

public class HintDisplay extends Actor {
    private String currentHint;
    private int displayTimer;
    
    public HintDisplay() {
        currentHint = "Welcome to GeographyQuest!";
        displayTimer = 0;
        updateDisplay();
    }
    
    public void act() {
        if (displayTimer > 0) {
            displayTimer--;
            if (displayTimer <= 0) {
                hideHint();
            }
        }
    }
    
    public void showHint(String hint, int duration) {
        currentHint = hint;
        displayTimer = duration;
        updateDisplay();
        getImage().setTransparency(255);
    }
    
    public void showEducationalHint(Question question) {
        String hint = "";
        
        if (question instanceof StateNameQuestion) {
            String stateName = question.getCorrectAnswer();
            hint = GeographyFacts.getStateFact(stateName) + "\n" + 
                   GeographyFacts.getLocationClue(stateName);
        } 
        else if (question instanceof CapitalQuestion) {
            String questionText = question.getQuestionText();
            String stateName = questionText.substring(questionText.indexOf("of ") + 3, questionText.indexOf("?"));
            hint = GeographyFacts.getCapitalHint(stateName) + "\n" +
                   "Capital of " + stateName;
        }
        else if (question instanceof RegionQuestion) {
            String questionText = question.getQuestionText();
            String stateName = questionText.substring(questionText.indexOf("is ") + 3, questionText.indexOf(" in"));
            hint = GeographyFacts.getLocationClue(stateName) + "\n" +
                   GeographyFacts.getRegionHint(question.getCorrectAnswer());
        }
        
        showHint(hint, 600);
    }
    
    public void showQuickFact(String stateName) {
        String fact = GeographyFacts.getStateFact(stateName);
        showHint("✓ CORRECT! " + fact, 180);
    }
    
    private void hideHint() {
        currentHint = "";
        updateDisplay();
        getImage().setTransparency(0);
    }
    
    private void updateDisplay() {
        if (currentHint.isEmpty()) {
            setImage(new GreenfootImage(1, 1));
            return;
        }
        
        String[] lines = currentHint.split("\n");
        int maxWidth = 0;
        int lineHeight = 16;
        int totalHeight = lines.length * lineHeight + 10;
        
        for (String line : lines) {
            GreenfootImage tempImg = new GreenfootImage(line, 14, Color.WHITE, new Color(0,0,0,0));
            maxWidth = Math.max(maxWidth, tempImg.getWidth());
        }
        
        maxWidth += 20;
        
        GreenfootImage img = new GreenfootImage(maxWidth, totalHeight);
        img.setColor(new Color(0, 50, 100, 220));
        img.fill();
        img.setColor(Color.CYAN);
        img.drawRect(0, 0, maxWidth-1, totalHeight-1);
        
        for (int i = 0; i < lines.length; i++) {
            GreenfootImage lineImg = new GreenfootImage(lines[i], 14, Color.WHITE, new Color(0,0,0,0));
            int x = (maxWidth - lineImg.getWidth()) / 2;
            int y = 5 + i * lineHeight;
            img.drawImage(lineImg, x, y);
        }
        
        setImage(img);
    }
}