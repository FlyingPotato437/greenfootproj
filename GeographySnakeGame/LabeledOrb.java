import greenfoot.*;

public class LabeledOrb extends Orb {
    private String displayText;
    
    public LabeledOrb(String stateName, boolean isCorrect, String displayText) {
        super(stateName, isCorrect);
        this.displayText = displayText;
        createLabeledImage();
    }
    
    private void createLabeledImage() {
        GreenfootImage orbImg = new GreenfootImage(16, 16);
        orbImg.setColor(Color.YELLOW);
        orbImg.fillOval(0, 0, 16, 16);
        orbImg.setColor(Color.ORANGE);
        orbImg.drawOval(0, 0, 15, 15);
        
        GreenfootImage textImg = new GreenfootImage(displayText, 14, Color.WHITE, new Color(0,0,0,150));
        
        int totalWidth = Math.max(orbImg.getWidth(), textImg.getWidth()) + 4;
        int totalHeight = orbImg.getHeight() + textImg.getHeight() + 2;
        
        GreenfootImage combined = new GreenfootImage(totalWidth, totalHeight);
        
        int orbX = (totalWidth - orbImg.getWidth()) / 2;
        int textX = (totalWidth - textImg.getWidth()) / 2;
        
        combined.drawImage(orbImg, orbX, 0);
        combined.drawImage(textImg, textX, orbImg.getHeight() + 2);
        
        setImage(combined);
    }
    
    public String getDisplayText() {
        return displayText;
    }
}