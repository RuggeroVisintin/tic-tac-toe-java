package tic_tac_toe.app.game;

import java.util.UUID;

public class Game {
    private UUID uuid;

    private String[][] mBoard;

    public Game() {
        this.uuid = UUID.randomUUID();
        this.mBoard = new String[][] { { "", "", "" }, { "", "", "" }, { "", "", "" } };
    }

    public UUID getId() {
        return uuid;
    }

    public String[][] getBoard() {
        return this.mBoard;
    }
}