package tic_tac_toe.app.application.game.useCases;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import tic_tac_toe.app.application.game.BaseFakeGameRepository;
import tic_tac_toe.app.application.game.representation.GameRepresentation;
import tic_tac_toe.app.application.game.representation.MoveRepresentation;
import tic_tac_toe.app.domain.game.models.Game;
import tic_tac_toe.app.domain.game.models.Move;
import tic_tac_toe.app.domain.game.models.Player;
import tic_tac_toe.app.domain.game.models.coordinate.XCoordinate;
import tic_tac_toe.app.domain.game.models.coordinate.YCoordinate;

public class MakeAMoveUseCaseTests {
    protected class FakeGameRepository extends BaseFakeGameRepository {
        public Game savedGame;

        @Override
        public void save(Game game) {
            savedGame = game.clone();
        }

        @Override
        public Game findById(UUID gameId) {
            return savedGame.clone();
        }
    }

    @Test
    void itShouldApplyTheGivenMoveToTheGiveGame() throws Exception {
        Game newGame = new Game();
        MakeAMoveUseCase useCase = new MakeAMoveUseCase();
        FakeGameRepository gameRepository = new FakeGameRepository();
        gameRepository.savedGame = newGame.clone();

        GameRepresentation updatedGame = useCase.execute(new MoveRepresentation(newGame.getId(), 1,
                1, 1), gameRepository);

        newGame.nextMove(new Move(new Player(1), new XCoordinate(1), new YCoordinate(1)));
        assertArrayEquals(newGame.getBoard(), updatedGame.board());
    }

    @Test
    void itShouldSaveTheGameOnceTheMoveIsApplied() throws Exception {
        MakeAMoveUseCase useCase = new MakeAMoveUseCase();
        FakeGameRepository gameRepository = new FakeGameRepository();
        Game expectedResult = new Game();

        gameRepository.savedGame = new Game();

        useCase.execute(new MoveRepresentation(gameRepository.savedGame.getId(), 1,
                1, 1), gameRepository);

        expectedResult.nextMove(new Move(new Player(1), new XCoordinate(1), new YCoordinate(1)));
        assertArrayEquals(expectedResult.getBoard(), gameRepository.savedGame.getBoard());
    }
}
