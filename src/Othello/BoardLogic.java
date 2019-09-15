package Othello;

import java.awt.*;

public class BoardLogic {

    private static class MoveInfo {

        int row;
        int col;
        CellType cellType;
        Board board;

        MoveInfo(int row, int col, CellType cellType, Board board) {
            this.row = row;
            this.col = col;
            this.cellType = cellType;
            this.board = board;
        }
    }

    static boolean isValidMove(Point point, CellType cellType, Board board) {
        if (!isWithinBoard(point)) {
            return false;
        }

        MoveInfo moveInfo = new MoveInfo(point.x, point.y, cellType, board);

        return canFlip(moveInfo);
    }

    private static boolean isWithinBoard(Point point) {
        if (point.x < 0 || point.x >= 8 || point.y < 0 || point.y >= 8) {
            return false;
        }
        return true;
    }

    private static boolean canFlip(MoveInfo moveInfo) {

        for (int rowIncrement = -1; rowIncrement <= 1; rowIncrement++) {
            for (int colIncrement = -1; colIncrement <= 1; colIncrement++) {
                if (rowIncrement != 0 || colIncrement != 0) {
                    if (checkDirection(moveInfo, rowIncrement, colIncrement)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private static boolean checkDirection(MoveInfo moveInfo, int rowIncrement, int colIncrement) {

        Point nextPoint = new Point(moveInfo.row + rowIncrement, moveInfo.col + colIncrement);

        if (!isWithinBoard(nextPoint)) {
            return false;
        }

        if (moveInfo.board.getCell(nextPoint.x, nextPoint.y).cellType == CellType.NONE) {
            return false;
        }

        if (moveInfo.board.getCell(nextPoint.x, nextPoint.y).cellType == moveInfo.cellType) {
            return false;
        }

        int newRow = moveInfo.row + rowIncrement;
        int newCol = moveInfo.col + colIncrement;
        do {
            if (moveInfo.board.getCell(newRow, newCol).cellType == moveInfo.cellType) {
                return true;
            }

            newRow += rowIncrement;
            newCol += colIncrement;

        } while (isWithinBoard(new Point(newRow, newCol)) && moveInfo.board.getCell(newRow, newCol).cellType != CellType.NONE);
        return false;

    }

    static void doFlip(Point point, CellType activePlayer, Board board) {

        MoveInfo moveInfo = new MoveInfo(point.x, point.y, activePlayer, board);

        boolean isIllegal = true;

        for (int rowIncrement = -1; rowIncrement <= 1; rowIncrement++) {
            for (int colIncrement = -1; colIncrement <= 1; colIncrement++) {
                if (rowIncrement != 0 || colIncrement != 0) {

                    if (checkDirection(moveInfo, rowIncrement, colIncrement)) {
                        doDirectionalFlip(moveInfo, rowIncrement, colIncrement);
                        isIllegal = false;
                    }

                }
            }
        }

        if (isIllegal) {
            throw new IllegalArgumentException("The attempted move is not legal.");
        }

        board.setCell(point.x, point.y, activePlayer);
    }

    private static void doDirectionalFlip(MoveInfo moveInfo, int rowIncrement, int colIncrement) {

        int newRow = moveInfo.row;
        int newCol = moveInfo.col;
        do {
            newRow += rowIncrement;
            newCol += colIncrement;
            if (moveInfo.board.getCell(newRow, newCol).cellType == moveInfo.cellType) {
                do {
                    newRow -= rowIncrement;
                    newCol -= colIncrement;
                    moveInfo.board.getCell(newRow, newCol).cellType = moveInfo.cellType;
                } while (newRow != moveInfo.row || newCol != moveInfo.col);
                return;
            }
        } while (isWithinBoard(new Point(newRow, newCol)) && moveInfo.board.getCell(newRow, newCol).cellType != moveInfo.cellType);

    }
}