import java.util.Random;
import java.util.Scanner;

class NumberGuessingGame {
    private int targetNumber;
    private int maxAttempts;
    private int attempts;
    private boolean isGameOver;
    private final Scanner scanner;

    public NumberGuessingGame() {
        this.scanner = new Scanner(System.in);
        this.isGameOver = false;
    }

    public void startGame() {
        displayWelcomeMessage();
        setDifficulty();
        playGame();
    }

    private void displayWelcomeMessage() {
        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("Rules: The computer will select a number between 1 and 100.");
        System.out.println("You need to guess the number within a limited number of attempts based on difficulty level.");
    }

    private void setDifficulty() {
        System.out.println("Select difficulty level:");
        System.out.println("[1] Easy (10 attempts)");
        System.out.println("[2] Medium (7 attempts)");
        System.out.println("[3] Hard (5 attempts)");
        System.out.print("Enter difficulty (1/2/3): ");
        
        String difficulty = scanner.nextLine().trim().toLowerCase();  
        
        switch (difficulty) {
            case "1":
                maxAttempts = 10;
                break;
            case "2":
                maxAttempts = 7;
                break;
            case "3":
                maxAttempts = 5;
                break;
            default:
                System.out.println("Invalid choice, setting to medium difficulty by default.");
                maxAttempts = 7;
        }
        generateRandomNumber();
    }

    private void generateRandomNumber() {
        Random random = new Random();
        targetNumber = random.nextInt(100) + 1;
    }

    private void playGame() {
        while (!isGameOver) {
            System.out.print("Enter your guess (1-100): ");
            
            if (!scanner.hasNextInt()) {  // Ensure input is an integer
                System.out.println("Invalid input! Please enter a number between 1 and 100.");
                scanner.next();  // Consume invalid input
                continue;
            }

            int userGuess = scanner.nextInt();
            scanner.nextLine();  // Consume newline character

            attempts++;

            if (userGuess == targetNumber) {
                System.out.println("Congratulations! You guessed the number in " + attempts + " attempts.");
                isGameOver = true;
            } else if (userGuess > targetNumber) {
                System.out.println("Too high! Try again.");
            } else {
                System.out.println("Too low! Try again.");
            }

            if (attempts >= maxAttempts && !isGameOver) {
                System.out.println("Game over! The correct number was: " + targetNumber);
                isGameOver = true;
            }
        }
        scanner.close();
    }
}

public class Main {
    public static void main(String[] args) {
        NumberGuessingGame game = new NumberGuessingGame();
        game.startGame();
    }
}
