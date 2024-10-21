package tic_tac_toe.app.game.ports;

import java.util.UUID;

import tic_tac_toe.app.game.models.Game;

public interface GameRepository {
    void save(Game game);

    Game findById(UUID gameId);
}
