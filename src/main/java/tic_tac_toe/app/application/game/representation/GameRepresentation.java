package tic_tac_toe.app.application.game.representation;

import java.util.UUID;

import tic_tac_toe.app.domain.game.models.Game;

public class GameRepresentation {
    private String[][] mBoard;
    private boolean mIsGameOver;
    private int mWinner;
    private UUID mId;

    public GameRepresentation(Game game) {
        mBoard = game.getBoard();
        mIsGameOver = game.getWinner() != null;
        mWinner = mIsGameOver ? game.getWinner().toInt() : -1;
        mId = game.getId();
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

    public UUID getId() {
        return mId;
    }
}
