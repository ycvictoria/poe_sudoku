package com.example.sudoku.models;

import java.util.Random;
/**
 * Automatic generator of valid Sudoku boards.
 * This class creates a complete Sudoku board using a backtracking algorithm,
 * ensuring that each number complies with the game rules in rows, columns, and blocks.
 * Supports different Sudoku sizes (4x4, 6x6, 9x9, etc.), automatically
 * adapting the dimensions of sub-boards according to their size.
 * @author Victoria Yuan
 * @version 1.0
 */
public class SudokuGenerator {
    /** Board size (number of rows and columns). */
    private final int size;
    /** Number of rows in each inner block. */
    private final int boxRows;
    /** Number of columns in each inner block. */
    private final int boxCols;
    /** Array representing the entire board. */
    private final int[][] board;
    /** Random number generator. */
    private final Random rand = new Random();
    /**
     * Creates a new Sudoku generator for the specified size.
     * @param size board size
     */
    public SudokuGenerator(int size) {
        this.size = size;

        switch (size) {
            case 4 -> { boxRows = 2; boxCols = 2; }
            case 6 -> { boxRows = 2; boxCols = 3; }
            case 8 -> { boxRows = 2; boxCols = 4; }
            case 9 -> { boxRows = 3; boxCols = 3; }
            case 12 -> { boxRows = 3; boxCols = 4; }
            case 16 -> { boxRows = 4; boxCols = 4; }
            default -> {
                int sqrt = (int) Math.sqrt(size);
                boxRows = sqrt;
                boxCols = sqrt;
            }
        }

        board = new int[size][size];
        generateBoard();
    }
    /**
     * Returns the generated Sudoku board.
     * @return a two-dimensional array of the Sudoku values
     */
    public int[][] getBoard() {
        return board;
    }
    /**
     * Start generating the Sudoku board.
     */
    private void generateBoard() {
        fillBoard(0, 0);
    }
    /**
     * Recursively fills the board using backtracking.
     *
     * @param row current row.
     * @param col current column.
     * @return {@code true} if the board is completely filled.
     */
    private boolean fillBoard(int row, int col) {
        if (row == size) return true;

        int nextRow = (col == size - 1) ? row + 1 : row;
        int nextCol = (col + 1) % size;

        int[] nums = generateRandomNumbers(size);
        for (int num : nums) {
            if (isSafe(row, col, num)) {
                board[row][col] = num;
                if (fillBoard(nextRow, nextCol))
                    return true;
                board[row][col] = 0;
            }
        }
        return false;
    }
    /**
     * Checks if a number can be placed in a given position.
     * @param row current row
     * @param col current column
     * @param num number to validate
     * @return {@code true} if the number meets the Sudoku rules
     */
    private boolean isSafe(int row, int col, int num) {
        for (int i = 0; i < size; i++) {
            if (board[row][i] == num || board[i][col] == num)
                return false;
        }
        int startRow = (row / boxRows) * boxRows;
        int startCol = (col / boxCols) * boxCols;

        for (int r = 0; r < boxRows; r++) {
            for (int c = 0; c < boxCols; c++) {
                if (board[startRow + r][startCol + c] == num)
                    return false;
            }
        }
        return true;
    }
    /**
     * Generates a sequence of random numbers from 1 to the board size.
     *
     * @param n the number of numbers to generate
     * @return a shuffled array of numbers from 1 to {@code n}.
     */
    private int[] generateRandomNumbers(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = i + 1;
        for (int i = 0; i < n; i++) {
            int j = rand.nextInt(n);
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        return arr;
    }
}
