import java.util.*;

public class StudentProgress {
    private String studentName;
    private int totalScore;
    private int questionsAnswered;
    private int correctAnswers;
    private long startTime;
    private Map<String, Integer> categoryScores;
    private Set<String> masteredStates;
    private List<String> incorrectAnswers;
    private static final int MASTERY_THRESHOLD = 2;
    
    public StudentProgress(String studentName) {
        this.studentName = studentName;
        this.totalScore = 0;
        this.questionsAnswered = 0;
        this.correctAnswers = 0;
        this.startTime = System.currentTimeMillis();
        this.categoryScores = new HashMap<>();
        this.masteredStates = new HashSet<>();
        this.incorrectAnswers = new ArrayList<>();
        initializeCategoryScores();
    }
    
    private void initializeCategoryScores() {
        categoryScores.put("State Name", 0);
        categoryScores.put("State Capital", 0);
        categoryScores.put("Geographic Region", 0);
    }
    
    public void recordAnswer(Question question, boolean isCorrect, String studentAnswer) {
        questionsAnswered++;
        
        if (isCorrect) {
            correctAnswers++;
            totalScore += question.getPoints();
            addCategoryScore(question.getQuestionType(), question.getPoints());
            
            if (question instanceof StateNameQuestion || 
                question instanceof CapitalQuestion || 
                question instanceof RegionQuestion) {
                checkStatemastery(question);
            }
        } else {
            incorrectAnswers.add(question.getQuestionText() + " - Your answer: " + 
                               studentAnswer + " - Correct: " + question.getCorrectAnswer());
        }
    }
    
    private void addCategoryScore(String category, int points) {
        categoryScores.put(category, categoryScores.get(category) + points);
    }
    
    private void checkStatemastery(Question question) {
        String stateName = extractStateName(question);
        if (stateName != null) {
            masteredStates.add(stateName);
        }
    }
    
    private String extractStateName(Question question) {
        if (question instanceof StateNameQuestion) {
            return question.getCorrectAnswer();
        } else if (question instanceof CapitalQuestion) {
            String questionText = question.getQuestionText();
            return questionText.substring(questionText.indexOf("of ") + 3, questionText.indexOf("?"));
        } else if (question instanceof RegionQuestion) {
            String questionText = question.getQuestionText();
            return questionText.substring(questionText.indexOf("is ") + 3, questionText.indexOf(" in"));
        }
        return null;
    }
    
    public String getStudentName() {
        return studentName;
    }
    
    public int getTotalScore() {
        return totalScore;
    }
    
    public double getAccuracy() {
        if (questionsAnswered == 0) return 0.0;
        return (double) correctAnswers / questionsAnswered * 100.0;
    }
    
    public int getQuestionsAnswered() {
        return questionsAnswered;
    }
    
    public int getCorrectAnswers() {
        return correctAnswers;
    }
    
    public long getElapsedTime() {
        return System.currentTimeMillis() - startTime;
    }
    
    public Map<String, Integer> getCategoryScores() {
        return new HashMap<>(categoryScores);
    }
    
    public int getMasteredStatesCount() {
        return masteredStates.size();
    }
    
    public Set<String> getMasteredStates() {
        return new HashSet<>(masteredStates);
    }
    
    public List<String> getIncorrectAnswers() {
        return new ArrayList<>(incorrectAnswers);
    }
    
    public String getPerformanceLevel() {
        double accuracy = getAccuracy();
        if (accuracy >= 90) return "Excellent";
        else if (accuracy >= 80) return "Good";
        else if (accuracy >= 70) return "Average";
        else if (accuracy >= 60) return "Needs Improvement";
        else return "Requires Practice";
    }
    
    public String generateReport() {
        StringBuilder report = new StringBuilder();
        report.append("=== STUDENT PROGRESS REPORT ===\n");
        report.append("Student: ").append(studentName).append("\n");
        report.append("Total Score: ").append(totalScore).append(" points\n");
        report.append("Questions Answered: ").append(questionsAnswered).append("\n");
        report.append("Accuracy: ").append(String.format("%.1f", getAccuracy())).append("%\n");
        report.append("Performance Level: ").append(getPerformanceLevel()).append("\n");
        report.append("States Mastered: ").append(getMasteredStatesCount()).append("/50\n");
        report.append("Time Played: ").append(getElapsedTime() / 1000).append(" seconds\n\n");
        
        report.append("Category Breakdown:\n");
        for (Map.Entry<String, Integer> entry : categoryScores.entrySet()) {
            report.append("- ").append(entry.getKey()).append(": ").append(entry.getValue()).append(" points\n");
        }
        
        return report.toString();
    }
}