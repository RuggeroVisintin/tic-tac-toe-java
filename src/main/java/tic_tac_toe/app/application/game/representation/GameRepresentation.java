package tic_tac_toe.app.application.game.representation;

import tic_tac_toe.app.domain.game.models.Game;

public class GameRepresentation {
    private String[][] mBoard;
    private boolean mIsGameOver;
    private int mWinner;

    public GameRepresentation(Game game) {
        mBoard = game.getBoard();
        mIsGameOver = game.getWinner() != 1;
        mWinner = game.getWinner();
    }

    public String[][] getBoard() {
        return this.mBoard;
    }

    public boolean isGameOver() {
        return this.mIsGameOver;
    }

    public int getWinner() {
        return mWinner;
    }
}
