package tic_tac_toe.app.application.game.useCases;

import org.junit.jupiter.api.Test;

import tic_tac_toe.app.domain.game.models.Game;
import tic_tac_toe.app.domain.game.ports.GameRepository;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.UUID;

class FakeGameRepository implements GameRepository {
    public Game findById(UUID id) {
        return new Game();
    }

    public void save(Game game) {

    }
}

class NewGameUseCaseTests {

    @Test
    void itShouldCreateAndReturnANewGame() {
        FakeGameRepository gameRepo = new FakeGameRepository();

        Game newGame = new NewGameUseCase()
                .execute(gameRepo);

        Game newGame2 = new NewGameUseCase()
                .execute(gameRepo);

        assertNotEquals(newGame.getId(), newGame2.getId());
    }
}