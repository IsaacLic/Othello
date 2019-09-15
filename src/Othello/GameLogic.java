package Othello;

import java.awt.*;
import java.util.Scanner;

public class GameLogic {

    private Board board;
    private boolean isPlayerOneTurn;
    private boolean isComputerGame;
    private IOController ioController;

    public GameLogic(IOController ioController) {
        this.ioController = ioController;
        ioController.setGameLogic(this);
        this.board = new Board();
        isPlayerOneTurn = true;
    }

    public void createOnePlayerGame() {
        isComputerGame = true;
        playGame();
    }

    public void createTwoPlayerGame() {
        isComputerGame = false;
        playGame();
    }

    public void playGame() {

        while (!isWinner()) {
            doTurn();
        }

        endGame();
    }

    private boolean isWinner() {

        return (!canActivePlayerMove() && !canOtherPlayerMove());

    }

    private boolean canActivePlayerMove() {
        for (Cell cell : board) {
            if (BoardLogic.isValidMove(cell.getPosition(), getActiveCellType(), board)){
                return true;
            }
        }
        return false;
    }

    private boolean canOtherPlayerMove() {
        for (Cell cell : board) {
            if (BoardLogic.isValidMove(cell.getPosition(), getOtherCellType(), board))
                return false;
        }
        return true;
    }

    private void doTurn() {
        if (canActivePlayerMove()) {
            updateBoard();
            Point point = getMove();
            BoardLogic.doFlip(point, getActiveCellType(), board);
        } else {
            ioController.announceTurnLoss();
        }
        changeActivePlayer();
    }

    private void updateBoard() {
        ioController.updateBoard();
    }

    private Point getMove() {
        if (isComputerMove()) {
            return ComputerLogic.getMove();
        }
        return getLegalPlayerMove();
    }

    private boolean isComputerMove() {
        if (isComputerGame && !isPlayerOneTurn) {
            return true;
        }
        return false;
    }

    private Point getLegalPlayerMove() {
        Point point;
        do {
            point = ioController.getPointInput();
        } while (!BoardLogic.isValidMove(point, getActiveCellType(), board));
        return point;
    }

    private void endGame() {
        updateBoard();
        ioController.announceGameEnd();
    }

    public CellType getActiveCellType() {
        if (isPlayerOneTurn) {
            return CellType.X;
        }
        return CellType.O;
    }

    private CellType getOtherCellType() {
        if (isPlayerOneTurn) {
            return CellType.O;
        }
        return CellType.X;
    }

    private void changeActivePlayer() {
        isPlayerOneTurn = !isPlayerOneTurn;
    }

    public int getPoints(CellType cellType) {
        int count = 0;
        for (Cell cell : board) {
            if (cell.cellType == cellType) {
                count++;
            }
        }
        return count;
    }

    public Cell getCell(int row, int col){
        return board.getCell(row, col);
    }
}
