package tic_tac_toe.app.application.game.useCases;

import org.junit.jupiter.api.Test;

import tic_tac_toe.app.application.game.BaseFakeGameRepository;
import tic_tac_toe.app.application.game.representation.GameRepresentation;
import tic_tac_toe.app.domain.game.models.Game;
import tic_tac_toe.app.domain.game.ports.GameRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class NewGameUseCaseTests {
    protected class FakeGameRepository extends BaseFakeGameRepository {
        public Game savedGame;

        @Override
        public void save(Game game) {
            savedGame = game;
        }
    }

    @Test
    void itShouldCreateAndReturnANewGame() {
        GameRepository gameRepo = new BaseFakeGameRepository();

        GameRepresentation newGame = new NewGameUseCase()
                .execute(gameRepo);

        GameRepresentation newGame2 = new NewGameUseCase()
                .execute(gameRepo);

        assertNotEquals(newGame.getId(), newGame2.getId());
    }

    @Test
    void itShouldSaveANewlyCreatedGame() {
        FakeGameRepository gameRepo = new FakeGameRepository();

        GameRepresentation result = new NewGameUseCase()
                .execute(gameRepo);

        GameRepresentation expected = new GameRepresentation(gameRepo.savedGame);

        assertEquals(expected.getId(), result.getId());
    }
}