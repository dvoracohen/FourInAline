package Targil3;

public class VirtualPlayerEasy implements VirtualPlayer {

    // returns a column number within 0...COLUMNS, -1 if board is full
    public int MakeAChoise(char[][] board)
    {
        for (int i = 0; i < Board.COLUMNS; i++) {
            if (!Board.isColumnFull(i)) {
                return i;
            }
        }
        return -1;
    }
}