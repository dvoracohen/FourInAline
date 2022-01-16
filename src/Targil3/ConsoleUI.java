package Targil3;

import java.util.Scanner;

public class ConsoleUI implements UserInterface {

    Scanner terminalInput;

    public ConsoleUI()
    {
        terminalInput= new Scanner(System.in);
    }

    public int printMenu() {
        int choice;
        boolean badchoice = false;

        do{
            System.out.println(MenuOptions.Exit.ordinal() + ". Exit");
            System.out.println(MenuOptions.SinglePlayer.ordinal()  + ". Play against a friend");
            System.out.println(MenuOptions.MultiPlayer.ordinal()  + ". Play against the computer");
            System.out.print("Please choose an option:");

            choice = Integer.parseInt(terminalInput.nextLine());
            badchoice = choice < 0 || choice > 2;
            if (badchoice)
            {
                badchoice = true;
                System.out.println("Input incorrect! Please try again.");
            }
        }
        while (badchoice);

        return choice;
    }

    public void exit()
    {
        System.out.println("Bye bye!");
        terminalInput.close();
    }

    public void printBoard(char[][] board) {
        System.out.println("Printing board:");
        System.out.println();
        for (int j = 0; j < Board.ROWS; j++) {
            System.out.print("|");
            for (int k = 0; k < Board.COLUMNS; k++)
                System.out.print(board[j][k] + "|");
            System.out.println();
        }
        for (int k = 0; k < 2*Board.COLUMNS+1; k++)
            System.out.print("-");
        System.out.println();
        System.out.println();
    }

    public int humanPlayerTurn(int playerNum)
    {
        System.out.print("Player " + playerNum + ", choose a column: ");
        return Integer.parseInt(terminalInput.nextLine());
    }

    public void computerPlayerTurn(int col)
    {
        System.out.print("Computer put a disk in column ");
        System.out.println(col + 1);
        System.out.println();
    }

    public void showWinner(char winner, boolean isComp, int playerNum) {
        if (winner == ' ')
            System.out.print("Board is full! game has ended with a tie!");
        else if (isComp && playerNum==2)
            System.out.println("Game has ended! The computer won!");
        else
            System.out.println("Game has ended! Player " + playerNum + " won!");

        System.out.println();
    }
}