package model;

public class GameBoard {
	
	private final Cell[][] grid;

    public GameBoard() {
        grid = new Cell[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = new Cell();
            }
        }
    }

    public boolean isCellEmpty(int row, int col) {
        return isValidPosition(row, col) && grid[row][col].isEmpty();
    }

    public void placeMarker(int row, int col, Marker marker) {
        if (isValidPosition(row, col)) {
            grid[row][col].setMarker(marker);
        } else {
            throw new IllegalArgumentException("Invalid board position.");
        }
    }

    public boolean isFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j].isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean hasWinner(Marker marker) {
        // rows and columns
        for (int i = 0; i < 3; i++) {
            if ((grid[i][0].getMarker() == marker &&
                 grid[i][1].getMarker() == marker &&
                 grid[i][2].getMarker() == marker) ||

                (grid[0][i].getMarker() == marker &&
                 grid[1][i].getMarker() == marker &&
                 grid[2][i].getMarker() == marker)) {
                return true;
            }
        }

        // diagonals
        return (grid[0][0].getMarker() == marker &&
                grid[1][1].getMarker() == marker &&
                grid[2][2].getMarker() == marker) ||

               (grid[0][2].getMarker() == marker &&
                grid[1][1].getMarker() == marker &&
                grid[2][0].getMarker() == marker);
    }

    public void reset() {
        for (Cell[] row : grid) {
            for (Cell cell : row) {
                cell.clear();
            }
        }
    }

    public void printBoard() {
        System.out.println("\nCurrent Board:");
        for (Cell[] row : grid) {
            for (Cell cell : row) {
                Marker m = cell.getMarker();
                String symbol = (m == Marker.EMPTY) ? "-" : m.toString();
                System.out.print(symbol + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3;
    }

}
