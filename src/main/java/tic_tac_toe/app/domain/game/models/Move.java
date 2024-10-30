package tic_tac_toe.app.domain.game.models;

public record Move(Player player, int x, int y) {
    public Move {
        if (x > 3 || x < 1 || y > 3 || y < 1) {
            throw new InvalidMoveException(
                    "Cell [" + x + ", " + y + "] is not a valid cell, use values in between 1 to 3");
        }
    }
}
