package Othello;

import java.awt.*;

enum CellType {
    NONE, O, X
};

public class Cell {

    private final int ROW;
    private final int COL;
    public CellType cellType;

    public Cell(int row, int col, CellType type){
        this.ROW = row;
        this.COL = col;
        this.cellType = type;
    }

    public Cell(int row, int col){
        this.ROW = row;
        this.COL = col;
        this.cellType = CellType.NONE;
    }

    public Point getPosition(){
        return new Point(this.ROW, this.COL);
    }

    public String toString(){
        if (cellType == CellType.NONE){
            return " ";
        }
        return cellType.toString();
    }
}
