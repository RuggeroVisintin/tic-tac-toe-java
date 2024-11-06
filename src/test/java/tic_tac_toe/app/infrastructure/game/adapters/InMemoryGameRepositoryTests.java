package tic_tac_toe.app.infrastructure.game.adapters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import tic_tac_toe.app.domain.game.models.Game;
import tic_tac_toe.app.domain.game.models.GameNotFoundException;
import tic_tac_toe.app.domain.game.models.Move;
import tic_tac_toe.app.domain.game.models.Player;
import tic_tac_toe.app.domain.game.models.coordinate.XCoordinate;
import tic_tac_toe.app.domain.game.models.coordinate.YCoordinate;

public class InMemoryGameRepositoryTests {

    @Nested
    class FindGameByIdTests {

        @Test
        void itShouldFindAGameByItsId() {
            Game newGame = new Game();

            InMemoryGameRepository repository = new InMemoryGameRepository();
            repository.save(newGame);

            assertNotNull(repository.findById(newGame.getId()));
        }

        @Test
        void itShouldThrowIfTheGameIsNotFound() throws Exception {
            InMemoryGameRepository repository = new InMemoryGameRepository();
            UUID randomUuid = UUID.randomUUID();

            Exception exception = assertThrows(GameNotFoundException.class,
                    () -> repository.findById(randomUuid));

            assertEquals("Could not find game with id " + randomUuid, exception.getMessage());
        }

        @Test
        void itShouldReturnACloneOfTheGameObjectToAvoidImplicitySaveOperations() {
            Game newGame = new Game();

            InMemoryGameRepository repository = new InMemoryGameRepository();
            repository.save(newGame);

            Game savedGame = repository.findById(newGame.getId());

            savedGame.nextMove(new Move(new Player(1), new XCoordinate(1), new YCoordinate(1)));

            assertNotEquals(savedGame, repository.findById(newGame.getId()));
        }
    }

    @Nested
    class SaveGameTests {

        @Test
        void itShouldPersistANewGameState() {
            Game newGame = new Game();

            InMemoryGameRepository repository = new InMemoryGameRepository();
            repository.save(newGame);

            Game foundGame = repository.findById(newGame.getId());

            assertEquals(newGame, foundGame);
        }

        @Test
        void itShouldUpdateAnExistingGameState() {
            Game newGame = new Game();

            InMemoryGameRepository repository = new InMemoryGameRepository();
            repository.save(newGame);

            newGame.nextMove(new Move(new Player(1), new XCoordinate(1), new YCoordinate(1)));
            repository.save(newGame);

            assertEquals(newGame, repository.findById(newGame.getId()));
        }
    }
}
