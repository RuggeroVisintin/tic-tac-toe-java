package tic_tac_toe.app.game;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
        void itShouldStartANewGameWithAUniqueId() {
            assertNotNull(game.getId());
        }

        @Test
        void itShouldStartANewGameWithASetOfEmptyMoves() {
            String[][] result = { { "", "", "" }, { "", "", "" }, { "", "", "" } };

            assertArrayEquals(result, game.getBoard());
        }

        @Test
        void itShouldStartANewGameWithNoWinnerSet() {
            assertEquals(-1, game.getWinner());
        }

    }

    @Nested
    class NextMoveTests {

        @Test
        void itShouldRegisterTheMoveInTheGameBoard() {
            game.nextMove(1, 3, 2);

            assertArrayEquals(new String[][] {
                    { "", "", "" },
                    { "", "", "O" },
                    { "", "", "" }
            }, game.getBoard());
        }
    }
}
