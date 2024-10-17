package tic_tac_toe.app.game;

import java.util.UUID;

public class Game {
    private UUID mUuid;

    private String[][] mBoard;

    private int mWinner;

    public Game() {
        this.mUuid = UUID.randomUUID();
        this.mBoard = new String[][] { { "", "", "" }, { "", "", "" }, { "", "", "" } };
        this.mWinner = -1;
    }

    public void nextMove(int playerId, int x, int y) {
        mBoard[y - 1][x - 1] = "O";
    }

    public UUID getId() {
        return mUuid;
    }

    public String[][] getBoard() {
        return this.mBoard;
    }

    public int getWinner() {
        return this.mWinner;
    }
}