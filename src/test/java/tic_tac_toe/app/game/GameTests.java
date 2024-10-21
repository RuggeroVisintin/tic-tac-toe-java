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
    class FromBoardSnapshotMethod {
        @Test
        void itShouldInitializeANewGameFromABoardSnaposhot() {
            Game newGame = Game.fromBoardSnapshot(BoardFixtures.moveToWinOnTopLeftToBottomRightDiagonal());

            assertArrayEquals(BoardFixtures.moveToWinOnTopLeftToBottomRightDiagonal(), newGame.getBoard());
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

            Exception exception = assertThrows(Exception.class, () -> game.nextMove(1, 2, 1));
            assertEquals("The same player cannot move more than once in a row", exception.getMessage());
        }

        @Test
        void itShouldThrowIfAPlayerTargetsACellThatIsAlreadyTaken() throws Exception {
            game.nextMove(1, 1, 1);

            Exception exception = assertThrows(Exception.class, () -> game.nextMove(0, 1, 1));
            assertEquals("Cell [1, 1] is already taken. Choose another cell", exception.getMessage());
        }

        @Test
        void itShouldThrowIfPlayerIdIsNotValid() throws Exception {
            Exception exception = assertThrows(Exception.class, () -> game.nextMove(2, 1, 1));
            assertEquals("PlayerId 2 is not valid. Use either 0 or 1", exception.getMessage());
        }

        @Test
        void itShouldThrowIfTheGameIsAlreadyOverWithAWin() throws Exception {
            Game game = Game.fromBoardSnapshot(BoardFixtures.moveToWinOnTopLeftToBottomRightDiagonal());

            game.nextMove(1, 3, 3);

            Exception exception = assertThrows(Exception.class, () -> game.nextMove(0, 3, 2));
            assertEquals("Cannot make new moves on a finished game", exception.getMessage());
        }

        @Test
        void itShouldThrowIfTheGameIsAlreadyOverWithADraft() throws Exception {
            Game game = Game.fromBoardSnapshot(BoardFixtures.draftOnBottomRightCorner());
            game.nextMove(0, 3, 3);

            Exception exception = assertThrows(Exception.class, () -> game.nextMove(0, 3, 2));
            assertEquals("Cannot make new moves on a finished game", exception.getMessage());
        }

        @Test
        void itShouldComputeTheWinningPlayerWhenThreeSymbolsAlignOnTheSameDiagonal() throws Exception {
            Game oWinsGame = Game.fromBoardSnapshot(BoardFixtures.moveToWinOnTopLeftToBottomRightDiagonal());
            oWinsGame.nextMove(1, 3, 3);

            Game xWinsGame = Game.fromBoardSnapshot(BoardFixtures.moveToWinOnTopLeftToBottomRightDiagonal(0));
            xWinsGame.nextMove(0, 3, 3);

            assertEquals(1, oWinsGame.getWinner());
            assertEquals(0, xWinsGame.getWinner());
        }

        @Test
        void itShouldComputeTheWinningPlayerWhenThreeSymbolsAlignOnTheSameColumn() throws Exception {
            Game game = Game.fromBoardSnapshot(BoardFixtures.moveToWinOnFirstRow());
            game.nextMove(0, 3, 1);

            assertEquals(0, game.getWinner());
        }

        @Test
        void itShouldComputeTheWinningPlayerWhenThreeSymoblsAlignOnTheSameColumn() throws Exception {
            Game game = Game.fromBoardSnapshot(BoardFixtures.moveToWinOnFirstColumn());
            game.nextMove(0, 1, 3);

            assertEquals(0, game.getWinner());
        }

        @Test
        void itShouldDrawADraftWhenNoPlayerHasWon() throws Exception {
            Game game = Game.fromBoardSnapshot(BoardFixtures.draftOnBottomRightCorner());
            game.nextMove(0, 3, 3);

            assertEquals(-1, game.getWinner());
        }
    }

    @Nested
    class isDraft {
        @Test
        void itShouldReturnTrueIfTheGameEndedWithADraft() throws Exception {
            Game newGame = Game.fromBoardSnapshot(BoardFixtures.draftOnBottomRightCorner());
            newGame.nextMove(0, 3, 3);

            assertEquals(true, newGame.isDraft());
        }

        @Test
        void itShouldReturnFalseIfTheGameIsNotEnded() throws Exception {
            Game newGame = Game.fromBoardSnapshot(BoardFixtures.draftOnBottomRightCorner());

            assertEquals(false, newGame.isDraft());
        }

        @Test
        void itShouldReturnFalseIfTheGameEndedWithAWin() throws Exception {
            Game newGame = Game.fromBoardSnapshot(BoardFixtures.moveToWinOnTopLeftToBottomRightDiagonal());

            newGame.nextMove(1, 3, 3);
            assertEquals(false, newGame.isDraft());
        }
    }
}
