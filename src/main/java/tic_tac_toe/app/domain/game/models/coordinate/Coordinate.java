package tic_tac_toe.app.domain.game.models.coordinate;

public sealed interface Coordinate permits XCoordinate, YCoordinate {
    public int value();
}
