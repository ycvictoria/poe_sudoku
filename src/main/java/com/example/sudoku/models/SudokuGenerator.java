package com.example.sudoku.models;

import java.util.Random;

public class SudokuGenerator {
    private final int size;
    private final int boxSize;
    private final int[][] board;

    public SudokuGenerator(int size) {
        this.size = size;
        this.boxSize = (int) Math.sqrt(size);
        this.board = new int[size][size];
        generateBoard();
    }

    public int[][] getBoard() {
        return board;
    }

    private void generateBoard() {
        fillBoard(0, 0);
    }

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

    private boolean isSafe(int row, int col, int num) {
        for (int i = 0; i < size; i++) {
            if (board[row][i] == num || board[i][col] == num)
                return false;
        }

        int boxRowStart = row - row % boxSize;
        int boxColStart = col - col % boxSize;
        for (int r = 0; r < boxSize; r++) {
            for (int c = 0; c < boxSize; c++) {
                if (board[boxRowStart + r][boxColStart + c] == num)
                    return false;
            }
        }
        return true;
    }

    private int[] generateRandomNumbers(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = i + 1;
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            int j = rand.nextInt(n);
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        return arr;
    }
}
