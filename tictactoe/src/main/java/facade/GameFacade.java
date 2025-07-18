package facade;

import java.util.Scanner;

import model.GameBoard;
import model.Marker;
import model.Player;

public class GameFacade {

	
	 private final GameBoard board;
	    private final Player player1;
	    private final Player player2;
	    private Player currentPlayer;

	    public GameFacade(String player1Name, String player2Name) {
	        this.board = new GameBoard();
	        this.player1 = new Player(player1Name, Marker.X);
	        this.player2 = new Player(player2Name, Marker.O);
	        this.currentPlayer = player1;
	    }

	    public void startGame() {
	        Scanner scanner = new Scanner(System.in);
	        board.printBoard();

	        while (true) {
	            System.out.println(currentPlayer.getName() + "'s turn (" + currentPlayer.getMarker() + ")");
	            System.out.print("Enter row (0-2): ");
	            int row = scanner.nextInt();
	            System.out.print("Enter column (0-2): ");
	            int col = scanner.nextInt();

	            if (!board.isCellEmpty(row, col)) {
	                System.out.println("Cell already taken. Try again.");
	                continue;
	            }

	            board.placeMarker(row, col, currentPlayer.getMarker());
	            board.printBoard();

	            if (board.hasWinner(currentPlayer.getMarker())) {
	                System.out.println(currentPlayer.getName() + " wins!");
	                break;
	            }

	            if (board.isFull()) {
	                System.out.println("It's a draw!");
	                break;
	            }

	            switchPlayer();
	        }
	    }

	    public void resetGame() {
	        board.reset();
	        currentPlayer = player1;
	        System.out.println("Game has been reset.");
	    }

	    private void switchPlayer() {
	        currentPlayer = (currentPlayer == player1) ? player2 : player1;
	    }
}
