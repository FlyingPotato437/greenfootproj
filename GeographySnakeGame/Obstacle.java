import greenfoot.*;

public class Obstacle extends Actor {
    private String obstacleType;
    private int damage;
    private int moveSpeed;
    private int direction;
    private boolean isMoving;
    
    public Obstacle(String type) {
        this.obstacleType = type;
        this.direction = Greenfoot.getRandomNumber(360);
        setupObstacle();
        createVisual();
    }
    
    private void setupObstacle() {
        switch(obstacleType) {
            case "TORNADO":
                damage = 25;
                moveSpeed = 2;
                isMoving = true;
                break;
            case "ROCKSLIDE":
                damage = 15;
                moveSpeed = 0;
                isMoving = false;
                break;
            case "STORM":
                damage = 20;
                moveSpeed = 1;
                isMoving = true;
                break;
            default:
                damage = 10;
                moveSpeed = 1;
                isMoving = false;
        }
    }
    
    public void act() {
        if (isMoving) {
            moveInPattern();
        }
        animate();
        checkBounds();
    }
    
    private void moveInPattern() {
        switch(obstacleType) {
            case "TORNADO":
                spinMove();
                break;
            case "STORM":
                linearMove();
                break;
        }
    }
    
    private void spinMove() {
        direction += 5;
        if (direction >= 360) direction = 0;
        
        int dx = (int)(moveSpeed * Math.cos(Math.toRadians(direction)));
        int dy = (int)(moveSpeed * Math.sin(Math.toRadians(direction)));
        setLocation(getX() + dx, getY() + dy);
    }
    
    private void linearMove() {
        int dx = (int)(moveSpeed * Math.cos(Math.toRadians(direction)));
        int dy = (int)(moveSpeed * Math.sin(Math.toRadians(direction)));
        setLocation(getX() + dx, getY() + dy);
    }
    
    private void animate() {
        setRotation(getRotation() + 3);
    }
    
    private void checkBounds() {
        World world = getWorld();
        if (world == null) return;
        
        if (getX() < -20 || getX() > world.getWidth() + 20 || 
            getY() < -20 || getY() > world.getHeight() + 20) {
            
            if (isMoving) {
                direction += 180;
                if (direction >= 360) direction -= 360;
                
                int newX = Math.max(20, Math.min(world.getWidth() - 20, getX()));
                int newY = Math.max(20, Math.min(world.getHeight() - 20, getY()));
                setLocation(newX, newY);
            }
        }
    }
    
    private void createVisual() {
        GreenfootImage img = new GreenfootImage(18, 18);
        
        switch(obstacleType) {
            case "TORNADO":
                img.setColor(Color.GRAY);
                img.fillOval(0, 0, 18, 18);
                img.setColor(Color.WHITE);
                img.fillOval(3, 3, 12, 12);
                break;
            case "ROCKSLIDE":
                img.setColor(new Color(139, 69, 19));
                img.fillRect(0, 0, 18, 18);
                img.setColor(Color.BLACK);
                img.drawRect(0, 0, 17, 17);
                break;
            case "STORM":
                img.setColor(Color.DARK_GRAY);
                img.fillOval(0, 0, 18, 18);
                img.setColor(Color.BLUE);
                img.fillOval(4, 4, 10, 10);
                break;
        }
        
        setImage(img);
    }
    
    public int getDamage() { return damage; }
    public String getObstacleType() { return obstacleType; }
}