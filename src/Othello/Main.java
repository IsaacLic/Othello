package Othello;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        GameLogic game = new GameLogic(new ConsoleIOController());
        Scanner kb = new Scanner(System.in);
        System.out.println("Would you like to play a 1 player game? Or 2?");
        String input = kb.next();
        while(input.charAt(0) != '1' && input.charAt(0) != '2'){
            System.out.println("Please type '1' or '2'");
            input = kb.next();
        }
        if (input.charAt(0) == '2')
            game.createTwoPlayerGame();
        else
            game.createOnePlayerGame();

        game.playGame();
    }

}