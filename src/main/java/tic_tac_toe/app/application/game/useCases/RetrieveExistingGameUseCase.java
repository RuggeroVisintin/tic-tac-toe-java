package tic_tac_toe.app.application.game.useCases;

import java.util.UUID;

import tic_tac_toe.app.application.game.representation.GameRepresentation;
import tic_tac_toe.app.domain.game.ports.GameRepository;

public class RetrieveExistingGameUseCase {
    public GameRepresentation execute(UUID gameId, GameRepository gameRepository) {
        return GameRepresentation.fromGame(gameRepository.findById(gameId));
    }
}
