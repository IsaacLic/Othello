package Othello;

import java.util.Iterator;
import java.util.NoSuchElementException;



public class Board implements Iterable<Cell>{

    public final int ROW_COUNT = 8;
    public final int COL_COUNT = 8;
    private Cell[][] cellBoard;

    public Board() {

        cellBoard = new Cell[8][8];
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                cellBoard[row][col] = new Cell(row, col);
            }
        }
        cellBoard[3][3].cellType = CellType.X;
        cellBoard[4][4].cellType = CellType.X;

        cellBoard[3][4].cellType = CellType.O;
        cellBoard[4][3].cellType = CellType.O;

    }

    public Cell getCell(int row, int col){
        return cellBoard[row][col];
    }

    public void setCell(int row, int col, CellType cellType){
        cellBoard[row][col].cellType = cellType;
    }

    public void setToDefault() {

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                cellBoard[row][col].cellType = CellType.NONE;
            }
        }

        cellBoard[3][3].cellType = CellType.X;
        cellBoard[4][4].cellType = CellType.X;

        cellBoard[3][4].cellType = CellType.O;
        cellBoard[4][3].cellType = CellType.O;
    }

    public void copyBoard(Board that) {

        for (int r = 0; r < ROW_COUNT; r++) {
            for (int c = 0; c < ROW_COUNT; c++) {
                this.cellBoard[r][c] = that.cellBoard[r][c];
            }
        }
    }

    public Iterator iterator() {
        return new gameStateIterator();
    }

    public class gameStateIterator implements Iterator<Cell> {

        private int currentRow;
        private int currentCol;

        public gameStateIterator() {
            currentRow = 0;
            currentCol = 0;
        }

        @Override
        public boolean hasNext() {
            if (currentRow > 7) {
                return false;
            }
            return true;
        }

        @Override
        public Cell next() {
            if(!hasNext()){
                throw new NoSuchElementException("There are no more elements.");
            }
            Cell nextCell = cellBoard[currentRow][currentCol];

            currentCol++;
            if (currentCol > 7){
                currentCol = 0;
                currentRow++;
            }

            return nextCell;
        }
    }

}