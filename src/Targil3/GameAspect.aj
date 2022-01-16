package Targil3;

import java.sql.Timestamp;

public aspect GameAspect {

    pointcut BoardLoging(char[][] board) : call(void printBoard(char [][])) && args(board);

    before(char[][] board) : BoardLoging(board)
            {
                System.out.println("Printing board:");
                System.out.println();

                for (int i = 0; i < Board.ROWS; i++)
                {
                    System.out.print("|");
                    for (int j = 0; j < Board.COLUMNS; j++)
                        System.out.print(board[i][j] + "|");
                    System.out.println();
                }

                for (int k = 0; k < 2*Board.ROWS+1; k++)
                    System.out.print("-");

                System.out.println();
            }

    after(char[][] board) : BoardLoging(board)
            {
                System.out.println("---------------------------------------");
            }


    pointcut humanChoiceLoging(int playerNum) : call(int humanPlayerTurn(int)) && args(playerNum);

    before(int playerNum) : humanChoiceLoging(playerNum)
            {
                System.out.print("Player " + playerNum + ", choose a column: ");
                System.out.println();
                System.out.println();
            }

    after(int playerNum) : humanChoiceLoging(playerNum)
            {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                System.out.println(timestamp + "  Player choice");
            }


    pointcut computerPlayerTurn(int col) : call(void computerPlayerTurn(int)) && args(col);

    before(int col): computerPlayerTurn(col)
            {
                System.out.print("Computer put a disk in column ");
                System.out.println(col + 1);
                System.out.println();
            }

    after(int col) : computerPlayerTurn(col)
            {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                System.out.println(timestamp + "  Computer choice");
                System.out.println();
            }
}
