package com.example.sudoku.models;

import java.util.Random;

public class SudokuGenerator {
    private final int size;
    private final int boxRows;
    private final int boxCols;
    private final int[][] board;
    private final Random rand = new Random();

    public SudokuGenerator(int size) {
        this.size = size;

        // Determinar dimensiones del bloque según tamaño
        switch (size) {
            case 4 -> { boxRows = 2; boxCols = 2; }
            case 6 -> { boxRows = 2; boxCols = 3; }
            case 8 -> { boxRows = 2; boxCols = 4; }
            case 9 -> { boxRows = 3; boxCols = 3; }
            case 12 -> { boxRows = 3; boxCols = 4; }
            case 16 -> { boxRows = 4; boxCols = 4; }
            default -> {
                // Por defecto intenta cuadrado perfecto
                int sqrt = (int) Math.sqrt(size);
                boxRows = sqrt;
                boxCols = sqrt;
            }
        }

        board = new int[size][size];
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
        // Validar fila y columna
        for (int i = 0; i < size; i++) {
            if (board[row][i] == num || board[i][col] == num)
                return false;
        }

        // Validar bloque rectangular
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
