package Targil3;

import java.io.IOException;
import java.io.PrintWriter;

public class LogUI extends UserInterfaceDecorator{
    private static PrintWriter writer;

    public LogUI(UserInterface userInterface)
    {
        super(userInterface);

        try{
            writer = new PrintWriter("Board capture");
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void printBoard(char[][] board)
    {
        super.printBoard(board);
        captureBoard(board);
    }

    private void captureBoard(char[][] board) {
        writer.println("Printing board:");
        writer.println();
        for (int j = 0; j < Board.ROWS; j++) {
            writer.print("|||");
            for (int k = 0; k < Board.COLUMNS; k++)
                writer.print(board[j][k] + "|");
            writer.println();
        }
        for (int k = 0; k < 2*Board.COLUMNS+1; k++)
            writer.print("-");
        writer.println();
        writer.println();
    }

    public static void closeFile()
    {
        writer.close();
    }
}
