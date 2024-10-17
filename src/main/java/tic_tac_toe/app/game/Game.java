package tic_tac_toe.app.game;

import java.util.UUID;

public class Game {
    private UUID uuid;

    private String[][] mBoard;

    private int mWinner;

    public Game() {
        this.uuid = UUID.randomUUID();
        this.mBoard = new String[][] { { "", "", "" }, { "", "", "" }, { "", "", "" } };
        this.mWinner = -1;
    }

    public UUID getId() {
        return uuid;
    }

    public String[][] getBoard() {
        return this.mBoard;
    }

    public int getWinner() {
        return this.mWinner;
    }
}