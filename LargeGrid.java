public class LargeGrid {
    private SmallGrid[][] grids;
    private int currentGrid;
    private char ultimateWinner;

    public LargeGrid() {
        this.grids = new SmallGrid[3][3];
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                this.grids[r][c] = new SmallGrid();
            }
        }
        this.ultimateWinner = ' ';
        this.currentGrid = 0;
    }

    public int getCurrentGrid() {
        return this.currentGrid;
    }

    public char getUltimateWinner() {
        return this.ultimateWinner;
    }

    public boolean isGameOver() {
        if (this.ultimateWinner == ' ') {
            return false;
        }
        return true;
    }

    public boolean turn(int largeInput, int smallInput, char player) {
        if (this.isGameOver()) {
            return false;
        }

        if (this.currentGrid != largeInput && this.currentGrid != 0) {
            return false;
        }

        if (largeInput < 1 || largeInput > 9) {
            return false;
        }

        if (smallInput < 1 || smallInput > 9) {
            return false;
        }

        SmallGrid selected = this.getSmallGrid(largeInput);
        if (selected == null || selected.isGameOver()) {
            return false;
        }

        boolean smallTurn = selected.turn(smallInput, player);
        if (!smallTurn) {
            return false;
        }

        //Check ultimate win condition

        SmallGrid next = this.getSmallGrid(smallInput);
        if (next == null || next.isGameOver()) {
            this.currentGrid = 0;
        } else {
            this.currentGrid = smallInput;
        }
        return true;
    }

    private SmallGrid getSmallGrid(int input) {
        int r;
        int c;
        switch (input) {
            case 1 -> {
                r = 0;
                c = 0;
            }

            case 2 -> {
                r = 0;
                c = 1;
            }

            case 3 -> {
                r = 0;
                c = 2;
            }

            case 4 -> {
                r = 1;
                c = 0;
            }

            case 5 -> {
                r = 1;
                c = 1;
            }

            case 6 -> {
                r = 1;
                c = 2;
            }

            case 7 -> {
                r = 2;
                c = 0;
            }

            case 8 -> {
                r = 2;
                c = 1;
            }

            case 9 -> {
                r = 2;
                c = 2;
            }

            default -> {
                return null;
            }
        }

        return this.grids[r][c];
    }

    private void checkUltimateWinConditions() {
        for (int i = 0; i < 3; i++) {
            //Check ultimate win by row
            if (this.isWin(this.grids[i][0], this.grids[i][1], this.grids[i][2])) {
                this.ultimateWinner = this.grids[i][0].getWinner();
                return;
            }

            //Check ultimate win by column
            if (this.isWin(this.grids[0][i], this.grids[1][i], this.grids[2][i])) {
                this.ultimateWinner = this.grids[0][i].getWinner();
                return;
            }
        }

        //Check ultimate win by diagonal
        if (this.isWin(this.grids[0][0], this.grids[1][1], this.grids[2][2])) {
            this.ultimateWinner = this.grids[0][0].getWinner();
            return;
        }

        if (this.isWin(this.grids[2][0], this.grids[1][1], this.grids[0][2])) {
            this.ultimateWinner = this.grids[2][0].getWinner();
            return;
        }

        //Check ultimate draw
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (!this.grids[r][c].isGameOver()) {
                    return;
                }
            }
        }
        this.ultimateWinner = 'D';
    }

    private boolean isWin(SmallGrid grid1, SmallGrid grid2, SmallGrid grid3) {
        char winner1 = grid1.getWinner();
        char winner2 = grid2.getWinner();
        char winner3 = grid3.getWinner();
        if (winner1 == 'X' || winner1 == 'O') {
            if (winner1 == winner2 && winner2 == winner3) {
                return true;
            }
        }
        return false;
    }
}
