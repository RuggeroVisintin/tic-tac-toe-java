package tic_tac_toe.app.domain.game.models;

import tic_tac_toe.app.domain.game.models.coordinate.XCoordinate;
import tic_tac_toe.app.domain.game.models.coordinate.YCoordinate;

public record Move(Player player, XCoordinate x, YCoordinate y) {
    public Move {
        if (x.value() > 3 || x.value() < 1 || y.value() > 3 || y.value() < 1) {
            throw new InvalidMoveException(
                    "Cell [" + x + ", " + y + "] is not a valid cell, use values in between 1 to 3");
        }
    }
}
