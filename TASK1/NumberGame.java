import java.util.Random;
import java.util.Scanner;

public class NumberGame {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        int playAgain = 1;

        while (playAgain == 1) {

            //Select Difficulty Level
            System.out.println("\nSelect Difficulty Level:");
            System.out.println("1. Easy (1-50, 10 attempts)");
            System.out.println("2. Medium (1-100, 7 attempts)");
            System.out.println("3. Hard (1-200, 5 attempts)");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            int maxAttempts = 0;
            int range = 0;

            switch (choice) {
                case 1:
                    range = 50;
                    maxAttempts = 10;
                    break;
                case 2:
                    range = 100;
                    maxAttempts = 7;
                    break;
                case 3:
                    range = 200;
                    maxAttempts = 5;
                    break;
                default:
                    System.out.println("Invalid choice! Defaulting to Medium.");
                    range = 100;
                    maxAttempts = 7;
            }

            int number = rand.nextInt(range) + 1;
            int attempts = 0;
            int guess;

            System.out.println("\n🎮 Welcome to Number Guessing Game!");
            System.out.println("Guess a number between 1 and " + range);
            System.out.println("You have " + maxAttempts + " attempts.");

            while (attempts < maxAttempts) {

                System.out.print("Enter your guess: ");
                guess = sc.nextInt();
                attempts++;

                if (guess == number) {
                    System.out.println("Correct! You guessed it in " + attempts + " attempts.");
                    break;
                } else if (guess > number) {
                    System.out.println("Too High! \nGuess Lower");
                } else {
                    System.out.println("Too Low! \nGuess Higher");
                }

                System.out.println("Attempts left: " + (maxAttempts - attempts));
            }

            if (attempts == maxAttempts) {
                System.out.println("You've used all attempts!");
                System.out.println("The correct number was: " + number);
            }

            // Play again option
            System.out.print("\nDo you want to play again? (1 = Yes, 0 = No): ");
            playAgain = sc.nextInt();
        }

        System.out.println("Thanks for playing!");
        sc.close();
    }
}
