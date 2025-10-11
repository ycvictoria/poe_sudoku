package com.example.sudoku;

import com.example.sudoku.models.SudokuGenerator;
import com.example.sudoku.views.SudokuCell;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.Random;

public class SudokuBoardController {

    @FXML
    private GridPane boardContainer;

    @FXML
    private Button btnNuevo;

    @FXML
    private Button btnVerificar;

    @FXML
    private Button btnResolver;

    private static final int SIZE = 6;
    private SudokuCell[][] cells = new SudokuCell[SIZE][SIZE];

    @FXML
    public void initialize() {
        generateBoard();
    }

    private void generateBoard() {
        boardContainer.getChildren().clear();
        SudokuGenerator sudokuGenerator = new SudokuGenerator(SIZE);
        int[][] sudoku= sudokuGenerator.getBoard();
        hideRandomCells(sudoku,30);

        for (int row = 0; row < sudoku.length; row++) {
            for (int col = 0; col < sudoku.length; col++) {
               /** int value = Math.random() < 0.3 ? (int) (Math.random() * 6 + 1) : 0; // 30% con números fijos*/
                int value = sudoku[row][col];
                boolean editable = value == 0;
                //if(value==0){editable=false;}else {editable=true;}
                SudokuCell cell = new SudokuCell(row, col, editable, value);
                cells[row][col] = cell;
                boardContainer.add(cell, col, row);

            }
        }

    }


    public static void hideRandomCells(int[][] board, int cellsToHide) {
        Random rand = new Random();
        int hidden = 0;

        while (hidden < cellsToHide) {
            int row = rand.nextInt(6);
            int col = rand.nextInt(6);

            // Si la celda aún no está vacía, la ocultamos
            if (board[row][col] != 0) {
                board[row][col] = 0;  // 0 representa vacío
                hidden++;
            }
        }
    }

    @FXML
    private void handleNuevoSudoku() {
        System.out.println("Generando nuevo Sudoku...");
        generateBoard();
    }

    @FXML
    private void handlePista() {
        System.out.println("Verificando Sudoku...");
        // Aquí podrías implementar la lógica de validación
    }

    @FXML
    private void handleResolver() {
        System.out.println("Resolviendo Sudoku...");
        // Aquí podrías poner un algoritmo de resolución
    }
}
