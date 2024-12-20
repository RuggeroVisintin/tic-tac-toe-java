package tic_tac_toe.app.domain.game.models;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import tic_tac_toe.app.domain.game.BoardFixtures;
import tic_tac_toe.app.domain.game.models.coordinate.XCoordinate;
import tic_tac_toe.app.domain.game.models.coordinate.YCoordinate;

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
            assertEquals(null, game.getWinner());
        }

    }

    @Nested
    class FromBoardSnapshotMethod {
        @Test
        void itShouldInitializeANewGameFromABoardSnaposhot() throws Exception {
            Game newGame = Game.fromBoardSnapshot(BoardFixtures.moveToWinOnTopLeftToBottomRightDiagonal());

            assertArrayEquals(BoardFixtures.moveToWinOnTopLeftToBottomRightDiagonal(), newGame.getBoard());
        }

        @Test
        void itShouldSetTheStartingPlayerBasedOnGivenSnapshot() throws Exception {
            Game resumeGameWithO = Game.fromBoardSnapshot(BoardFixtures.player1Turn());
            Game resumeGameWithX = Game.fromBoardSnapshot(BoardFixtures.player0Turn());

            assertEquals(new Player(1), resumeGameWithO.getCurrentPlayer());
            assertEquals(new Player(0), resumeGameWithX.getCurrentPlayer());
        }

        @Test
        void itShouldSetTheWinnerBasedOnGivenSnapshot() throws Exception {
            Game player1WinningGame = Game.fromBoardSnapshot(BoardFixtures.Player1Won());
            Game player0WinningGame = Game.fromBoardSnapshot(BoardFixtures.Player0Won());

            assertEquals(new Player(1), player1WinningGame.getWinner());
            assertEquals(new Player(0), player0WinningGame.getWinner());
        }
    }

    @Nested
    class NextMoveTests {

        @Test
        void itShouldRegisterTheMoveInTheGameBoard() throws Exception {
            game.nextMove(new Move(new Player(1), new XCoordinate(3), new YCoordinate(2)));

            assertArrayEquals(new String[][] {
                    { "", "", "" },
                    { "", "", "O" },
                    { "", "", "" }
            }, game.getBoard());
        }

        @Test
        void itShouldResisterXOnTheBoardWhenPlayer0Moves() throws Exception {
            game.nextMove(new Move(new Player(0), new XCoordinate(3), new YCoordinate(2)));

            assertArrayEquals(new String[][] {
                    { "", "", "" },
                    { "", "", "X" },
                    { "", "", "" }
            }, game.getBoard());
        }

        @Test
        void itShouldResisterOOnTheBoardWhenPlayer0Moves() throws Exception {
            game.nextMove(new Move(new Player(1), new XCoordinate(3), new YCoordinate(2)));

            assertArrayEquals(new String[][] {
                    { "", "", "" },
                    { "", "", "O" },
                    { "", "", "" }
            }, game.getBoard());
        }

        @Test
        void itShouldThrowIfTheSamePlayerMovesMoreThanOnceInARow() throws Exception {
            game.nextMove(new Move(new Player(1), new XCoordinate(1), new YCoordinate(1)));

            Exception exception = assertThrows(Exception.class, () -> game.nextMove(new Move(new Player(1),
                    new XCoordinate(2), new YCoordinate(1))));
            assertEquals("The same player cannot move more than once in a row", exception.getMessage());
        }

        @Test
        void itShouldThrowIfAPlayerTargetsACellThatIsAlreadyTaken() throws Exception {
            game.nextMove(new Move(new Player(1), new XCoordinate(1), new YCoordinate(1)));

            Exception exception = assertThrows(Exception.class, () -> game.nextMove(new Move(new Player(0),
                    new XCoordinate(1), new YCoordinate(1))));
            assertEquals("Cell [1, 1] is already taken. Choose another cell", exception.getMessage());
        }

        @Test
        void itShouldThrowIfPlayerIsNotValid() throws Exception {
            Exception exception = assertThrows(Exception.class, () -> game.nextMove(new Move(new Player(2),
                    new XCoordinate(1), new YCoordinate(1))));
            assertEquals("PlayerId 2 is not valid. Use either 0 or 1", exception.getMessage());
        }

        @Test
        void itShouldThrowIfTheGameIsAlreadyOverWithAWin() throws Exception {
            Game game = Game.fromBoardSnapshot(BoardFixtures.moveToWinOnTopLeftToBottomRightDiagonal());

            game.nextMove(new Move(new Player(1), new XCoordinate(3), new YCoordinate(3)));

            Exception exception = assertThrows(Exception.class, () -> game.nextMove(new Move(new Player(0),
                    new XCoordinate(3), new YCoordinate(2))));
            assertEquals("Cannot make new moves on a finished game", exception.getMessage());
        }

        @Test
        void itShouldThrowIfTheGameIsAlreadyOverWithADraft() throws Exception {
            Game game = Game.fromBoardSnapshot(BoardFixtures.draftOnBottomRightCorner());
            game.nextMove(new Move(new Player(0), new XCoordinate(3), new YCoordinate(3)));

            Exception exception = assertThrows(Exception.class, () -> game.nextMove(new Move(new Player(0),
                    new XCoordinate(3), new YCoordinate(2))));
            assertEquals("Cannot make new moves on a finished game", exception.getMessage());
        }

        @Test
        void itShouldComputeTheWinningPlayerWhenThreeSymbolsAlignOnTheSameDiagonal() throws Exception {
            Game oWinsGame = Game.fromBoardSnapshot(BoardFixtures.moveToWinOnTopLeftToBottomRightDiagonal());
            oWinsGame.nextMove(new Move(new Player(1), new XCoordinate(3), new YCoordinate(3)));

            Game xWinsGame = Game.fromBoardSnapshot(BoardFixtures.moveToWinOnTopLeftToBottomRightDiagonal(0));
            xWinsGame.nextMove(new Move(new Player(0), new XCoordinate(3), new YCoordinate(3)));

            assertEquals(new Player(1), oWinsGame.getWinner());
            assertEquals(new Player(0), xWinsGame.getWinner());
        }

        @Test
        void itShouldComputeTheWinningPlayerWhenThreeSymbolsAlignOnTheSameColumn() throws Exception {
            Game game = Game.fromBoardSnapshot(BoardFixtures.moveToWinOnFirstRow());
            game.nextMove(new Move(new Player(0), new XCoordinate(3), new YCoordinate(1)));

            assertEquals(new Player(0), game.getWinner());
        }

        @Test
        void itShouldComputeTheWinningPlayerWhenThreeSymoblsAlignOnTheSameColumn() throws Exception {
            Game game = Game.fromBoardSnapshot(BoardFixtures.moveToWinOnFirstColumn());
            game.nextMove(new Move(new Player(0), new XCoordinate(1), new YCoordinate(3)));

            assertEquals(new Player(0), game.getWinner());
        }

        @Test
        void itShouldDrawADraftWhenNoPlayerHasWon() throws Exception {
            Game game = Game.fromBoardSnapshot(BoardFixtures.draftOnBottomRightCorner());
            game.nextMove(new Move(new Player(0), new XCoordinate(3), new YCoordinate(3)));

            assertEquals(null, game.getWinner());
        }
    }

    @Nested
    class IsDraftTests {
        @Test
        void itShouldReturnTrueIfTheGameEndedWithADraft() throws Exception {
            Game newGame = Game.fromBoardSnapshot(BoardFixtures.draftOnBottomRightCorner());
            newGame.nextMove(new Move(new Player(0), new XCoordinate(3), new YCoordinate(3)));

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

            newGame.nextMove(new Move(new Player(1), new XCoordinate(3), new YCoordinate(3)));
            assertEquals(false, newGame.isDraft());
        }
    }

    @Nested
    class CloneTests {
        @Test
        void itShouldNotReferenceTheOriginalObject() throws Exception {
            Game originalGame = new Game();
            Game gameClone = originalGame.clone();

            originalGame.nextMove(new Move(new Player(1), new XCoordinate(1), new YCoordinate(1)));

            assertNotEquals(originalGame, gameClone);
        }

        @Test
        void itShouldCreateACloneOfTheOriginalObject() throws Exception {
            Game originalGame = new Game();
            Game gameClone = originalGame.clone();

            assertEquals(originalGame, gameClone);
        }
    }

    @Nested
    class EqualsTests {
        @Test
        void itShouldReturnTrueIfTheStateIsTheSame() {
            Game newGame = new Game();
            Game gameCopy = newGame.clone();

            assertTrue(newGame.equals(gameCopy));
        }

        @Test
        void itShouldReturnFalseIfTheStatesDontMatch() throws Exception {
            Game newGame = new Game();
            Game gameCopy = newGame.clone();
            gameCopy.nextMove(new Move(new Player(1), new XCoordinate(1), new YCoordinate(1)));

            assertFalse(newGame.equals(gameCopy));
        }

        @Test
        void itShouldReturnFalseIfTheObjectIsNull() {
            Game newGame = new Game();
            Game otherGame = null;

            assertFalse(newGame.equals(otherGame));
        }
    }
}
