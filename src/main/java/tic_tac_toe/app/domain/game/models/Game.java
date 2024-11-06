package tic_tac_toe.app.domain.game.models;

import java.util.Arrays;
import java.util.UUID;

public class Game implements Cloneable {
    private UUID mUuid;

    private String[][] mBoard;

    private Player mWinner;

    private Player mLastPlayerToMove;

    private int mMovesCount;

    private static String[] mPlayerSymbolsMap = { "X", "O" };

    public

    Game() {
        this.mUuid = UUID.randomUUID();
        this.mBoard = new String[][] { { "", "", "" }, { "", "", "" }, { "", "", "" } };
        this.mWinner = null;
        this.mLastPlayerToMove = null;
        this.mMovesCount = 0;
    }

    public Game clone() {
        Game clone = new Game();

        clone.mUuid = this.mUuid;
        clone.mWinner = this.mWinner;
        clone.mMovesCount = this.mMovesCount;
        clone.mLastPlayerToMove = this.mLastPlayerToMove;

        clone.mBoard[0] = this.mBoard[0].clone();
        clone.mBoard[1] = this.mBoard[1].clone();
        clone.mBoard[2] = this.mBoard[2].clone();

        return clone;
    }

    public static Game fromBoardSnapshot(String[][] board) throws InvalidPlayerException {
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
            result.mLastPlayerToMove = new Player(1);
        } else if (oCount < xCount) {
            result.mLastPlayerToMove = new Player(0);
        }

        result.mMovesCount = xCount + oCount;

        return result;
    }

    public void nextMove(Move move) throws InvalidMoveException, InvalidPlayerException {
        if (this.mWinner != null || this.mMovesCount == 9) {
            throw new InvalidMoveException("Cannot make new moves on a finished game");
        }

        if (move.player().equals(mLastPlayerToMove)) {
            throw new InvalidMoveException("The same player cannot move more than once in a row");
        }

        if (mBoard[move.y().value() - 1][move.x().value() - 1] != "") {
            throw new InvalidMoveException(
                    "Cell [" + move.x() + ", " + move.y() + "] is already taken. Choose another cell");
        }

        mBoard[move.y().value() - 1][move.x().value() - 1] = mPlayerSymbolsMap[move.player().playerId()];
        mLastPlayerToMove = move.player();
        mMovesCount++;

        mWinner = checkWin();
    }

    public boolean isDraft() {
        return this.mMovesCount == 9 && this.mWinner == null;
    }

    @Override
    public boolean equals(Object other) {
        boolean isEqual = false;

        if (other != null && other.getClass() == this.getClass()) {
            Game typedOther = (Game) other;

            isEqual = mUuid.equals(typedOther.mUuid)
                    && Arrays.deepEquals(mBoard, typedOther.mBoard)
                    && mLastPlayerToMove == typedOther.mLastPlayerToMove
                    && mWinner == typedOther.mWinner
                    && mMovesCount == typedOther.mMovesCount;
        }

        return isEqual;
    }

    public UUID getId() {
        return mUuid;
    }

    public String[][] getBoard() {
        return this.mBoard;
    }

    public Player getWinner() {
        return this.mWinner;
    }

    public Player getCurrentPlayer() throws InvalidPlayerException {
        if (this.mLastPlayerToMove == null) {
            return null;
        }

        if (this.mLastPlayerToMove.equals(new Player(0))) {
            return new Player(1);
        }

        return new Player(0);
    }

    private Player checkWin() throws InvalidPlayerException {
        // Check diagonals
        if (this.mBoard[0][0] != "" && this.mBoard[0][0] == this.mBoard[1][1]
                && this.mBoard[1][1] == this.mBoard[2][2]) {

            return this.PlayerFromSymbol(this.mBoard[0][0]);
        }

        if (this.mBoard[0][2] != "" && this.mBoard[0][2] == this.mBoard[1][1]
                && this.mBoard[1][1] == this.mBoard[2][0]) {

            return this.PlayerFromSymbol(this.mBoard[0][2]);
        }

        // Check Rows
        for (int i = 0; i < 3; i++) {
            if (this.mBoard[i][0] != "" && this.mBoard[i][0] == this.mBoard[i][1]
                    && this.mBoard[i][1] == this.mBoard[i][2]) {

                return this.PlayerFromSymbol(this.mBoard[0][i]);
            }
        }

        // Check Columns
        for (int i = 0; i < 3; i++) {
            if (this.mBoard[0][i] != "" && this.mBoard[0][i] == this.mBoard[1][i]
                    && this.mBoard[1][i] == this.mBoard[2][i]) {

                return this.PlayerFromSymbol(this.mBoard[0][i]);
            }
        }

        return null;
    }

    private Player PlayerFromSymbol(String symbol) throws InvalidPlayerException {
        if (symbol == "X")
            return new Player(0);

        if (symbol == "O")
            return new Player(1);

        return null;
    }
}
