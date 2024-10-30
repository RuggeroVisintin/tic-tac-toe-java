package tic_tac_toe.app.application.game.representation;

import java.util.UUID;

import tic_tac_toe.app.domain.game.models.Game;

public record GameRepresentation(String[][] board, boolean isGameOver, int winnerId, UUID gameId) {
    public static GameRepresentation fromGame(Game game) {
        return new GameRepresentation(
                game.getBoard(),
                game.getWinner() != null,
                game.getWinner() != null ? game.getWinner().playerId() : -1,
                game.getId());
    }
}