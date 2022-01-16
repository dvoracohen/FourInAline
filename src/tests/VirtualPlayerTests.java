package tests;

import Targil3.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class VirtualPlayerTests {
    public static Board board;
    private UserInterface userInterface;

    @Test
    public void computerFullBoardChoiseTest()
    {
        board = new Board();
        userInterface = new ConsoleUI();

        // fill the board with 'X'
        for (int i=0; i<Board.ROWS; i++) {
            for (int j=0; j<Board.COLUMNS; j++) {
                Board.board[i][j] = Game.X_PLAYER;
            }
        }

        userInterface.printBoard(Board.board);

        VirtualPlayerFactory virtualPlayerFactory = new VirtualPlayerFactory();
        VirtualPlayer computer = virtualPlayerFactory.getVirtualPlayer(GameModes.MEDIUM);

        int computerChoice = computer.MakeAChoise(Board.board);

        // expect invalid column
        assertTrue(computerChoice == -1);

    }

    @Test
    public void computerWinnigChoiseEasyTest()
    {
        int col = 1;
        userInterface = new ConsoleUI();

        // line with 3 discs
        for (int i=Board.ROWS-1; i>2; i--) {
            Board.board[i][col] = Game.O_PLAYER;
        }

        userInterface.printBoard(Board.board);

        VirtualPlayerFactory virtualPlayerFactory = new VirtualPlayerFactory();
        VirtualPlayer computer = virtualPlayerFactory.getVirtualPlayer(GameModes.EASY);

        col = computer.MakeAChoise(Board.board);

        assertTrue(col == 0);

    }

    @Test
    public void computerWinnigChoiseMediumTest()
    {
        int col = 1;

        board = new Board();
        userInterface = new ConsoleUI();

        // line with 3 discs
        for (int i=Board.ROWS-1; i>2; i--) {
            Board.board[i][col] = Game.O_PLAYER;
        }

        userInterface.printBoard(Board.board);

        VirtualPlayerFactory virtualPlayerFactory = new VirtualPlayerFactory();
        VirtualPlayer computer = virtualPlayerFactory.getVirtualPlayer(GameModes.MEDIUM);

        col = computer.MakeAChoise(Board.board);

        assertTrue(col == 1);

    }

}