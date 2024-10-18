package tic_tac_toe.app.game;

import java.util.UUID;

public class Game {
    private UUID mUuid;

    private String[][] mBoard;

    private int mWinner;

    private int mLastPlayerToMove;

    private static String[] mPlayerSymbolsMap = { "X", "O" };

    public Game() {
        this.mUuid = UUID.randomUUID();
        this.mBoard = new String[][] { { "", "", "" }, { "", "", "" }, { "", "", "" } };
        this.mWinner = -1;
        this.mLastPlayerToMove = -1;
    }

    public static Game fromBoardSnapshot(String[][] board) {
        Game result = new Game();
        result.mBoard = board;

        return result;
    }

    public void nextMove(int playerId, int x, int y) throws Exception {
        if (playerId != 0 && playerId != 1) {
            throw new Exception("PlayerId " + playerId + " is not valid. Use either 0 or 1");
        }

        if (playerId == mLastPlayerToMove) {
            throw new Exception("The same player cannot move more than once in a row");
        }

        if (mBoard[y - 1][x - 1] != "") {
            throw new Exception("Cell [" + x + ", " + 2 + "] is already taken. Choose another cell");
        }

        mBoard[y - 1][x - 1] = mPlayerSymbolsMap[playerId];
        mLastPlayerToMove = playerId;
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