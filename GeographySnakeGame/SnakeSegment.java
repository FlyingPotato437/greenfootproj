import greenfoot.*;

public class SnakeSegment extends Actor {
    private int prevX;
    private int prevY;
    
    public SnakeSegment() {
        createVisual();
    }
    
    public void updatePosition() {
        prevX = getX();
        prevY = getY();
    }
    
    private void createVisual() {
        GreenfootImage img = new GreenfootImage(16, 16);
        img.setColor(new Color(0, 150, 0));
        img.fillOval(0, 0, 16, 16);
        img.setColor(Color.BLACK);
        img.drawOval(0, 0, 15, 15);
        setImage(img);
    }
    
    public int getPrevX() { return prevX; }
    public int getPrevY() { return prevY; }
}