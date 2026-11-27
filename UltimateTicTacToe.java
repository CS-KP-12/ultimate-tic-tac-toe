public class UltimateTicTacToe {
    public static void main(String[] args) {
        LargeGrid ultimateGame = new LargeGrid();
        char player = 'X';

        while(!ultimateGame.isGameOver()) {
            printUltimateGrid(ultimateGame);
            String playerTurn = "";
            if (ultimateGame.getCurrentGrid() == 0) {
                playerTurn = IO.readln("(Player: " + Character.toString(player) + ") Enter large grid & small grid (Numbered 1-9) separated by space (Free Turn)\n");
            } else {
                playerTurn = IO.readln("(Player: " + Character.toString(player) + ") Enter large grid & small grid (Numbered 1-9) separated by space (Selected Grid: " + ultimateGame.getCurrentGrid() + ")\n");
            }
            try {
                String[] nums = playerTurn.split(" ");
                if (nums.length != 2) {
                    IO.println("Invalid input, try again");
                    continue;
                }
                int largeInput = Integer.parseInt(nums[0]);
                int smallInput = Integer.parseInt(nums[1]);
                boolean turn = ultimateGame.turn(largeInput, smallInput, player);
                if (!turn) {
                    IO.println("Invalid input, try again");
                    continue;
                }
            } catch (Exception e) {
                IO.println("Invalid input, try again");
                continue;
            }
            switch (player) {
                case 'X' -> {
                    player = 'O';
                }
                case 'O' -> {
                    player = 'X';
                }
                default -> {
                    player = 'E';
                }
            }
        }

        printUltimateGrid(ultimateGame);
        char winner = ultimateGame.getUltimateWinner();
        switch (winner) {
            case 'D' -> {
                IO.println("Game Over, Draw");
            }
            case 'X' -> {
                IO.println("Game Over, Winner: X");
            }
            case 'O' -> {
                IO.println("Game Over, Winner: O");
            }
            default -> IO.println("Game over?");
        }
    }

    private static void printUltimateGrid(LargeGrid ultimateGame) {
        //Get all SmallGrids from the ultimateGame
        SmallGrid grid1 = ultimateGame.getSmallGridRC(0, 0);
        SmallGrid grid2 = ultimateGame.getSmallGridRC(0, 1);
        SmallGrid grid3 = ultimateGame.getSmallGridRC(0, 2);
        SmallGrid grid4 = ultimateGame.getSmallGridRC(1, 0);
        SmallGrid grid5 = ultimateGame.getSmallGridRC(1, 1);
        SmallGrid grid6 = ultimateGame.getSmallGridRC(1, 2);
        SmallGrid grid7 = ultimateGame.getSmallGridRC(2, 0);
        SmallGrid grid8 = ultimateGame.getSmallGridRC(2, 1);
        SmallGrid grid9 = ultimateGame.getSmallGridRC(2, 2);

        SmallGrid[] gridsA = {grid1, grid2, grid3};
        SmallGrid[] gridsB = {grid4, grid5, grid6};
        SmallGrid[] gridsC = {grid7, grid8, grid9};

        //GridsA
        IO.println(rowString(gridsA, 0));
        IO.println("----------- | ----------- | -----------");
        IO.println(rowString(gridsA, 1));
        IO.println("----------- | ----------- | -----------");
        IO.println(rowString(gridsA, 2));
        IO.println("=========== | =========== | ===========");

        //GridsB
        IO.println(rowString(gridsB, 0));
        IO.println("----------- | ----------- | -----------");
        IO.println(rowString(gridsB, 1));
        IO.println("----------- | ----------- | -----------");
        IO.println(rowString(gridsB, 2));
        IO.println("=========== | =========== | ===========");

        //GridsC
        IO.println(rowString(gridsC, 0));
        IO.println("----------- | ----------- | -----------");
        IO.println(rowString(gridsC, 1));
        IO.println("----------- | ----------- | -----------");
        IO.println(rowString(gridsC, 2));
    }

    private static String rowString(SmallGrid[] smallGrids, int r) {
        String retStr = "";
        for (int i = 0; i < 3; i++) {
            if (i == 2) {
                retStr += " " + smallGrids[i].getGridSpace(r, 0) + " | " + smallGrids[i].getGridSpace(r, 1) + " | " + smallGrids[i].getGridSpace(r, 2) + " ";
            } else {
                retStr += " " + smallGrids[i].getGridSpace(r, 0) + " | " + smallGrids[i].getGridSpace(r, 1) + " | " + smallGrids[i].getGridSpace(r, 2) + "  | ";
            }
        }
        return retStr;
    }
}