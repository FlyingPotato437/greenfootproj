import greenfoot.*;

public class Orb extends Actor {
    private String stateName;
    private boolean isCorrect;
    private int size = 12;
    private long spawnTime;

    public Orb(String stateName, boolean isCorrect) {
        this.stateName = stateName;
        this.isCorrect = isCorrect;
        spawnTime = System.currentTimeMillis();
        updateImage();
    }
    
    public Orb(String stateName) {
        this(stateName, false);
    }

    private void updateImage() {
        GreenfootImage img = new GreenfootImage(size, size);
        img.setColor(Color.YELLOW);
        img.fillOval(0, 0, size, size);
        img.setColor(Color.ORANGE);
        img.drawOval(0, 0, size-1, size-1);
        setImage(img);
        getImage().setTransparency(200);
    }

    public String getStateName() {
        return stateName;
    }
    
    public boolean isCorrectAnswer() {
        return isCorrect;
    }
    
    public long getSpawnTime() {
        return spawnTime;
    }
}
