package com.elevatelabs.app;

public class Question {
    private String questionText;
    private String[] options;
    private int correctAnswer; // Index of correct option (0-3)
    private String category;
    
    // Constructor
    public Question(String questionText, String[] options, int correctAnswer, String category) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
        this.category = category;
    }
    
    // Getters
    public String getQuestionText() {
        return questionText;
    }
    
    public String[] getOptions() {
        return options;
    }
    
    public int getCorrectAnswer() {
        return correctAnswer;
    }
    
    public String getCategory() {
        return category;
    }
    
    // Method to display question with options
    public void displayQuestion(int questionNumber) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("Question " + questionNumber + " [" + category + "]");
        System.out.println("=".repeat(60));
        System.out.println(questionText);
        System.out.println();
        
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
    }
    
    // Method to check if answer is correct
    public boolean checkAnswer(int userAnswer) {
        return userAnswer == correctAnswer;
    }
}

