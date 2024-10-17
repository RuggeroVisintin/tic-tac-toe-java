package tic_tac_toe.app.game;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        void itShouldRegisterTheMoveInTheGameBoard() throws Exception {
            game.nextMove(1, 3, 2);

            assertArrayEquals(new String[][] {
                    { "", "", "" },
                    { "", "", "O" },
                    { "", "", "" }
            }, game.getBoard());
        }

        @Test
        void itShouldResisterXOnTheBoardWhenPlayer0Moves() throws Exception {
            game.nextMove(0, 3, 2);

            assertArrayEquals(new String[][] {
                    { "", "", "" },
                    { "", "", "X" },
                    { "", "", "" }
            }, game.getBoard());
        }

        @Test
        void itShouldResisterOOnTheBoardWhenPlayer0Moves() throws Exception {
            game.nextMove(1, 3, 2);

            assertArrayEquals(new String[][] {
                    { "", "", "" },
                    { "", "", "O" },
                    { "", "", "" }
            }, game.getBoard());
        }

        @Test
        void itShouldThrowIfTheSamePlayerMovesMoreThanOnceInARow() throws Exception {
            game.nextMove(1, 1, 1);

            assertThrows(Exception.class, () -> game.nextMove(1, 2, 1));
        }

        @Test
        void itShouldThrowIfAPlayerTargetsACellThatIsAlreadyTaken() throws Exception {
            game.nextMove(1, 1, 1);

            assertThrows(Exception.class, () -> game.nextMove(2, 1, 1));
        }

        @Test
        void itShouldThrowIfPlayerIdIsNotValid() throws Exception {
            assertThrows(Exception.class, () -> game.nextMove(2, 1, 1));
        }
    }
}
