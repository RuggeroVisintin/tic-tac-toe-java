package tic_tac_toe.app.domain.game.models;

public class InvalidPlayerException extends IllegalArgumentException {
    public InvalidPlayerException(String errorMessage) {
        super(errorMessage);
    }
}
