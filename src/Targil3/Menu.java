package Targil3;

public class Menu {

    // the main menu
    public static char PLAY = '1';
    public static char PLAYCOMPUTER = '2';
    public static char QUIT = '0';

    public static void printMenu() {
        System.out.println(QUIT + ". Exit");
        System.out.println(PLAY + ". Play against a friend");
        System.out.println(PLAYCOMPUTER + ". Play against the computer");
        System.out.print("Please choose an option:");
    }
}
