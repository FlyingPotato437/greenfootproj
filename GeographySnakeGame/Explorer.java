import greenfoot.*;
import java.util.*;

public class Explorer extends Actor {
    private String explorerName;
    private int energy;
    private int shield;
    private boolean hasSpeedBoost;
    private boolean hasDoublePoints;
    private int speedBoostTimer;
    private int doublePointsTimer;
    private int shieldTimer;
    private List<BodySegment> bodySegments;
    private List<int[]> positions;
    private static final int MAX_ENERGY = 100;
    
    public Explorer(String name) {
        this.explorerName = name;
        this.energy = MAX_ENERGY;
        this.shield = 0;
        this.hasSpeedBoost = false;
        this.hasDoublePoints = false;
        this.bodySegments = new ArrayList<>();
        this.positions = new ArrayList<>();
        updateVisual();
    }
    
    public void act() {
        if (getWorld() == null) return;
        
        handleInput();
        updatePowerUps();
        checkCollisions();
        manageBounds();
        if (energy > 25) {
            energy--;
        }
    }
    
    private int direction = 90;
    private int moveCounter = 0;
    private static final int GRID_SIZE = 20;
    
    private void handleInput() {
        if (Greenfoot.isKeyDown("up") && direction != 180) direction = 0;
        if (Greenfoot.isKeyDown("down") && direction != 0) direction = 180;
        if (Greenfoot.isKeyDown("left") && direction != 90) direction = 270;
        if (Greenfoot.isKeyDown("right") && direction != 270) direction = 90;
        
        moveCounter++;
        int moveDelay = hasSpeedBoost ? 8 : 15;
        
        if (moveCounter >= moveDelay) {
            moveCounter = 0;
            moveSnake();
        }
    }
    
    private void moveSnake() {
        int currentX = getX();
        int currentY = getY();
        
        positions.add(0, new int[]{currentX, currentY});
        
        int newX = currentX;
        int newY = currentY;
        
        switch(direction) {
            case 0:   newY -= GRID_SIZE; break;
            case 90:  newX += GRID_SIZE; break;
            case 180: newY += GRID_SIZE; break;
            case 270: newX -= GRID_SIZE; break;
        }
        
        setLocation(newX, newY);
        
        while (positions.size() > bodySegments.size() + 1) {
            positions.remove(positions.size() - 1);
        }
        
        for (int i = 0; i < bodySegments.size(); i++) {
            if (i < positions.size() - 1) {
                int[] pos = positions.get(i + 1);
                bodySegments.get(i).setLocation(pos[0], pos[1]);
            }
        }
        
        checkSelfCollision();
    }
    
    private void checkSelfCollision() {
        int headX = getX();
        int headY = getY();
        
        for (BodySegment segment : bodySegments) {
            if (Math.abs(headX - segment.getX()) < GRID_SIZE/2 && 
                Math.abs(headY - segment.getY()) < GRID_SIZE/2) {
                energy = 0;
                return;
            }
        }
    }
    
    public void growSnake() {
        BodySegment newSegment = new BodySegment();
        getWorld().addObject(newSegment, getX(), getY());
        bodySegments.add(newSegment);
    }
    
    public void removeAllSegments() {
        for (BodySegment segment : bodySegments) {
            if (segment.getWorld() != null) {
                getWorld().removeObject(segment);
            }
        }
        bodySegments.clear();
        positions.clear();
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
    }
    
    private void flashDamage() {
        GreenfootImage img = getImage();
        img.setColor(Color.RED);
        img.fillOval(0, 0, 24, 24);
        updateVisual();
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
    
    private void manageBounds() {
        World world = getWorld();
        int x = getX();
        int y = getY();
        
        if (x < GRID_SIZE) setLocation(world.getWidth() - GRID_SIZE, y);
        if (x > world.getWidth() - GRID_SIZE) setLocation(GRID_SIZE, y);
        if (y < GRID_SIZE) setLocation(x, world.getHeight() - GRID_SIZE);
        if (y > world.getHeight() - GRID_SIZE) setLocation(x, GRID_SIZE);
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
            img.setColor(new Color(0, 200, 0));
        }
        
        img.fillOval(2, 2, 16, 16);
        img.setColor(Color.BLACK);
        img.drawOval(2, 2, 15, 15);
        
        img.setColor(Color.WHITE);
        img.fillOval(5, 5, 4, 4);
        img.fillOval(11, 5, 4, 4);
        
        setImage(img);
    }
    
    public String getExplorerName() { return explorerName; }
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