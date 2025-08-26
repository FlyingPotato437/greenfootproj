import greenfoot.*;

public class BodySegment extends Actor {
    
    public BodySegment() {
        createVisual();
    }
    
    private void createVisual() {
        GreenfootImage img = new GreenfootImage(15, 15);
        img.setColor(new Color(0, 120, 0));
        img.fillOval(0, 0, 15, 15);
        img.setColor(Color.BLACK);
        img.drawOval(0, 0, 14, 14);
        setImage(img);
    }
}