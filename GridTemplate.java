//Template for Small & Large Grid

public class GridTemplate {
    private char[][] gridSpaces = new char[3][3];
    private char winner;

    public GridTemplate() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                this.gridSpaces[r][c] = ' ';
            }
        }
        this.winner = ' ';
    }

    public char getWinner() {
        return this.winner;
    }

    public boolean isGameOver() {
        if (this.winner == ' ') {
            return false;
        }
        return true;
    }

    public boolean turn(int r, int c, char player) {
        if (this.isGameOver()) {
            return false;
        }

        if (r < 0 || r > 3) {
            return false;
        }

        if (c < 0 || c > 3) {
            return false;
        }
        this.gridSpaces[r][c] = player;
        this.checkWinConditions();
        return true;
    }

    private void checkWinConditions() {
        for (int i = 0; i < 3; i++) {

            //Check win condition for each row
            if (this.gridSpaces[i][0] != ' ') {
                if (this.gridSpaces[i][0] == this.gridSpaces[i][1] &&
                    this.gridSpaces[i][1] == this.gridSpaces[i][2]) {
                    this.winner = this.gridSpaces[i][0];
                    return;
                }
            }

            //Check win condition for each column
            if (this.gridSpaces[0][i] != ' ') {
                if (this.gridSpaces[0][i] == this.gridSpaces[1][i] &&
                    this.gridSpaces[1][i] == this.gridSpaces[2][i]) {
                    this.winner = this.gridSpaces[0][i];
                    return;
                }
            }

        }

        //Check win condition for diagonals
        if (this.gridSpaces[0][0] == this.gridSpaces[1][1] &&
            this.gridSpaces[1][1] == this.gridSpaces[2][2]) {
            this.winner = this.gridSpaces[0][0];
            return;
        }

        if (this.gridSpaces[2][0] == this.gridSpaces[1][1] &&
            this.gridSpaces[1][1] == this.gridSpaces[0][2]) {
            this.winner = this.gridSpaces[0][0];
            return;
        }

        //Check for a draw
        boolean full = true;
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (this.gridSpaces[r][c] == ' ') {
                    full = false;
                    break;
                }
            }
            if (full) {
                this.winner = 'D';
                break;
            }
        }
    }
}
