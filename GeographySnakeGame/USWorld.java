import greenfoot.*;  
import java.util.*;

public class USWorld extends World {
    private int score = 0;
    private int level = 1;
    private int questionsInLevel = 0;
    private int combo = 0;
    private int maxCombo = 0;
    private List<String> remainingStates = new ArrayList<>();
    private List<Orb> currentOrbs = new ArrayList<>();
    private List<PowerUp> activePowerUps = new ArrayList<>();
    private List<Obstacle> obstacles = new ArrayList<>();
    private Question currentQuestion;
    private long lastActionTime = 0;
    private Explorer explorer;
    private Timer timer;
    private boolean gameStarted = false;
    private ScoreText scoreText;
    private CollectText collectText;
    private HintDisplay hintDisplay;
    private StudentProgress studentProgress;
    private GameLevel currentLevel;
    private int powerUpSpawnTimer = 0;
    private int obstacleSpawnTimer = 0;
    private static int highScore = 0;

    public static int getHighScore() {
        return highScore;
    }
    
    public USWorld() {
        super(600, 400, 1);
        setupStates();
        setupStateMarkers();
        currentLevel = new BeginnerLevel();
        prepareStartScreen();
    }

    private void prepareStartScreen() {
        StartButton startButton = new StartButton();
        addObject(startButton, getWidth() / 2, getHeight() / 2);
    }

    public void startGame() {
        if (!gameStarted) {
            gameStarted = true;
            studentProgress = new StudentProgress("GeographyExplorer");
            
            explorer = new Explorer("GeoQuestHero");
            addObject(explorer, 300, 200);

            scoreText = new ScoreText();
            addObject(scoreText, getWidth() - 100, 20);

            collectText = new CollectText();
            addObject(collectText, getWidth() / 2, getHeight() - 30);

            hintDisplay = new HintDisplay();
            addObject(hintDisplay, getWidth() / 2, 50);

            timer = new Timer();
            addObject(timer, getWidth() / 2, 20);
            timer.start();

            generateNewQuestion();
            createInitialBackground();
        }
    }

    public void act() {
        if (gameStarted && explorer != null) {
            updateDisplay();
            manageSpawning();
            checkLevelProgression();
            checkGameEnd();
        }
    }
    
    private void updateDisplay() {
        String displayText = "Score: " + score + " | Level: " + level + " | Combo: " + combo + " | Energy: " + explorer.getEnergy();
        scoreText.setImage(new GreenfootImage(displayText, 18, Color.WHITE, Color.BLACK));
    }
    
    private void manageSpawning() {
        powerUpSpawnTimer++;
        obstacleSpawnTimer++;
        
        if (powerUpSpawnTimer > 300 + Greenfoot.getRandomNumber(200)) {
            spawnPowerUp();
            powerUpSpawnTimer = 0;
        }
        
        if (obstacleSpawnTimer > 200 + level * 50 + Greenfoot.getRandomNumber(100)) {
            spawnObstacle();
            obstacleSpawnTimer = 0;
        }
    }
    
    private void checkLevelProgression() {
        if (questionsInLevel >= 5 + level * 2) {
            advanceLevel();
        }
    }
    
    private void checkGameEnd() {
        if (remainingStates.isEmpty() || !explorer.isAlive()) {
            endGame();
        }
    }

    public void endGame() {
        if (score > highScore) highScore = score;
        if (timer != null) timer.stop();
        
        for (Orb orb : new ArrayList<>(currentOrbs)) removeObject(orb);
        for (PowerUp power : new ArrayList<>(activePowerUps)) removeObject(power);
        for (Obstacle obs : new ArrayList<>(obstacles)) removeObject(obs);
        currentOrbs.clear();
        activePowerUps.clear();
        obstacles.clear();
        
        for (StateMarker marker : getObjects(StateMarker.class)) removeObject(marker);
        if (explorer != null) {
            explorer.removeAllSegments();
            removeObject(explorer);
        }
        if (scoreText != null) removeObject(scoreText);
        if (collectText != null) removeObject(collectText);
        if (hintDisplay != null) removeObject(hintDisplay);

        String report = "=== GEOQUEST COMPLETE ===\n" +
                       "Final Score: " + score + "\n" +
                       "Max Level Reached: " + level + "\n" +
                       "Highest Combo: " + maxCombo + "\n" +
                       "Accuracy: " + String.format("%.1f", studentProgress.getAccuracy()) + "%\n" +
                       "Performance: " + studentProgress.getPerformanceLevel() + "\n" +
                       "States Mastered: " + studentProgress.getMasteredStatesCount() + "/50\n" +
                       "Time: " + (timer != null ? timer.getElapsedTime() + "s" : "0s") + "\n" +
                       "High Score: " + highScore;

        GreenfootImage bg = getBackground();
        bg.setColor(new Color(0, 0, 50, 200));
        bg.fill();
        
        GreenfootImage endScreenText = new GreenfootImage(report, 18, Color.CYAN, new Color(0,0,0,0));
        int x = getWidth() / 2;
        int y = getHeight() / 2;
        bg.drawImage(endScreenText, x - endScreenText.getWidth()/2, y - endScreenText.getHeight()/2);
        
        Greenfoot.stop();
    }

