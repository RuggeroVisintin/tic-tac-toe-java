package tic_tac_toe.app.application.game.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import tic_tac_toe.app.application.game.representation.GameRepresentation;
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
        return new GameRepresentation((new NewGameUseCase().execute(repository)));
    }
}
