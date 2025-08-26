import greenfoot.*;

public class CollectText extends Actor {
    public void update(String questionText) {
        String displayText = questionText;
        if (displayText.length() > 50) {
            displayText = questionText.substring(0, 47) + "...";
        }
        
        GreenfootImage img = new GreenfootImage(displayText, 18, Color.WHITE, new Color(0,0,100,150));
        setImage(img);
    }
}