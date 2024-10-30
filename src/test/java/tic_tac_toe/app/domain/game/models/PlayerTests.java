package tic_tac_toe.app.domain.game.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
}
