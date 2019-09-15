package Othello;

import java.awt.Point;

public interface IOController {

    public void setGameLogic(GameLogic gameLogic);

    public void announceTurnLoss();

    public Point getPointInput();

    public void updateBoard();

    public void announceGameEnd();
}
