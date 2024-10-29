package tic_tac_toe.app.application.game;

import tic_tac_toe.app.domain.game.models.Game;
import tic_tac_toe.app.domain.game.ports.GameRepository;

import java.util.UUID;

public class BaseFakeGameRepository implements GameRepository {
    @Override
    public void save(Game game) {
    }

    @Override
    public Game findById(UUID gameId) {
        return null;
    }
}
