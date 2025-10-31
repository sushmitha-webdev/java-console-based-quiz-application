package com.elevatelabs.app;

import java.util.ArrayList;
import java.util.List;

public class QuizResult {
    private int totalQuestions;
    private int correctAnswers;
    private int wrongAnswers;
    private int skippedQuestions;
    private List<Integer> wrongQuestionIndices;
    private long timeTaken; // in seconds
    
    // Constructor
    public QuizResult() {
        this.totalQuestions = 0;
        this.correctAnswers = 0;
        this.wrongAnswers = 0;
        this.skippedQuestions = 0;
        this.wrongQuestionIndices = new ArrayList<>();
        this.timeTaken = 0;
    }
    
    // Methods to update results
    public void incrementCorrect() {
        correctAnswers++;
    }
    
    public void incrementWrong(int questionIndex) {
        wrongAnswers++;
        wrongQuestionIndices.add(questionIndex);
    }
    
    public void incrementSkipped() {
        skippedQuestions++;
    }
    
    public void setTotalQuestions(int total) {
        this.totalQuestions = total;
    }
    
    public void setTimeTaken(long time) {
        this.timeTaken = time;
    }
    
    // Getters
    public int getTotalQuestions() {
        return totalQuestions;
    }
    
    public int getCorrectAnswers() {
        return correctAnswers;
    }
    
    public int getWrongAnswers() {
        return wrongAnswers;
    }
    
    public int getSkippedQuestions() {
        return skippedQuestions;
    }
    
    public List<Integer> getWrongQuestionIndices() {
        return wrongQuestionIndices;
    }
    
    // Calculate percentage
    public double getPercentage() {
        if (totalQuestions == 0) return 0;
        return ((double) correctAnswers / totalQuestions) * 100;
    }
    
    // Get grade based on percentage
    public String getGrade() {
        double percentage = getPercentage();
        if (percentage >= 90) return "A+";
        else if (percentage >= 80) return "A";
        else if (percentage >= 70) return "B";
        else if (percentage >= 60) return "C";
        else if (percentage >= 50) return "D";
        else return "F";
    }
    
    // Display detailed results
    public void displayResults() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("                    QUIZ RESULTS");
        System.out.println("=".repeat(60));
        System.out.println("Total Questions    : " + totalQuestions);
        System.out.println("Correct Answers    : " + correctAnswers);
        System.out.println("Wrong Answers      : " + wrongAnswers);
        System.out.println("Skipped Questions  : " + skippedQuestions);
        System.out.println("-".repeat(60));
        System.out.printf("Score              : %.2f%%\n", getPercentage());
        System.out.println("Grade              : " + getGrade());
        System.out.println("Time Taken         : " + formatTime(timeTaken));
        System.out.println("=".repeat(60));
        
        // Performance feedback
        displayFeedback();
    }
    
    // Format time in minutes and seconds
    private String formatTime(long seconds)
    {
        long minutes = seconds / 60;
        long secs = seconds % 60;
        return minutes + " min " + secs + " sec";
    }
    
    // Display personalized feedback
    private void displayFeedback() {
        double percentage = getPercentage();
        System.out.println("\nFeedback:");
        if (percentage >= 90) 
        {
            System.out.println("Outstanding! You have excellent knowledge!");
        } 
        else if (percentage >= 80) 
        {
            System.out.println("Great job! Keep up the good work!");
        } 
        else if (percentage >= 70) 
        {
            System.out.println("Good effort! A bit more practice will help!");
        } 
        else if (percentage >= 60) 
        {
            System.out.println("Fair attempt. Review the topics again.");
        } 
        else if (percentage >= 50) 
        {
            System.out.println("You need more practice. Don't give up!");
        } 
        else 
        {
            System.out.println("Keep trying! Practice makes perfect!");
        }
        System.out.println("=".repeat(60));
    }
}

