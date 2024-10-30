package tic_tac_toe.app.domain.game.models;

public class InvalidMoveException extends IllegalArgumentException {
    public InvalidMoveException(String errorMessage) {
        super(errorMessage);
    }
}
