package tic_tac_toe.app.domain.game;

public class BoardFixtures {
    public static String[][] moveToWinOnTopLeftToBottomRightDiagonal() {
        return new String[][] {
                { "O", "X", "O" },
                { "X", "O", "" },
                { "X", "", "" }
        };
    }

    public static String[][] moveToWinOnTopLeftToBottomRightDiagonal(int winnerId) {
        if (winnerId == 0) {
            return new String[][] {
                    { "X", "O", "X" },
                    { "O", "X", "" },
                    { "O", "", "" }
            };
        }

        return moveToWinOnTopLeftToBottomRightDiagonal();
    }

    public static String[][] moveToWinOnFirstRow() {
        return new String[][] {
                { "X", "X", "" },
                { "O", "", "O" },
                { "", "", "" }
        };
    }

    public static String[][] moveToWinOnFirstColumn() {
        return new String[][] {
                { "X", "O", "O" },
                { "X", "", "" },
                { "", "", "" }
        };
    }

    public static String[][] draftOnBottomRightCorner() {
        return new String[][] {
                { "O", "X", "X" },
                { "X", "O", "O" },
                { "O", "X", "" }
        };
    }

    public static String[][] player1Turn() {
        return new String[][] {
                { "X", "", "" },
                { "", "", "" },
                { "", "", "" }
        };
    }

    public static String[][] player0Turn() {
        return new String[][] {
                { "O", "", "" },
                { "", "", "" },
                { "", "", "" }
        };
    }

    public static String[][] Player1Won() {
        return new String[][] {
                { "O", "X", "X" },
                { "X", "O", "O" },
                { "O", "X", "O" }
        };
    }

    public static String[][] Player0Won() {
        return new String[][] {
                { "X", "X", "X" },
                { "X", "O", "O" },
                { "O", "X", "O" }
        };
    }

}
