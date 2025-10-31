package com.elevatelabs.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuizApp {
    private List<Question> questions;
    private QuizResult result;
    private Scanner scanner;
    
    // Constructor
    public QuizApp() {
        questions = new ArrayList<>();
        result = new QuizResult();
        scanner = new Scanner(System.in);
        loadQuestions();
    }
    
    // Load quiz questions
    private void loadQuestions() {
        // Java Programming Questions
        questions.add(new Question(
            "What is the size of int data type in Java?",
            new String[]{"8 bits", "16 bits", "32 bits", "64 bits"},
            2, // Index 2 is correct (32 bits)
            "Java Basics"
        ));
        
        questions.add(new Question(
            "Which keyword is used to inherit a class in Java?",
            new String[]{"implement", "extends", "inherits", "super"},
            1, // Index 1 is correct (extends)
            "Java OOP"
        ));
        
        questions.add(new Question(
            "What is the default value of a boolean variable in Java?",
            new String[]{"true", "false", "null", "0"},
            1, // Index 1 is correct (false)
            "Java Basics"
        ));
        
        questions.add(new Question(
            "Which method is the entry point of a Java application?",
            new String[]{"start()", "main()", "run()", "init()"},
            1, // Index 1 is correct (main())
            "Java Basics"
        ));
        
        questions.add(new Question(
            "What is encapsulation in Java?",
            new String[]{
                "Hiding implementation details",
                "Creating multiple classes",
                "Using inheritance",
                "Overloading methods"
            },
            0, // Index 0 is correct
            "Java OOP"
        ));
        
        // General Programming Questions
        questions.add(new Question(
            "Which loop is guaranteed to execute at least once?",
            new String[]{"for loop", "while loop", "do-while loop", "foreach loop"},
            2, // Index 2 is correct (do-while loop)
            "Control Flow"
        ));
        
        questions.add(new Question(
            "What does API stand for?",
            new String[]{
                "Application Programming Interface",
                "Advanced Programming Interface",
                "Application Process Integration",
                "Automated Program Interaction"
            },
            0, // Index 0 is correct
            "General"
        ));
        
        questions.add(new Question(
            "Which collection class allows duplicate elements?",
            new String[]{"Set", "HashSet", "ArrayList", "TreeSet"},
            2, // Index 2 is correct (ArrayList)
            "Java Collections"
        ));
        
        questions.add(new Question(
            "What is the output of: System.out.println(10 + 20 + \"Hello\");",
            new String[]{"1020Hello", "30Hello", "Hello30", "Error"},
            1, // Index 1 is correct (30Hello)
            "Java Basics"
        ));
        
        questions.add(new Question(
            "Which access modifier provides the most restricted access?",
            new String[]{"public", "protected", "default", "private"},
            3, // Index 3 is correct (private)
            "Java OOP"
        ));
    }
    
    // Start the quiz
    public void startQuiz() {
        displayWelcome();
        
        result.setTotalQuestions(questions.size());
        long startTime = System.currentTimeMillis();
        
        for (int i = 0; i < questions.size(); i++) {
            Question currentQuestion = questions.get(i);
            currentQuestion.displayQuestion(i + 1);
            
            int userAnswer = getUserAnswer();
            
            if (userAnswer == -1) {
                result.incrementSkipped();
                System.out.println("⏭️  Question skipped!");
            } else {
                if (currentQuestion.checkAnswer(userAnswer)) {
                    result.incrementCorrect();
                    System.out.println("✅ Correct!");
                } else {
                    result.incrementWrong(i);
                    System.out.println("❌ Wrong! Correct answer: " + 
                        currentQuestion.getOptions()[currentQuestion.getCorrectAnswer()]);
                }
            }
            
            // Show progress
            System.out.println("\nProgress: " + (i + 1) + "/" + questions.size());
            
            // Pause between questions
            if (i < questions.size() - 1) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
        
        long endTime = System.currentTimeMillis();
        result.setTimeTaken((endTime - startTime) / 1000);
        
        // Display final results
        result.displayResults();
        
        // Ask if user wants to review wrong answers
        reviewWrongAnswers();
    }
    
    // Get user answer
    private int getUserAnswer() {
        System.out.print("\nEnter your answer (1-4) or 0 to skip: ");
        try {
            int answer = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            if (answer == 0) {
                return -1; // Skip
            } else if (answer >= 1 && answer <= 4) {
                return answer - 1; // Convert to 0-based index
            } else {
                System.out.println("Invalid input! Question skipped.");
                return -1;
            }
        } catch (Exception e) {
            scanner.nextLine(); // Clear buffer
            System.out.println("Invalid input! Question skipped.");
            return -1;
        }
    }
    
    // Display welcome message
    private void displayWelcome() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("           WELCOME TO ONLINE QUIZ APPLICATION");
        System.out.println("=".repeat(60));
        System.out.println("Instructions:");
        System.out.println("• Answer each question by entering the option number (1-4)");
        System.out.println("• Enter 0 to skip a question");
        System.out.println("• You'll see your score at the end");
        System.out.println("• Total Questions: " + questions.size());
        System.out.println("=".repeat(60));
        System.out.print("\nPress Enter to start the quiz...");
        scanner.nextLine();
    }
    
    // Review wrong answers
    private void reviewWrongAnswers() {
        if (result.getWrongAnswers() > 0) {
            System.out.print("\nWould you like to review your wrong answers? (yes/no): ");
            String response = scanner.nextLine().trim().toLowerCase();
            
            if (response.equals("yes") || response.equals("y")) {
                System.out.println("\n" + "=".repeat(60));
                System.out.println("              REVIEWING WRONG ANSWERS");
                System.out.println("=".repeat(60));
                
                for (int index : result.getWrongQuestionIndices()) {
                    Question q = questions.get(index);
                    q.displayQuestion(index + 1);
                    System.out.println("\n✓ Correct Answer: " + 
                        (q.getCorrectAnswer() + 1) + ". " + 
                        q.getOptions()[q.getCorrectAnswer()]);
                    System.out.println("-".repeat(60));
                }
            }
        }
    }
    
    // Main method
    public static void main(String[] args) {
        QuizApp quizApp = new QuizApp();
        quizApp.startQuiz();
        
        System.out.println("\nThank you for taking the quiz! 🎓");
    }
}

