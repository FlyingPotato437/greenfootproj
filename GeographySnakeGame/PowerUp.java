import greenfoot.*;

public class PowerUp extends Actor {
    private String powerType;
    private int lifespan;
    private Color powerColor;
    
    public PowerUp(String type) {
        this.powerType = type;
        this.lifespan = 300;
        setPowerColor();
        createVisual();
    }
    
    public void act() {
        lifespan--;
        if (lifespan <= 0) {
            getWorld().removeObject(this);
            return;
        }
        
        animate();
    }
    
    private void setPowerColor() {
        switch(powerType) {
            case "SPEED": powerColor = Color.YELLOW; break;
            case "SHIELD": powerColor = Color.CYAN; break;
            case "DOUBLE": powerColor = Color.MAGENTA; break;
            default: powerColor = Color.WHITE;
        }
    }
    
    private void createVisual() {
        GreenfootImage img = new GreenfootImage(16, 16);
        img.setColor(powerColor);
        img.fillRect(0, 0, 16, 16);
        img.setColor(Color.BLACK);
        img.drawRect(0, 0, 15, 15);
        setImage(img);
    }
    
    private void animate() {
        int transparency = 150 + (int)(50 * Math.sin(lifespan * 0.1));
        getImage().setTransparency(transparency);
    }
    
    public String getPowerType() {
        return powerType;
    }
}