package tic_tac_toe.app.infrastructure.game.adapters;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import tic_tac_toe.app.domain.game.models.Game;

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
