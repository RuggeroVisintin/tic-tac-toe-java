package tic_tac_toe.app.domain.game.models;

import java.util.NoSuchElementException;

public class GameNotFoundException extends NoSuchElementException {
    public GameNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
