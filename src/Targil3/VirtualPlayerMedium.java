package Targil3;

public class VirtualPlayerMedium implements VirtualPlayer {

    // returns a column number within 0...COLUMNS, -1 if board is full
    public int MakeAChoise(char[][] board) {
        int emptyrow= 0;
        // first check if a move can win
        for (int i=0; i<Board.COLUMNS; i++) {
            if (!Board.isColumnFull(i)) {
                emptyrow = Board.firstEmptyRow(i);
                board[emptyrow][i] = Game.X_PLAYER;
                if (Game.winningDisc(Board.board, emptyrow, i)) {
                    board[emptyrow][i] = Board.EMPTY; // reset
                    return i;
                }
                board[emptyrow][i] = Board.EMPTY; // reset
            }
        }
        // otherwise then pick up any move that will prevent other player to win
        // in case there is a win on next turn
        int counter = 0; // i count other player possible winnings
        int chosenrow = 0;
        for (int i=0; i<Board.COLUMNS; i++) {
            if (!Board.isColumnFull(i)) {
                emptyrow = Board.firstEmptyRow(i);
                board[emptyrow][i] = Game.O_PLAYER; // assume the other player does this
                if (Game.winningDisc(Board.board, emptyrow, i)) {
                    board[emptyrow][i] = Board.EMPTY; // reset
                    counter++; // we found a winning disc
                    chosenrow = i; // remember the row
                }
                board[emptyrow][i] = Board.EMPTY; // reset
            }
        }
        // we block the player if there is exactly one winning disc
        if (counter==1) return chosenrow;

        // else if other player wins no matter what, pick up first non full column
        for (int i=0; i<Board.COLUMNS; i++)
            if (!Board.isColumnFull(i)){
                return i;
            }
        return -1;

    }
}