    private void setupStates() {
        remainingStates.addAll(Arrays.asList(
            "Alabama","Alaska","Arizona","Arkansas","California","Colorado","Connecticut","Delaware","Florida","Georgia",
            "Hawaii","Idaho","Illinois","Indiana","Iowa","Kansas","Kentucky","Louisiana","Maine","Maryland","Massachusetts",
            "Michigan","Minnesota","Mississippi","Missouri","Montana","Nebraska","Nevada","New Hampshire","New Jersey",
            "New Mexico","New York","North Carolina","North Dakota","Ohio","Oklahoma","Oregon","Pennsylvania","Rhode Island",
            "South Carolina","South Dakota","Tennessee","Texas","Utah","Vermont","Virginia","Washington","West Virginia","Wisconsin","Wyoming"
        ));
    }

    private void setupStateMarkers() {
        addObject(new StateMarker("Alabama"), 405, 275);
        addObject(new StateMarker("Alaska"), 75, 325);
        addObject(new StateMarker("Arizona"), 130, 245);
        addObject(new StateMarker("Arkansas"), 342, 250);
        addObject(new StateMarker("California"), 55, 200);
        addObject(new StateMarker("Colorado"), 205, 190);
        addObject(new StateMarker("Connecticut"), 537, 127);
        addObject(new StateMarker("Delaware"), 513, 168);
        addObject(new StateMarker("Florida"), 473, 330);
        addObject(new StateMarker("Georgia"), 445, 275);
        addObject(new StateMarker("Hawaii"), 205, 370);
        addObject(new StateMarker("Idaho"), 125, 110);
        addObject(new StateMarker("Illinois"), 372, 175);
        addObject(new StateMarker("Indiana"), 404, 175);
        addObject(new StateMarker("Iowa"), 330, 150);
        addObject(new StateMarker("Kansas"), 280, 200);
        addObject(new StateMarker("Kentucky"), 420, 204);
        addObject(new StateMarker("Louisiana"), 342, 300);
        addObject(new StateMarker("Maine"), 558, 67);
        addObject(new StateMarker("Maryland"), 500, 168);
        addObject(new StateMarker("Massachusetts"), 537, 116);
        addObject(new StateMarker("Michigan"), 415, 125);
        addObject(new StateMarker("Minnesota"), 318, 92);
        addObject(new StateMarker("Mississippi"), 374, 275);
        addObject(new StateMarker("Missouri"), 340, 200);
        addObject(new StateMarker("Montana"), 180, 75);
        addObject(new StateMarker("Nebraska"), 270, 160);
        addObject(new StateMarker("Nevada"), 90, 170);
        addObject(new StateMarker("New Hampshire"), 541, 100);
        addObject(new StateMarker("New Jersey"), 522, 150);
        addObject(new StateMarker("New Mexico"), 195, 250);
        addObject(new StateMarker("New York"), 510, 115);
        addObject(new StateMarker("North Carolina"), 483, 227);
        addObject(new StateMarker("North Dakota"), 265, 75);
        addObject(new StateMarker("Ohio"), 439, 165);
        addObject(new StateMarker("Oklahoma"), 290, 240);
        addObject(new StateMarker("Oregon"), 65, 95);
        addObject(new StateMarker("Pennsylvania"), 490, 150);
        addObject(new StateMarker("Rhode Island"), 547, 126);
        addObject(new StateMarker("South Carolina"), 470, 250);
        addObject(new StateMarker("South Dakota"), 265, 115);
        addObject(new StateMarker("Tennessee"), 405, 232);
        addObject(new StateMarker("Texas"), 270, 300);
        addObject(new StateMarker("Utah"), 140, 180);
        addObject(new StateMarker("Vermont"), 528, 90);
        addObject(new StateMarker("Virginia"), 490, 195);
        addObject(new StateMarker("Washington"), 85, 50);
        addObject(new StateMarker("West Virginia"), 460, 190);
        addObject(new StateMarker("Wisconsin"), 361, 110);
        addObject(new StateMarker("Wyoming"), 190, 130);
    }

    private StateMarker findMarkerByName(String name) {
        for (StateMarker marker : getObjects(StateMarker.class)) {
            if (marker.getStateName().equals(name)) return marker;
        }
        return null;
    }

    private void generateNewQuestion() {
        for(Orb orb: currentOrbs) removeObject(orb);
        currentOrbs.clear();
        
        if(remainingStates.isEmpty()) {
            endGame();
            return;
        }
        
        currentQuestion = currentLevel.generateQuestion(remainingStates);
        if (currentQuestion == null) {
            endGame();
            return;
        }
        
        List<String> choices = currentQuestion.getChoices();
        String correctAnswer = currentQuestion.getCorrectAnswer();
        
        for (String choice : choices) {
            StateMarker marker = findMarkerByName(choice);
            if (marker != null) {
                boolean isCorrect = choice.equals(correctAnswer);
                String displayText = getDisplayTextForChoice(choice);
                LabeledOrb orb = new LabeledOrb(choice, isCorrect, displayText);
                addObject(orb, marker.getX(), marker.getY());
                currentOrbs.add(orb);
            }
        }
        
        if(collectText != null) collectText.update(currentQuestion.getQuestionText());
    }
    
