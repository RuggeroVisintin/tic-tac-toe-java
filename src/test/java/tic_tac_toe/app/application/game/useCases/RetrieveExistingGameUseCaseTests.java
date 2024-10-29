package tic_tac_toe.app.application.game.useCases;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import tic_tac_toe.app.application.game.BaseFakeGameRepository;
import tic_tac_toe.app.application.game.representation.GameRepresentation;
import tic_tac_toe.app.domain.game.models.Game;
import tic_tac_toe.app.domain.game.models.PlayerID;

public class RetrieveExistingGameUseCaseTests {
    protected class FakeGameRepository extends BaseFakeGameRepository {
        public Game savedGame;

        @Override
        public Game findById(UUID gameId) {
            return savedGame;
        }

        @Override
        public void save(Game game) {
            savedGame = game;
        }
    }

    @Test
    void itShouldRetrieveAnExistingGameStatus() throws Exception {
        FakeGameRepository gameRepository = new FakeGameRepository();
        gameRepository.savedGame = new Game();
        gameRepository.savedGame.nextMove(new PlayerID(1), 1, 1);

        GameRepresentation retrievedGame = (new RetrieveExistingGameUseCase().execute(gameRepository.savedGame.getId(),
                gameRepository));

        assertArrayEquals(new GameRepresentation(gameRepository.savedGame).getBoard(), retrievedGame.getBoard());
    }
}
