package tic_tac_toe.app.application.game.useCases;

import tic_tac_toe.app.domain.game.models.Game;
import tic_tac_toe.app.domain.game.ports.GameRepository;

public class NewGameUseCase {
    public Game execute(GameRepository repository) {
        Game newGame = new Game();

        repository.save(newGame);

        return newGame;
    }
}