    private String getDisplayTextForChoice(String choice) {
        if (currentQuestion instanceof StateNameQuestion) {
            return choice;
        } else if (currentQuestion instanceof CapitalQuestion) {
            return choice;
        } else if (currentQuestion instanceof RegionQuestion) {
            return choice;
        }
        return choice;
    }
    
    private void spawnPowerUp() {
        String[] powerTypes = {"SPEED", "SHIELD", "DOUBLE"};
        String type = powerTypes[Greenfoot.getRandomNumber(powerTypes.length)];
        PowerUp power = new PowerUp(type);
        
        int x = 50 + Greenfoot.getRandomNumber(getWidth() - 100);
        int y = 50 + Greenfoot.getRandomNumber(getHeight() - 100);
        addObject(power, x, y);
        activePowerUps.add(power);
    }
    
    private void spawnObstacle() {
        if (level < 3) return;
        
        String[] obstacleTypes = {"TORNADO", "ROCKSLIDE", "STORM"};
        String type = obstacleTypes[Greenfoot.getRandomNumber(obstacleTypes.length)];
        Obstacle obstacle = new Obstacle(type);
        
        int x = 30 + Greenfoot.getRandomNumber(getWidth() - 60);
        int y = 30 + Greenfoot.getRandomNumber(getHeight() - 60);
        addObject(obstacle, x, y);
        obstacles.add(obstacle);
    }
    
    private void advanceLevel() {
        level++;
        questionsInLevel = 0;
        combo = 0;
        
        if (level == 2) currentLevel = new IntermediateLevel();
        else if (level >= 3) currentLevel = new AdvancedLevel();
        
        explorer.restoreEnergy(50);
        
        showLevelUpMessage();
    }
    
    private void showLevelUpMessage() {
        GreenfootImage bg = getBackground();
        GreenfootImage levelMsg = new GreenfootImage("LEVEL UP! " + level, 36, Color.YELLOW, Color.BLACK);
        bg.drawImage(levelMsg, getWidth()/2 - levelMsg.getWidth()/2, getHeight()/2 - 50);
    }
    
    private void createInitialBackground() {
        GreenfootImage bg = getBackground();
        bg.setColor(new Color(20, 50, 80));
        bg.fill();
        
        for (int i = 0; i < 50; i++) {
            int x = Greenfoot.getRandomNumber(getWidth());
            int y = Greenfoot.getRandomNumber(getHeight());
            bg.setColor(Color.WHITE);
            bg.fillOval(x, y, 2, 2);
        }
    }

    public void collectOrb(Orb orb){
        if (orb.getWorld() == null) return;
        
        long now = System.currentTimeMillis();
        if(now - orb.getSpawnTime() < 300) return;
        if(now - lastActionTime < 300) return;
        lastActionTime = now;

        boolean isCorrect = orb.isCorrectAnswer();
        String studentAnswer = orb.getStateName();
        int orbX = orb.getX();
        int orbY = orb.getY();
        
        removeObject(orb);
        currentOrbs.remove(orb);
        
        studentProgress.recordAnswer(currentQuestion, isCorrect, studentAnswer);

        if(isCorrect) {
            combo++;
            maxCombo = Math.max(maxCombo, combo);
            
            int basePoints = currentQuestion.getPoints();
            int comboBonus = combo * 2;
            int levelBonus = level * 5;
            int totalPoints = basePoints + comboBonus + levelBonus;
            
            if (explorer.hasDoublePoints()) totalPoints *= 2;
            
            score += totalPoints;
            questionsInLevel++;
            
            remainingStates.remove(studentAnswer);
            StateMarker marker = findMarkerByName(studentAnswer);
            if(marker != null) marker.collect();
            
            explorer.restoreEnergy(10);
            explorer.growSnake();
            
            showScoreBonus(totalPoints, orbX, orbY);
            
            if(hintDisplay != null) {
                hintDisplay.showQuickFact(studentAnswer);
            }
            
            if(!remainingStates.isEmpty()) {
                generateNewQuestion();
            } else {
                endGame();
            }
        } else {
            combo = 0;
            explorer.takeDamage(20);
            
            if(hintDisplay != null) {
                String correctAnswer = currentQuestion.getCorrectAnswer();
                hintDisplay.showHint("❌ Wrong! Correct answer: " + correctAnswer + "\n" + 
                                   GeographyFacts.getStateFact(correctAnswer), 300);
            }
            
            if(!explorer.isAlive()) {
                endGame();
            } else {
                generateNewQuestion();
            }
        }
    }
    
    private void showScoreBonus(int points, int x, int y) {
        GreenfootImage bg = getBackground();
        String bonusText = "+" + points;
        if (combo > 3) bonusText += " COMBO!";
        
        GreenfootImage bonus = new GreenfootImage(bonusText, 20, Color.YELLOW, new Color(0,0,0,0));
        bg.drawImage(bonus, x - bonus.getWidth()/2, y - 20);
    }
}
