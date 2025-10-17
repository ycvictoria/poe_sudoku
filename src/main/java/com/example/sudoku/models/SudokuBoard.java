package com.example.sudoku.models;

import com.example.sudoku.controllers.CellChangeListener;
import com.example.sudoku.utils.AlertsSudoku;
import com.example.sudoku.views.SudokuCell;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;

import java.util.Random;

public class SudokuBoard extends GridPane implements CellChangeListener {

    private int size;
    private int boxSize;
    private static final int SIZE = 6;
    private static final int MAX_NUM_HINTS_LEFT = 2;
    private SudokuCell[][] cells;
    private int[][] generatedSudoku;
    private int[][] numbersSudoku;
    private int numInitialShowNumbers;
    private int numOccupiedCells;

    public SudokuBoard(int size) {
        this.size = size;
        this.boxSize = (int) Math.sqrt(size);
        this.numOccupiedCells = 0;
        cells = new SudokuCell[SIZE][SIZE];
        numInitialShowNumbers = 5;
        generateBoard(size);
    }

    public void generateBoard(int size) {
        this.getChildren().clear();

        SudokuGenerator sudokuGenerator = new SudokuGenerator(SIZE);
        generatedSudoku = sudokuGenerator.getBoard();
        numbersSudoku = copyBoard(generatedSudoku);

        hideRandomCells(numbersSudoku, (size * size) - numInitialShowNumbers);

        for (int row = 0; row < numbersSudoku.length; row++) {
            for (int col = 0; col < numbersSudoku.length; col++) {
                int value = numbersSudoku[row][col];
                boolean editable = value == 0;
                SudokuCell cell = new SudokuCell(row, col, editable, value);

                // Colores iniciales
                if (!editable) {
                    cell.setStyle("-fx-background-color: #e0e0e0; -fx-border-color: #808080;");
                } else {
                    cell.setStyle("-fx-background-color: white; -fx-border-color: #808080;");
                }

                cells[row][col] = cell;
                cell.setChangeListener(this);
                this.add(cell, col, row);
            }
        }
    }

    public static void hideRandomCells(int[][] board, int cellsToHide) {
        Random rand = new Random();
        int hidden = 0;
        while (hidden < cellsToHide) {
            int row = rand.nextInt(6);
            int col = rand.nextInt(6);
            if (board[row][col] != 0) {
                board[row][col] = 0;
                hidden++;
            }
        }
    }

    public void getHint() {
        Random rand = new Random();
        boolean found = false;
        numOccupiedCells = numberOccupiedCells();
        int hintsLeft = ((size * size) - numOccupiedCells);

        if (hintsLeft > MAX_NUM_HINTS_LEFT) {
            while (!found) {
                int row = rand.nextInt(6);
                int col = rand.nextInt(6);
                SudokuCell cell = cells[row][col];
                if (cell.getValue() == 0) {
                    int hint = generatedSudoku[row][col];
                    cell.setValue(hint);
                    cell.setEditable(false);
                    cell.setStyle("-fx-background-color: #FCFF26; -fx-border-color: #808080;"); // amarillo
                    found = true;
                }
            }
            // Revalidar tablero después de poner la pista
            updateCellColors();
        }
    }

    public int numberOccupiedCells() {
        int occupied = 0;
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                if (cells[i][j].getValue() != 0) occupied++;
            }
        }
        return occupied;
    }

    private int[][] copyBoard(int[][] board) {
        int[][] copy = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            System.arraycopy(board[i], 0, copy[i], 0, board[i].length);
        }
        return copy;
    }

    @Override
    public void onValueChanged(int row, int col, int newValue) {
        updateCellColors();
        checkCompletion();
    }

    private void updateCellColors() {
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                SudokuCell cell = cells[r][c];

                if (!cell.isEditable()) {
                    // Pistas iniciales o de getHint() no cambian de color
                    if (cell.getStyle().contains("#FCFF26")) continue; // amarillas
                    cell.setStyle("-fx-background-color: #e0e0e0; -fx-border-color: #808080;"); // gris
                    continue;
                }

                int val = cell.getValue();
                if (val == 0) {
                    cell.setStyle("-fx-background-color: white; -fx-border-color: #808080;");
                } else if (isValidMove(r, c, val)) {
                    cell.setStyle("-fx-background-color: #9EFF75; -fx-border-color: #808080;"); // verde
                } else {
                    cell.setStyle("-fx-background-color: #EB9478; -fx-border-color: #808080;"); // rojo
                }
            }
        }
    }

    private boolean isValidMove(int row, int col, int value) {
        // Validar fila y columna
        for (int i = 0; i < size; i++) {
            if ((i != col && cells[row][i].getValue() == value) ||
                    (i != row && cells[i][col].getValue() == value))
                return false;
        }

        // Validar bloque 2x3 para 6x6
        int startRow = (row / 2) * 2;
        int startCol = (col / 3) * 3;

        for (int r = startRow; r < startRow + 2; r++) {
            for (int c = startCol; c < startCol + 3; c++) {
                if ((r != row || c != col) && cells[r][c].getValue() == value)
                    return false;
            }
        }

        return true;
    }
    public void generateNewBoard(int size) {
        generateBoard(size);
        numOccupiedCells = 0;
    }

    private void checkCompletion() {
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                int val = cells[r][c].getValue();
                if (val == 0 || !isValidMove(r, c, val)) {
                    return; // el tablero aún no está completo o hay errores
                }
            }
        }

        // Si llegamos aquí, el tablero está completo y válido
        AlertsSudoku newAlert = new AlertsSudoku();
        newAlert.showAlert(Alert.AlertType.INFORMATION, "¡Felicidades!", "Has completado el Sudoku correctamente.");

        // Desactivar edición en todas las celdas
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                cells[r][c].setEditable(false);
            }
        }
    }


}
