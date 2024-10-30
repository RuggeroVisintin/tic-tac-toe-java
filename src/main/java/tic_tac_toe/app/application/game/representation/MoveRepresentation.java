package tic_tac_toe.app.application.game.representation;

import java.util.UUID;

public record MoveRepresentation(UUID gameId, int playerId, int x, int y) {
}