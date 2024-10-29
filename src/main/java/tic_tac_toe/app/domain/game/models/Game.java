package tic_tac_toe.app.domain.game.models;

import java.util.UUID;

public class Game {
    private UUID mUuid;

    private String[][] mBoard;

    private int mWinner;

    private int mLastPlayerToMove;

    private int mMovesCount;

    private static String[] mPlayerSymbolsMap = { "X", "O" };

    public Game() {
        this.mUuid = UUID.randomUUID();
        this.mBoard = new String[][] { { "", "", "" }, { "", "", "" }, { "", "", "" } };
        this.mWinner = -1;
        this.mLastPlayerToMove = -1;
        this.mMovesCount = 0;
    }

    public Game clone() {
        Game clone = new Game();

        clone.mUuid = this.mUuid;
        clone.mWinner = this.mWinner;
        clone.mMovesCount = this.mMovesCount;
        clone.mLastPlayerToMove = this.mMovesCount;
        clone.mBoard = this.mBoard;

        return clone;
    }

    public static Game fromBoardSnapshot(String[][] board) {
        Game result = new Game();
        result.mBoard = board;

        result.mWinner = result.checkWin();

        int xCount = 0;
        int oCount = 0;

        for (int i = 0; i < result.mBoard.length; i++) {
            for (int j = 0; j < result.mBoard[i].length; j++) {
                if (result.mBoard[i][j] == "O") {
                    oCount++;
                } else if (result.mBoard[i][j] == "X") {
                    xCount++;
                }
            }
        }

        if (xCount < oCount) {
            result.mLastPlayerToMove = 1;
        } else if (oCount < xCount) {
            result.mLastPlayerToMove = 0;
        }

        result.mMovesCount = xCount + oCount;

        return result;
    }

    public void nextMove(int playerId, int x, int y) throws Exception {
        if (this.mWinner != -1 || this.mMovesCount == 9) {
            throw new Exception("Cannot make new moves on a finished game");
        }

        if (playerId != 0 && playerId != 1) {
            throw new Exception("PlayerId " + playerId + " is not valid. Use either 0 or 1");
        }

        if (playerId == mLastPlayerToMove) {
            throw new Exception("The same player cannot move more than once in a row");
        }

        if (mBoard[y - 1][x - 1] != "") {
            throw new Exception("Cell [" + x + ", " + y + "] is already taken. Choose another cell");
        }

        mBoard[y - 1][x - 1] = mPlayerSymbolsMap[playerId];
        mLastPlayerToMove = playerId;
        mMovesCount++;

        mWinner = checkWin();
    }

    public boolean isDraft() {
        return this.mMovesCount == 9 && this.mWinner == -1;
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

    public int getCurrentPlayer() {
        if (this.mLastPlayerToMove == 0)
            return 1;

        return 0;
    }

    private int checkWin() {
        // Check diagonals
        if (this.mBoard[0][0] != "" && this.mBoard[0][0] == this.mBoard[1][1]
                && this.mBoard[1][1] == this.mBoard[2][2]) {

            return this.playerIdFromSymbol(this.mBoard[0][0]);
        }

        if (this.mBoard[0][2] != "" && this.mBoard[0][2] == this.mBoard[1][1]
                && this.mBoard[1][1] == this.mBoard[2][0]) {

            return this.playerIdFromSymbol(this.mBoard[0][2]);
        }

        // Check Rows
        for (int i = 0; i < 3; i++) {
            if (this.mBoard[i][0] != "" && this.mBoard[i][0] == this.mBoard[i][1]
                    && this.mBoard[i][1] == this.mBoard[i][2]) {

                return this.playerIdFromSymbol(this.mBoard[0][i]);
            }
        }

        // Check Columns
        for (int i = 0; i < 3; i++) {
            if (this.mBoard[0][i] != "" && this.mBoard[0][i] == this.mBoard[1][i]
                    && this.mBoard[1][i] == this.mBoard[2][i]) {

                return this.playerIdFromSymbol(this.mBoard[0][i]);
            }
        }

        return -1;
    }

    private int playerIdFromSymbol(String symbol) {
        if (symbol == "X")
            return 0;

        if (symbol == "O")
            return 1;

        return -1;
    }
}