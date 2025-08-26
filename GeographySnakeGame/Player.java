import greenfoot.*;

public class Player extends Actor {
    private static final int SPEED = 4;
    private int health;
    private String playerName;
    
    public Player(String name) {
        this.playerName = name;
        this.health = 100;
        updateImage();
    }
    
    public void act() {
        if (getWorld() == null) return;
        handleMovement();
        checkCollisions();
        checkBounds();
    }

    private void handleMovement() {
        if (Greenfoot.isKeyDown("up")) moveVertical(-SPEED);
        if (Greenfoot.isKeyDown("down")) moveVertical(SPEED);
        if (Greenfoot.isKeyDown("left")) moveHorizontal(-SPEED);
        if (Greenfoot.isKeyDown("right")) moveHorizontal(SPEED);
    }
    
    private void moveVertical(int distance) {
        setLocation(getX(), getY() + distance);
    }
    
    private void moveHorizontal(int distance) {
        setLocation(getX() + distance, getY());
    }

    private void checkCollisions() {
        Actor orb = getOneIntersectingObject(Orb.class);
        if (orb != null) {
            ((USWorld)getWorld()).collectOrb((Orb)orb);
        }
    }
    
    private void checkBounds() {
        int x = getX();
        int y = getY();
        World world = getWorld();
        
        if (x < 10) setLocation(10, y);
        if (x > world.getWidth() - 10) setLocation(world.getWidth() - 10, y);
        if (y < 10) setLocation(x, 10);
        if (y > world.getHeight() - 10) setLocation(x, world.getHeight() - 10);
    }
    
    private void updateImage() {
        GreenfootImage img = new GreenfootImage(20, 20);
        img.setColor(Color.CYAN);
        img.fillOval(0, 0, 20, 20);
        img.setColor(Color.DARK_GRAY);
        img.drawOval(0, 0, 19, 19);
        setImage(img);
    }
    
    public String getPlayerName() {
        return playerName;
    }
    
    public int getHealth() {
        return health;
    }
    
    protected void takeDamage(int damage) {
        health = Math.max(0, health - damage);
    }
    
    public boolean isAlive() {
        return health > 0;
    }
}
