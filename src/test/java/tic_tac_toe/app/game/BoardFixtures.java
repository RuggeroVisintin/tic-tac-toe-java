package tic_tac_toe.app.game;

public class BoardFixtures {
    public static String[][] moveToWinOnTopLeftToBottomRightDiagonal() {
        return new String[][] {
                { "O", "X", "O" },
                { "X", "O", "" },
                { "X", "", "" }
        };
    }
}
