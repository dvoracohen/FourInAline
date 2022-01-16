package Targil3;

public class UserInterfaceDecorator implements UserInterface{
    private final UserInterface userInterfaceToBeDecorated;

    public UserInterfaceDecorator(UserInterface userInterfaceToBeDecorated) {
        this.userInterfaceToBeDecorated = userInterfaceToBeDecorated;
    }

    public void printBoard(char[][] board)
    {
        userInterfaceToBeDecorated.printBoard(board); //delegation
    }

    public int printMenu()
    {
        return userInterfaceToBeDecorated.printMenu();
    }

    public void exit()
    {
        userInterfaceToBeDecorated.exit();
    }

    public int humanPlayerTurn(int playerNum)
    {
        return userInterfaceToBeDecorated.humanPlayerTurn(playerNum);
    }

    public void computerPlayerTurn(int col)
    {
        userInterfaceToBeDecorated.computerPlayerTurn(col);
    }

    public void showWinner(char winner,boolean isComp, int playerNum)
    {
        userInterfaceToBeDecorated.showWinner(winner, isComp, playerNum);
    }
}