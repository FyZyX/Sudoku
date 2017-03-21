import java.util.ArrayList;

/**
 * A Sudoku board is a 9x9 number grid
 * Each row, column and 3x3 square contains the digits 1-9
 */
class Board {
    private static final int DIM = 3;
    private Cell[][] cells = new Cell[DIM*DIM][DIM*DIM];

    /** Constructor
     * Creates a board from a grid of values
     *
     * @param grid is a two-dimensional array of integer values representing a Sudoku puzzle
     */
    Board(int[][] grid) {
        // Populate cell array with cells containing values from the grid
        for (int i = 0; i < DIM*DIM; i++) {
            for (int j = 0; j < DIM*DIM; j++) {
                cells[i][j] = new Cell(grid[i][j]); // create cell from corresponding grid value
            }
        }

        print();  // print initial board
    }

    /**
     * Provides the location of the next cell on the board
     * Since the board is tow-dimensional, we must handle wrapping to the next line
     *
     * @return a new IndexPath object containing the desired location
     */
    private IndexPath getNextPath(IndexPath indexPath) {
        if (indexPath.col == DIM*DIM - 1)
            return new IndexPath(indexPath.row + 1, 0); // move to start of next row
        else
            return new IndexPath(indexPath.row, indexPath.col + 1); // move to next column
    }

    /**
     * Tests the validity of a particular value for a given grid location
     *
     * @param indexPath specifies the location in the grid
     * @param value is the value to be tested in the cell at the specified location
     * @return true if the value can be safely placed at the specified location
     */
    private boolean isValid(IndexPath indexPath, int value) {
        for (int k = 0; k < DIM*DIM; k++) {
            if (cells[k][indexPath.col].equals(value))  // value already in column
                return false;

            if (cells[indexPath.row][k].equals(value))  // value already in row
                return false;

            int i = indexPath.row / DIM * DIM + k / DIM;  // row offset
            int j = indexPath.col / DIM * DIM + k % DIM;  // column offset
            if (cells[i][j].equals(value))  // value already in square
                return false;
        }

        return true; // value is safe to put in the specified location
    }

    /**
     * Implements a backtracking algorithm to test all possible solutions to the puzzle
     * For each potential value at the specified cell, a recursive call is made on the next cell
     *
     * @param indexPath provides the location of the cell to be solved
     * @return true if the puzzle can be solved
     */
    private boolean solve(IndexPath indexPath) {
        if (indexPath.row == DIM*DIM)  // last cell complete
            return true;

        Cell cell = cells[indexPath.row][indexPath.col];

        if (cell.isSolved())  // skip solved cells
            return solve(getNextPath(indexPath));

        // Test all potential values for the cell
        for (int value = 1; value <= DIM*DIM; value++) {
            if (isValid(indexPath, value)) {  // value is a possibility
                cell.setValue(value);  // test value
                if (solve(getNextPath(indexPath)))  // value leads to solution
                    return true;
            }
        }

        cell.clear();  // reset cell on backtrack
        return false;  // no solution exists
    }

    /** Wrapper Function
     * Starts solving the puzzle from the beginning
     */
    void solve() {
        IndexPath startLocation = new IndexPath(0, 0);  // first cell
        if (solve(startLocation))  // solve was successful
            print();  // print solution
        else  // solve was unsuccessful
            System.out.println("No solution found.");
    }

    /** Utility Function
     * Prints the grid to System output in a human readable format
     */
    private void print() {
        System.out.println();
        for (int i = 0; i < DIM*DIM; i++) {
            for (int j = 0; j < DIM*DIM; j++) {
                if (j > 0 && j % DIM == 0) {
                    System.out.print("| ");
                }
                System.out.print(cells[i][j].getValue() + " ");
            }
            System.out.println();
            ArrayList<Integer> separators = new ArrayList<>();
            int multiplier = 1, odd;
            while ((odd = multiplier*DIM - 1) < DIM*DIM - 1) { separators.add(odd); multiplier++; }
            if (separators.contains(i)) { System.out.println("---------------------"); }
        }
    }
}
