package Targil3;

public interface UserInterface {
    public abstract int printMenu();
    public abstract void exit();
    public abstract void printBoard(char[][] board);
    public abstract int humanPlayerTurn(int playerNum);
    public abstract void computerPlayerTurn(int col);
    public abstract void showWinner(char winner,boolean isComp, int playerNum);
}
