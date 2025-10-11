package com.example.sudoku;

import com.example.sudoku.models.SudokuGenerator;
import com.example.sudoku.views.SudokuCell;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

public class SudokuBoard extends GridPane {
    private SudokuCell[][] cells;
    private int size;
    private int[][] boardData;

    public SudokuBoard(int size) {
        this.size = size;
        generateBoard();
    }

    private void generateBoard() {
        SudokuGenerator generator = new SudokuGenerator(size);
        boardData = generator.getBoard();
        cells = new SudokuCell[size][size];
        this.getChildren().clear();
        this.setAlignment(Pos.CENTER);
        this.setHgap(2);
        this.setVgap(2);

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                SudokuCell cell = new SudokuCell(row, col, true, 0);


                cells[row][col] = cell;
                this.add(cell, col, row);
            }
        }
    }

    public void generateNewBoard() {
        generateBoard();
    }
}
