package tic_tac_toe.app.domain.game.models;

public class PlayerID {
    private int mPlayerId;

    public PlayerID(int playerId) throws InvalidPlayerException {
        if (playerId != 0 && playerId != 1) {
            throw new InvalidPlayerException("PlayerId " + playerId + " is not valid. Use either 0 or 1");
        }

        mPlayerId = playerId;
    }

    @Override
    public boolean equals(Object other) {
        boolean isEqual = false;

        if (other != null && other.getClass() == this.getClass()) {
            PlayerID typedOther = (PlayerID) other;

            isEqual = typedOther.mPlayerId == mPlayerId;
        }

        return isEqual;
    }

    public int toInt() {
        return mPlayerId;
    }
}
