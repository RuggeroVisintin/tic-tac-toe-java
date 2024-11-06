package tic_tac_toe.app.application.game.useCases;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import tic_tac_toe.app.application.game.BaseFakeGameRepository;
import tic_tac_toe.app.application.game.representation.GameRepresentation;
import tic_tac_toe.app.domain.game.models.Game;
import tic_tac_toe.app.domain.game.models.Move;
import tic_tac_toe.app.domain.game.models.Player;
import tic_tac_toe.app.domain.game.models.coordinate.XCoordinate;
import tic_tac_toe.app.domain.game.models.coordinate.YCoordinate;

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
        gameRepository.savedGame.nextMove(new Move(new Player(1), new XCoordinate(1), new YCoordinate(1)));

        GameRepresentation retrievedGame = (new RetrieveExistingGameUseCase().execute(gameRepository.savedGame.getId(),
                gameRepository));

        assertArrayEquals(GameRepresentation.fromGame(gameRepository.savedGame).board(), retrievedGame.board());
    }
}
