/* Created by Lucas Lofaro 3/18/2017
 * Inspired in part by https://bob-carpenter.github.io/games/sudoku/java_sudoku.html
 */

public class Sudoku {
    private static int[][] puzzleGrid = {
            {8, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 3, 6, 0, 0, 0, 0, 0},
            {0, 7, 0, 0, 9, 0, 2, 0, 0},
            {0, 5, 0, 0, 0, 7, 0, 0, 0},
            {0, 0, 0, 0, 4, 5, 7, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 3, 0},
            {0, 0, 1, 0, 0, 0, 0, 6, 8},
            {0, 0, 8, 5, 0, 0, 0, 1, 0},
            {0, 9, 0, 0, 0, 0, 4, 0, 0}
    };

    public static void main(String[] args) {
        timeSolve(new Board(puzzleGrid));
    }

    private static void timeSolve(Board board) {
        double startTime = System.currentTimeMillis();
        board.solve();
        double solveTime = System.currentTimeMillis() - startTime;
        System.out.println("\nSolve took " + solveTime + " milliseconds");
    }
}
