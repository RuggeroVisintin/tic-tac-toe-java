package tic_tac_toe.app.application.game.representation;

import java.util.UUID;

public class MoveRepresentation {
    private UUID mGameId;
    private int mX;
    private int mY;
    private int mPlayerId;

    public MoveRepresentation(UUID gameId, int playerId, int x, int y) {
        mGameId = gameId;
        mX = x;
        mY = y;
        mPlayerId = playerId;
    }

    public UUID getGameId() {
        return mGameId;
    }

    public int getX() {
        return mX;
    }

    public int getY() {
        return mY;
    }

    public int playerId() {
        return mPlayerId;
    }
}
