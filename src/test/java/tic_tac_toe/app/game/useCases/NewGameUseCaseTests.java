package tic_tac_toe.app.game.useCases;

import org.junit.jupiter.api.Test;
import tic_tac_toe.app.game.models.Game;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

class NewGameUseCaseTests {

    @Test
    void itShouldCreateAndReturnANewGame() {
        Game newGame = new NewGameUseCase()
                .execute();

        Game newGame2 = new NewGameUseCase()
                .execute();

        assertNotEquals(newGame.getId(), newGame2.getId());
    }
}