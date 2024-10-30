package tic_tac_toe.app.infrastructure.game.adapters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import tic_tac_toe.app.domain.game.models.Game;
import tic_tac_toe.app.domain.game.models.GameNotFoundException;

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
    }

    @Nested
    class SaveGameTests {

        @Test
        void itShouldPersistAGameState() {
            Game newGame = new Game();

            InMemoryGameRepository repository = new InMemoryGameRepository();
            repository.save(newGame);

            Game foundGame = repository.findById(newGame.getId());

            assertEquals(newGame, foundGame);
        }
    }
}
