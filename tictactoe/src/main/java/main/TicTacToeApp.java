package main;

import java.util.Scanner;

import facade.GameFacade;

public class TicTacToeApp {
	
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Welcome to Tic Tac Toe ===");
        System.out.print("Enter Player 1 name (X): ");
        String player1 = scanner.nextLine();

        System.out.print("Enter Player 2 name (O): ");
        String player2 = scanner.nextLine();

        GameFacade engine = new GameFacade(player1, player2);

        while (true) {
            engine.startGame();

            System.out.print("Play again? (yes/no): ");
            String choice = scanner.nextLine().trim().toLowerCase();

            if (!choice.equals("yes")) {
                System.out.println("Thanks for playing!");
                break;
            }

            engine.resetGame();
        }

        scanner.close();
    }

}
