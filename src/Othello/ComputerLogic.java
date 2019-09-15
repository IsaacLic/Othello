package Othello;

import java.awt.*;

public class ComputerLogic {

    public static Point getMove() {
        //TODO
        return null;
    }
}

/*
    private class OthelloComputer {

        private CellType[][][] tempBoard;
        CellType computerPlayerCellType;
        private CellType currentHypotheticalPlayer;

        private void toggleHypotheticalPlayer() {
            currentHypotheticalPlayer = (currentHypotheticalPlayer == CellType.X ? CellType.O : CellType.X);
        }

        private void copyBoard(CellType[][] copySource, CellType[][] copyDestination) {
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    copyDestination[row][col] = copySource[row][col];
                }
            }
        }

        public void makeMove() {
            computerPlayerCellType = isPlayerOneMove ? CellType.X : CellType.O;
            currentHypotheticalPlayer = computerPlayerCellType;
            double bestMove = 0;
            int bestRow = 0;
            int bestCol = 0;
            tempBoard = new CellType[8][8][8];
            System.out.println("");
            for (int x = 0; x < 8; x++) {
                copyBoard(grid, tempBoard[x]);
            }
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    if (isValidMove(row, col, tempBoard[7], computerPlayerCellType)) {
                        copyBoard(tempBoard[7], tempBoard[6]);
                        double thisMove = recursiveTryMove(7);
                        copyBoard(grid, tempBoard[7]);
                        if (thisMove > bestMove) {
                            bestMove = thisMove;
                            bestRow = row;
                            bestCol = col;
                        }
                    }
                }
            }
            System.out.println("Here's my move:");
            System.out.println("");
            FlipIfLegal(bestRow, bestCol, grid, computerPlayerCellType);
            isPlayerOneMove = !isPlayerOneMove;
            currentHypotheticalPlayer = CellType.NONE;
        }

        private double recursiveTryMove(int iterations) {
            if (iterations == 0 || iterations == 1) {
//                display(tempBoard[iterations]);
                return isPlayerOneMove ? getXValue() : getOValue();
            }
            double sumOfMoveValues = 0;
            int validMoveCount = 0;
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    if (isValidMove(row, col, tempBoard[iterations], currentHypotheticalPlayer)) {
                        validMoveCount++;
                        toggleHypotheticalPlayer();
                        copyBoard(tempBoard[iterations - 1], tempBoard[iterations - 2]);
                        double thisMove = recursiveTryMove(iterations - 1);
                        copyBoard(tempBoard[iterations], tempBoard[iterations - 1]);
                        toggleHypotheticalPlayer();
                        sumOfMoveValues += thisMove;
                        validMoveCount++;
                    }
                }
            }
            return sumOfMoveValues / validMoveCount; //average of all possibilities in the tree
        }

        private int getXValue() {
            int sum = 0;
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    if (tempBoard[0][row][col] == CellType.X) {

                        sum++;

                        if (row == 0) {
                            if (col == 0 || col == 7) {
                                sum += 6;
                            }
                        }

                        if (row == 7) {
                            if (col == 0 || col == 7) {
                                sum += 6;
                            }
                        }

                    }
                }
            }

            return sum;
        }

        private int getOValue() {
            int sum = 0;
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    if (tempBoard[0][row][col] == CellType.O) {
                        sum++;

                        if (row == 0) {
                            if (col == 0 || col == 7) {
                                sum += 6;
                            }
                        }

                        if (row == 7) {
                            if (col == 0 || col == 7) {
                                sum += 6;
                            }
                        }

                    }
                }
            }
            return sum;
        }

    }
*/