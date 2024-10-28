package tic_tac_toe.app.application.game.controllers;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import tic_tac_toe.app.domain.game.models.Game;
import tic_tac_toe.app.domain.game.ports.GameRepository;

import java.util.UUID;

class FakeGameRepository implements GameRepository {
    public Game savedGame;

    @Override
    public void save(Game game) {
        savedGame = game;
    }

    @Override
    public Game findById(UUID gameId) {
        return null;
    }
}

public class GameControllerTests {
    @Nested
    class PostNewGameTests {
        @Test
        void itShouldAddANewTestInGivenRepository() {
            FakeGameRepository repository = new FakeGameRepository();
            GameController controller = new GameController(repository);

            controller.newGame();

            assertNotNull(repository.savedGame);
        }

        @Test
        void itShouldReturnANewlyCreatedGame() {
            FakeGameRepository repository = new FakeGameRepository();
            GameController controller = new GameController(repository);

            Game newGame = controller.newGame();
            assertNotNull(newGame);
        }
    }
}
