package tic_tac_toe.app.domain.game.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import tic_tac_toe.app.domain.game.models.coordinate.XCoordinate;
import tic_tac_toe.app.domain.game.models.coordinate.YCoordinate;

public class MoveTests {
    @Nested
    class NewMoveTests {
        @Test
        void itShouldThrowIfAMoveXCoordinateIsGreaterThan3() {
            Exception exception = assertThrows(InvalidMoveException.class,
                    () -> new Move(new Player(0), new XCoordinate(4), new YCoordinate(1)));
            assertEquals("Cell [4, 1] is not a valid cell, use values in between 1 to 3", exception.getMessage());
        }

        @Test
        void itShouldThrowIfAMoveXCoordinateIsLowernThan1() {
            Exception exception = assertThrows(InvalidMoveException.class,
                    () -> new Move(new Player(0), new XCoordinate(0), new YCoordinate(1)));
            assertEquals("Cell [0, 1] is not a valid cell, use values in between 1 to 3", exception.getMessage());
        }

        @Test
        void itShouldThrowIfAMoveYCoordinateIsGreaterThan3() {
            Exception exception = assertThrows(InvalidMoveException.class,
                    () -> new Move(new Player(0), new XCoordinate(1), new YCoordinate(4)));
            assertEquals("Cell [1, 4] is not a valid cell, use values in between 1 to 3", exception.getMessage());
        }

        @Test
        void itShouldThrowIfAMoveYCoordinateIsLowernThan1() {
            Exception exception = assertThrows(InvalidMoveException.class,
                    () -> new Move(new Player(0), new XCoordinate(1), new YCoordinate(0)));
            assertEquals("Cell [1, 0] is not a valid cell, use values in between 1 to 3", exception.getMessage());
        }
    }
}
