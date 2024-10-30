package tic_tac_toe.app.domain.game.ports;

import java.util.UUID;

import tic_tac_toe.app.domain.game.models.Game;
import tic_tac_toe.app.domain.game.models.GameNotFoundException;

public interface GameRepository {
    void save(Game game);

    Game findById(UUID gameId) throws GameNotFoundException;
}
