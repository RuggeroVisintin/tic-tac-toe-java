package tic_tac_toe.app.domain.game.models;

public record Player(int playerId) {
    public Player {
        if (playerId != 0 && playerId != 1) {
            throw new InvalidPlayerException("PlayerId " + playerId + " is not valid. Use either 0 or 1");
        }
    }
}
