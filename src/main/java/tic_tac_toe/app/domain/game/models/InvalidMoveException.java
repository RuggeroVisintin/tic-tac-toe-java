package tic_tac_toe.app.domain.game.models;

public class InvalidMoveException extends Exception {
    public InvalidMoveException(String errorMessage) {
        super(errorMessage);
    }
}
