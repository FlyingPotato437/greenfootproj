import greenfoot.*;
import java.util.*;

public class Snake extends Actor {
    private String snakeName;
    private int energy;
    private int shield;
    private boolean hasSpeedBoost;
    private boolean hasDoublePoints;
    private int speedBoostTimer;
    private int doublePointsTimer;
    private int shieldTimer;
    private List<SnakeSegment> body;
    private int direction;
    private int moveTimer;
    private int moveDelay;
    private static final int MAX_ENERGY = 100;
    
    public Snake(String name) {
        this.snakeName = name;
        this.energy = MAX_ENERGY;
        this.shield = 0;
        this.hasSpeedBoost = false;
        this.hasDoublePoints = false;
        this.body = new ArrayList<>();
        this.direction = 0;
        this.moveTimer = 0;
        this.moveDelay = 15;
        updateVisual();
    }
    
    public void act() {
        if (getWorld() == null) return;
        
        handleInput();
        updatePowerUps();
        moveSnake();
        checkCollisions();
        checkBounds();
        
        if (energy > 25) {
            energy--;
        }
    }
    
    private void handleInput() {
        if (Greenfoot.isKeyDown("up") && direction != 180) {
            direction = 0;
        }
        if (Greenfoot.isKeyDown("down") && direction != 0) {
            direction = 180;
        }
        if (Greenfoot.isKeyDown("left") && direction != 90) {
            direction = 270;
        }
        if (Greenfoot.isKeyDown("right") && direction != 270) {
            direction = 90;
        }
    }
    
    private void moveSnake() {
        moveTimer++;
        int currentDelay = hasSpeedBoost ? moveDelay / 2 : moveDelay;
        
        if (moveTimer >= currentDelay) {
            moveTimer = 0;
            
            int oldX = getX();
            int oldY = getY();
            
            int newX = oldX;
            int newY = oldY;
            
            switch (direction) {
                case 0:   newY -= 10; break;
                case 90:  newX += 10; break;
                case 180: newY += 10; break;
                case 270: newX -= 10; break;
            }
            
            setLocation(newX, newY);
            
            for (int i = body.size() - 1; i >= 0; i--) {
                SnakeSegment segment = body.get(i);
                if (i == 0) {
                    segment.setLocation(oldX, oldY);
                } else {
                    SnakeSegment prevSegment = body.get(i - 1);
                    segment.setLocation(prevSegment.getPrevX(), prevSegment.getPrevY());
                }
                segment.updatePosition();
            }
        }
    }
    
    private void updatePowerUps() {
        if (hasSpeedBoost) {
            speedBoostTimer--;
            if (speedBoostTimer <= 0) hasSpeedBoost = false;
        }
        
        if (hasDoublePoints) {
            doublePointsTimer--;
            if (doublePointsTimer <= 0) hasDoublePoints = false;
        }
        
        if (shield > 0) {
            shieldTimer--;
            if (shieldTimer <= 0) shield--;
        }
        
        updateVisual();
    }
    
    private void checkCollisions() {
        PowerUp power = (PowerUp) getOneIntersectingObject(PowerUp.class);
        if (power != null) {
            activatePowerUp(power);
            getWorld().removeObject(power);
            return;
        }
        
        LabeledOrb labeledOrb = (LabeledOrb) getOneIntersectingObject(LabeledOrb.class);
        if (labeledOrb != null) {
            ((USWorld)getWorld()).collectOrb(labeledOrb);
            return;
        }
        
        Orb orb = (Orb) getOneIntersectingObject(Orb.class);
        if (orb != null) {
            ((USWorld)getWorld()).collectOrb(orb);
            return;
        }
        
        Obstacle obstacle = (Obstacle) getOneIntersectingObject(Obstacle.class);
        if (obstacle != null && !hasShield()) {
            takeDamage(obstacle.getDamage());
            flashDamage();
        }
        
        for (SnakeSegment segment : body) {
            if (Math.abs(getX() - segment.getX()) < 15 && Math.abs(getY() - segment.getY()) < 15) {
                takeDamage(50);
                break;
            }
        }
    }
    
    private void checkBounds() {
        World world = getWorld();
        int x = getX();
        int y = getY();
        
        if (x < 20) setLocation(world.getWidth() - 20, y);
        if (x > world.getWidth() - 20) setLocation(20, y);
        if (y < 20) setLocation(x, world.getHeight() - 20);
        if (y > world.getHeight() - 20) setLocation(x, 20);
    }
    
    public void growSnake() {
        SnakeSegment newSegment = new SnakeSegment();
        getWorld().addObject(newSegment, getX(), getY());
        body.add(newSegment);
    }
    
    private void activatePowerUp(PowerUp power) {
        String type = power.getPowerType();
        switch(type) {
            case "SPEED":
                hasSpeedBoost = true;
                speedBoostTimer = 200;
                break;
            case "SHIELD":
                shield = 3;
                shieldTimer = 300;
                break;
            case "DOUBLE":
                hasDoublePoints = true;
                doublePointsTimer = 150;
                break;
        }
    }
    
    private void updateVisual() {
        GreenfootImage img = new GreenfootImage(20, 20);
        
        if (shield > 0) {
            img.setColor(Color.CYAN);
            img.fillOval(1, 1, 18, 18);
        }
        
        if (hasSpeedBoost) {
            img.setColor(Color.YELLOW);
        } else if (hasDoublePoints) {
            img.setColor(Color.MAGENTA);
        } else {
            img.setColor(Color.GREEN);
        }
        
        img.fillOval(3, 3, 14, 14);
        img.setColor(Color.BLACK);
        img.drawOval(3, 3, 13, 13);
        
        setImage(img);
    }
    
    private void flashDamage() {
        GreenfootImage img = getImage();
        img.setColor(Color.RED);
        img.fillOval(0, 0, 20, 20);
        updateVisual();
    }
    
    public String getSnakeName() { return snakeName; }
    public int getEnergy() { return energy; }
    public boolean hasShield() { return shield > 0; }
    public boolean hasSpeedBoost() { return hasSpeedBoost; }
    public boolean hasDoublePoints() { return hasDoublePoints; }
    
    public void restoreEnergy(int amount) {
        energy = Math.min(MAX_ENERGY, energy + amount);
    }
    
    public void takeDamage(int damage) {
        if (shield > 0) {
            shield--;
            return;
        }
        energy -= damage;
    }
    
    public boolean isAlive() {
        return energy > 0;
    }
}