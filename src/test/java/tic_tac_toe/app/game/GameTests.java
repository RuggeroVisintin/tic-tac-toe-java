package tic_tac_toe.app.game;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class GameTests {
    protected Game game;

    @BeforeEach
    void createNewGame() {
        game = new Game();
    }

    @Nested
    class NewGameTests {

        @Test
        void itShouldConstructANewGameWithAUniqueId() {
            assertNotNull(game.getId());
        }

        @Test
        void itShouldStartWithASetOfEmptyMoves() {
            String[][] result = { { "", "", "" }, { "", "", "" }, { "", "", "" } };

            assertArrayEquals(game.getBoard(), result);
        }

    }
}
