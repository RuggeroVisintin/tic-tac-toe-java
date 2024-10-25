package tic_tac_toe.app.application.game.useCases;

import org.junit.jupiter.api.Test;

import tic_tac_toe.app.domain.game.models.Game;
import tic_tac_toe.app.domain.game.ports.GameRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.UUID;

class FakeGameRepository implements GameRepository {
    public Game savedGame;

    @Override
    public void save(Game game) {
        // TODO Auto-generated method stub

        savedGame = game;
    }

    @Override
    public Game findById(UUID gameId) {
        // TODO Auto-generated method stub
        return null;
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

    @Test
    void itShouldSaveANewlyCreatedGame() {
        FakeGameRepository gameRepo = new FakeGameRepository();

        Game newGame = new NewGameUseCase()
                .execute(gameRepo);

        assertEquals(newGame, gameRepo.savedGame);
    }
}