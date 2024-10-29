package tic_tac_toe.app.application.game.useCases;

import tic_tac_toe.app.application.game.representation.GameRepresentation;
import tic_tac_toe.app.domain.game.models.Game;
import tic_tac_toe.app.domain.game.ports.GameRepository;

public class NewGameUseCase {
    public GameRepresentation execute(GameRepository repository) {
        Game newGame = new Game();

        repository.save(newGame);

        return new GameRepresentation(newGame);
    }
}
