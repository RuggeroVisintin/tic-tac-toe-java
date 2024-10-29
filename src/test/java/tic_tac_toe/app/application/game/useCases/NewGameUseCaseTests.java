package tic_tac_toe.app.application.game.useCases;

import org.junit.jupiter.api.Test;

import tic_tac_toe.app.application.game.BaseFakeGameRepository;
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

        Game newGame = new NewGameUseCase()
                .execute(gameRepo);

        Game newGame2 = new NewGameUseCase()
                .execute(gameRepo);

        assertNotEquals(newGame.getId(), newGame2.getId());
    }

    @Test
    void itShouldSaveANewlyCreatedGame() {
        FakeGameRepository gameRepo = new FakeGameRepository();

        Game newGame = new NewGameUseCase()
                .execute(gameRepo);

        assertEquals(newGame, gameRepo.savedGame);
    }
}