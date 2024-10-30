package tic_tac_toe.app.domain.game.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class PlayerTests {
    @Nested
    class NewPlayerIDTests {
        @Test
        void itShouldThrwoIfThePlayerIdIsNotValid() throws Exception {
            Exception exception = assertThrows(InvalidPlayerException.class, () -> new Player(2));
            assertEquals("PlayerId 2 is not valid. Use either 0 or 1", exception.getMessage());
        }
    }

    @Nested
    class IsEqualTests {
        @Test
        void itShouldReturnTrueWhenThePlayerIDMatch() throws Exception {
            Player player1 = new Player(0);
            Player player2 = new Player(0);

            assertTrue(player1.equals(player2));
        }
    }
}
