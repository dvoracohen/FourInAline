package Targil3;

public class Board {
    // first index is row (horizontal), second is column (vertical)
    // [0,0] is the top left board cell
    public static char[][] board;

    // board size
    public static int ROWS = 6;
    public static int COLUMNS = 7;
    public static char EMPTY = ' ';

    public Board()
    {
        this.board = new char[ROWS][COLUMNS];

        for (int i = 0; i < ROWS; i++)
            for (int j = 0; j < COLUMNS; j++)
                board[i][j] = EMPTY;
    }

    public static boolean isColumnFull(int colIndex)
    {
        for (int i = 0; i < ROWS; i++) {
            if (board[i][colIndex] == EMPTY)
                return false;
        }
        return true;
    }

    // returns the ROW index of the first empty cell in the COLUMN rowIndex. -1 if all full
    public static int firstEmptyRow(int colIndex) {
        for (int i = ROWS-1; i >=0; i--) {
            if (board[i][colIndex] == EMPTY) return i;
        }
        return -1;
    }

    public static boolean boardIsFull() {
        // it's enough to check top row
        for (int i=0; i<COLUMNS; i++)
            if (board[0][i]==EMPTY) return false;
        return true;
    }
}
