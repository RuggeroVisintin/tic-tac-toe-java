package tic_tac_toe.app.application.game.controllers;

import java.util.UUID;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tic_tac_toe.app.application.game.representation.GameRepresentation;
import tic_tac_toe.app.application.game.representation.MoveRepresentation;
import tic_tac_toe.app.application.game.useCases.MakeAMoveUseCase;
import tic_tac_toe.app.application.game.useCases.NewGameUseCase;
import tic_tac_toe.app.domain.game.ports.GameRepository;

@RestController
public class GameController {

    private final GameRepository repository;

    public GameController(GameRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/v1/games")
    public GameRepresentation newGame() {
        return new NewGameUseCase().execute(repository);
    }

    @PostMapping("/v1/games/{gameId}/moves")
    public GameRepresentation makeAMove(@PathVariable UUID gameId, @RequestParam int playerId,
            @RequestParam int x, @RequestParam int y) throws Exception {

        return new MakeAMoveUseCase().execute(new MoveRepresentation(gameId, playerId, x, y), repository);
    }
}
