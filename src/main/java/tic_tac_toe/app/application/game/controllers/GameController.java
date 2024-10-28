package tic_tac_toe.app.application.game.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import tic_tac_toe.app.application.game.useCases.NewGameUseCase;
import tic_tac_toe.app.domain.game.models.Game;
import tic_tac_toe.app.domain.game.ports.GameRepository;

@Controller
public class GameController {

    private final GameRepository repository;

    public GameController(GameRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/v1/games")
    public Game newGame() {
        return new NewGameUseCase().execute(repository);
    }
}
