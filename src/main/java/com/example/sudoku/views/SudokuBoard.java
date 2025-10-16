package com.example.sudoku.views;

import com.example.sudoku.models.SudokuGenerator;
import com.example.sudoku.views.SudokuCell;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

import java.util.Random;

public class SudokuBoard extends GridPane {

    private int size;

    private static final int SIZE = 6;
   // private SudokuCell[][] cells = new SudokuCell[SIZE][SIZE];
   private SudokuCell[][] cells;
   private int[][] numerosSudoku;

    public SudokuBoard(int size) {
        this.size = size;
        cells = new SudokuCell[SIZE][SIZE];
        generateBoard(size);
    }

    public void generateBoard(int size) {

        this.getChildren().clear();
        //Aqui genera un nuevo sudoku int[][] y lo almacena en numerosSudoku
        SudokuGenerator sudokuGenerator = new SudokuGenerator(SIZE);
        numerosSudoku= sudokuGenerator.getBoard();

        hideRandomCells(numerosSudoku,30);

        for (int row = 0; row < numerosSudoku.length; row++) {
            for (int col = 0; col < numerosSudoku.length; col++) {
                /** int value = Math.random() < 0.3 ? (int) (Math.random() * 6 + 1) : 0; // 30% con números fijos*/
                int value = numerosSudoku[row][col];
                boolean editable = value == 0;
                //if(value==0){editable=false;}else {editable=true;}
                SudokuCell cell = new SudokuCell(row, col, editable, value);
                cells[row][col] = cell;
                //boardContainer.add(cell, col, row);
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

            // Si la celda aún no está vacía, la ocultamos
            if (board[row][col] != 0) {
                board[row][col] = 0;  // 0 representa vacío
                hidden++;
            }
        }
    }
    public int[][] getNumerosSudoku(){
        return numerosSudoku;
    }

}
