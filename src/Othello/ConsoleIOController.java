package Othello;

import java.awt.*;
import java.util.Scanner;

public class ConsoleIOController implements IOController {

    private GameLogic gameLogic;

    public void setGameLogic(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }

    @Override
    public void announceTurnLoss() {
        System.out.println("The current player cannot move. The turn has passed to the other player.");
    }

    @Override
    public Point getPointInput() {

        assertBoardInstantiated();
        System.out.println("Enter your Move, player " + (gameLogic.getActiveCellType().toString()) + ".");

        Scanner kb = new Scanner(System.in);
        int row = -1, col = -1;
        do {
            System.out.println("Legal positions are A1 - H8");
            String userMove = kb.next();
            try {
                userMove = userMove.toUpperCase();
                col = userMove.charAt(0) - 'A';
                row = userMove.charAt(1) - '1';
                System.out.println("");
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("That was not a position.");
            }
        } while (row < 0 || col < 0 || row >= 8 || col >= 8);
        return new Point(row, col);
    }

    @Override
    public void updateBoard() {
        assertBoardInstantiated();
        System.out.println("  A B C D E F G H");
        for (int row = 0; row < 8; row++) {
            System.out.print((row + 1) + ":");
            for (int col = 0; col < 8; col++) {
                System.out.print(gameLogic.getCell(row, col).toString() + " ");
            }
            System.out.println("");
        }
    }

    @Override
    public void announceGameEnd() {
        System.out.println("Game over!!!");
        if (gameLogic.getPoints(CellType.O) == gameLogic.getPoints(CellType.X)) {
            System.out.println("It's a tie!");
        } else if (gameLogic.getPoints(CellType.X) > gameLogic.getPoints(CellType.O)) {
            System.out.println("Player X wins!");
        } else {
            System.out.println("Player O wins!");
        }
        System.out.println(String.format("The score was %d (X) to %d (O).", gameLogic.getPoints(CellType.X), gameLogic.getPoints(CellType.O)));
    }

    private void assertBoardInstantiated() {
        if (gameLogic == null) {
            throw new IllegalComponentStateException("The board was never instantiated");
        }
    }
}
