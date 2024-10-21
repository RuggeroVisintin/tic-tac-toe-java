package tic_tac_toe.app.game.useCases;

import tic_tac_toe.app.game.models.Game;

public class NewGameUseCase {

    public Game execute() {
        return new Game();
    }
}
