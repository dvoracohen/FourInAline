package Targil3;

import java.util.Objects;

public class Game {

    public static Board board;
    //public static DecoratedBoard board;

    // how many sequence needs to win the game
    public static int WIN = 4;

    // the sequences
    public static char X_PLAYER = 'X';
    public static char O_PLAYER = 'O';
    public static char EMPTY = ' ';

    public static char currentPlayer; // X_PLAYER or O_PLAYER
    public static boolean gameOver = false;
    public static boolean againstComputer = false;

    // 'O' is player 1, 'X' is player 2
    public static int playerNum (char c)
    {
        if (c == X_PLAYER) {
            return 2;
        }
        else
        {
            return 1;
        }
    }

    public static void swapPlayer() {
        if (currentPlayer == O_PLAYER)
            currentPlayer = X_PLAYER;
        else currentPlayer = O_PLAYER;
    }

    public static boolean isHorizontalWinning(char[][] board, int rowIndex, int colIndex){
        char c = board[rowIndex][colIndex];
        int count = 1;
        // horizontal right
        for (int i=colIndex+1; i < Board.COLUMNS; i++) {
            if (board[rowIndex][i]==c)
                count++;
            else break;
        }
        if (count >= Game.WIN) return true; // won horizontally

        // keep counting horizontal left
        for (int i=colIndex-1; i >=0; i--) {
            if (board[rowIndex][i]==c)
                count++;
            else break;
        }
        if (count >= Game.WIN) return true; // won horizontally

        return false;
    }

    public static boolean isDiagonalWinning(char[][] board, int rowIndex, int colIndex){
        // first diagonal:  /
        int count = 1;
        char c = board[rowIndex][colIndex];

        // up
        int kol = colIndex+1;
        for (int i=rowIndex-1; i >= 0; i--) {
            if (kol>=Board.COLUMNS) break; // we reached the end of the board right side
            if (board[i][kol]==c)
                count++;
            else
                break;
            kol++;
        }
        if (count >= Game.WIN) return true;
        // keep counting down
        kol = colIndex-1;
        for (int i=rowIndex+1; i < Board.ROWS; i++) {
            if (kol<0) break; // we reached the end of the board left side
            if (board[i][kol]==c)
                count++;
            else
                break;
            kol--;
        }
        if (count >= Game.WIN) return true; // won diagonal "/"

        // second diagonal : \
        count = 1;
        // up
        kol = colIndex-1;
        for (int i=rowIndex-1; i >= 0; i--) {
            if (kol<0) break; // we reached the end of the board left side
            if (board[i][kol]==c)
                count++;
            else
                break;
            kol--;
        }
        if (count >= Game.WIN) return true; // won diagonal "\"
        // keep counting down
        kol = colIndex+1;
        for (int i=rowIndex+1; i < Board.ROWS; i++) {
            if (kol>=Board.COLUMNS) break; // we reached the end of the board right side
            if (board[i][kol]==c)
                count++;
            else
                break;
            kol++;
        }
        return count >= Game.WIN; // won diagonal "\"
    }

    public static boolean isVerticalWinning(char[][] board, int rowIndex, int colIndex){
        char c = board[rowIndex][colIndex];
        int count = 1;
        // vertical down
        for (int i=rowIndex+1; i < Board.ROWS; i++) {
            if (board[i][colIndex]==c)
                count++;
            else break;
        }
        if (count >= Game.WIN) return true; // won vertical down

        // keep counting vertical up
        for (int i=rowIndex-1; i >=0; i--) {
            if (board[i][colIndex]==c)
                count++;
            else
                break;
        }
        if (count >= Game.WIN) return true; // won vertical up
        return false;
    }

    // is the disc at board[rowIndex][colIndex] winning?
    public static boolean winningDisc(char[][] board, int rowIndex, int colIndex){

        if(isHorizontalWinning(board, rowIndex, colIndex)) return true;

        if(isVerticalWinning(board, rowIndex, colIndex)) return true;

        if(isDiagonalWinning(board, rowIndex, colIndex)) return true;

        return false;
    }



    public static void startGame()
    {
        UserInterface userInterface;
        userInterface = (UserInterface) DebugProxy.newInstance(new ConsoleUI());

        // each loop is a new game
        while (true) {

            int choice = userInterface.printMenu();

            // 0: quit the game
            if (choice == MenuOptions.Exit.ordinal()) {
                userInterface.exit();
                return;
            }

            board = new Board();
            //board= new DecoratedBoard();

            // start the game
            userInterface.printBoard(Board.board); // empty board
            System.out.println("Starting a game of 'Four in a Line'.");

            currentPlayer = O_PLAYER;
            gameOver = false;
            againstComputer = false;

            VirtualPlayer computer = null;

            if (choice == 2) {
                againstComputer = true;
                VirtualPlayerFactory virtualPlayerFactory = new VirtualPlayerFactory();
                GameModes mode = GameModes.MEDIUM;
                computer = virtualPlayerFactory.getVirtualPlayer(mode);
                //computer = (VirtualPlayer) DebugProxy.newInstance(new VirtualPlayerMedium());
            }

            do {
                // loop as long as the chosen column is full
                // we request the player to enter a column which is not full
                int col;
                int row;
                do {
                    if (againstComputer && currentPlayer == X_PLAYER) {
                        col = Objects.requireNonNull(computer).MakeAChoise(Board.board);
                        userInterface.computerPlayerTurn(col);
                    }
                    else
                    {
                        col = userInterface.humanPlayerTurn(playerNum(currentPlayer));
                        col--; // the real index
                    }

                    row = -1;

                    // is this really a column number?
                    if (col < 0 || col >= Board.COLUMNS)
                    {
                        System.out.println("Illegal column number");
                    }
                    else {
                        // find the row and check if winning
                        if (!Board.isColumnFull(col)) {
                            row = Board.firstEmptyRow(col);
                            System.out.println();
                        } else
                            // column is full, try again
                            System.out.println("Column is full.");
                    }
                } while (row == -1);
                // now we have a valid (row,col) cell

                Board.board[row][col] = currentPlayer;

                // in any case we print the board
                userInterface.printBoard(Board.board);

                if (winningDisc(Board.board, row, col)) {
                    gameOver = true;
                    userInterface.showWinner(currentPlayer, againstComputer, playerNum(currentPlayer));
                } else if (Board.boardIsFull()) {
                    gameOver = true;
                    userInterface.showWinner(EMPTY, againstComputer, playerNum(EMPTY)); // tie
                }
                // switch to next player
                swapPlayer();

            } while (!gameOver);

            DebugProxy.close_inner_file();
        }
    }
}