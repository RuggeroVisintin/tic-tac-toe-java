package tic_tac_toe.app.application.game.useCases;

import tic_tac_toe.app.application.game.representation.GameRepresentation;
import tic_tac_toe.app.application.game.representation.MoveRepresentation;
import tic_tac_toe.app.domain.game.models.Game;
import tic_tac_toe.app.domain.game.models.Player;
import tic_tac_toe.app.domain.game.ports.GameRepository;

public class MakeAMoveUseCase {
    public GameRepresentation execute(MoveRepresentation move, GameRepository repository) throws Exception {
        Game currentGame = repository.findById(move.getGameId());

        currentGame.nextMove(new Player(move.playerId()), move.getX(), move.getY());
        repository.save(currentGame);

        return GameRepresentation.fromGame(currentGame);
    }
}
