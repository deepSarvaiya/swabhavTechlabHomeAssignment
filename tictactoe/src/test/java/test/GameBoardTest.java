package test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.GameBoard;
import model.Marker;

public class GameBoardTest {


    private GameBoard board;

    @BeforeEach
    void setUp() {
        board = new GameBoard();
    }

    @Test
    void testPlaceMarkerAndIsEmpty() {
        assertTrue(board.isCellEmpty(0, 0));
        board.placeMarker(0, 0, Marker.X);
        assertFalse(board.isCellEmpty(0, 0));
    }

    @Test
    void testFullBoardDetection() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                board.placeMarker(i, j, Marker.O);

        assertTrue(board.isFull());
    }

    @Test
    void testWinnerInRow() {
        board.placeMarker(1, 0, Marker.X);
        board.placeMarker(1, 1, Marker.X);
        board.placeMarker(1, 2, Marker.X);
        assertTrue(board.hasWinner(Marker.X));
    }

    @Test
    void testWinnerInColumn() {
        board.placeMarker(0, 2, Marker.O);
        board.placeMarker(1, 2, Marker.O);
        board.placeMarker(2, 2, Marker.O);
        assertTrue(board.hasWinner(Marker.O));
    }

    @Test
    void testWinnerInDiagonal() {
        board.placeMarker(0, 0, Marker.X);
        board.placeMarker(1, 1, Marker.X);
        board.placeMarker(2, 2, Marker.X);
        assertTrue(board.hasWinner(Marker.X));
    }

    @Test
    void testResetClearsBoard() {
        board.placeMarker(0, 0, Marker.X);
        board.reset();
        assertTrue(board.isCellEmpty(0, 0));
    }
	
}
