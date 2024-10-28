package tic_tac_toe.app.infrastructure.game.adapters;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import tic_tac_toe.app.domain.game.models.Game;
import tic_tac_toe.app.domain.game.ports.GameRepository;

@Repository
public class InMemoryGameRepository implements GameRepository {
    private ArrayList<Game> mGames;

    public InMemoryGameRepository() {
        mGames = new ArrayList<>();
    }

    public void save(Game game) {
        mGames.add(game);
    }

    public Game findById(UUID gameId) {
        for (Game game : mGames) {
            if (game.getId() == gameId) {
                return game;
            }
        }

        return null;
    }
}
