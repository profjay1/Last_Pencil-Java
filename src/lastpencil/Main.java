package lastpencil;

import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        //Get the total number of pencils
        int pencils = 0;
        boolean validPencilInput = false;
        while (!validPencilInput) {
            System.out.print("How many pencils would you like to use: ");
            String pencilInput = sc.nextLine();

            try {
                pencils = Integer.parseInt(pencilInput);
                if (pencils > 0) {
                    validPencilInput = true; // Exit loop if valid positive number
                } else {
                    System.out.println("The number of pencils should be positive");
                }
            } catch (NumberFormatException e) {
                System.out.println("The number of pencils should be numeric");
            }
        }

        // Get the first player with validation
        String currentPlayer;
        while (true) {
            System.out.print("Who will be the first (Paul, James): ");
            currentPlayer = sc.nextLine();
            if (currentPlayer.equals("Paul") || currentPlayer.equals("James")) {
                break;
            } else {
                System.out.println("Choose between 'John' and 'James'");
            }
        }

        // Main game loop
        while (pencils > 0) {
            System.out.println("|".repeat(pencils));
            System.out.println(currentPlayer + "'s turn!");

            int takenPencils;
            if (currentPlayer.equals("James")) { // Bot's turn
                if (pencils == 1) {
                    takenPencils = 1; // Bot takes the last pencil
                    System.out.println(takenPencils);
                    System.out.println("Paul won!");
                    break;
                } else if ((pencils - 1) % 4 == 0) {
                    takenPencils = random.nextInt(3) + 1; // Random move in losing position
                } else {
                    takenPencils = (pencils - 1) % 4; // Winning strategy
                    if (takenPencils == 0) takenPencils = 3;
                }
                System.out.println(takenPencils);
            } else { // User's turn
                while (true) {
                    try {
                        takenPencils = Integer.parseInt(sc.nextLine());
                        if (takenPencils >= 1 && takenPencils <= 3 && takenPencils <= pencils) break;
                        if (takenPencils < 1 || takenPencils > 3) {
                            System.out.println("Possible values: '1', '2' or '3'");
                        } else if (takenPencils > pencils) {
                            System.out.println("Too many pencils were taken");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Possible values: '1', '2' or '3'");
                    }
                }
            }

            // Update pencils and check game end
            pencils -= takenPencils;

            if (pencils == 0) {
                System.out.println(currentPlayer.equals("James") ? "Paul won!" : "James won!");
                break;
            }

            // Swap players
            currentPlayer = currentPlayer.equals("Paul") ? "James" : "Paul";
        }


        //End the game once there are no more pencils left
        sc.close();
    }
}
