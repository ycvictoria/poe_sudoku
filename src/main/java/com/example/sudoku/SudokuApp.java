package com.example.sudoku;

import com.example.sudoku.models.*;
import com.example.sudoku.views.SudokuCell;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.geometry.Pos;

/*
public class SudokuApp extends Application {
/*
    private static final int SIZE = 6;

    @Override
    public void start(Stage primaryStage) {
        SudokuBoard sudokuBoard = new com.example.sudoku.SudokuBoard();
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(5);

        Cell[][] cells = sudokuBoard.getBoard();

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                TextField cellField = new TextField();
                cellField.setPrefSize(50, 50);
                cellField.setAlignment(Pos.CENTER);

                Cell cell = cells[row][col];
                int value = cell.getValue();

                if (cell.isFixed()) {
                    cellField.setText(String.valueOf(value));
                    cellField.setEditable(false);
                    cellField.setStyle("-fx-background-color: #dcdcdc; -fx-font-weight: bold; -fx-font-size: 16;");
                } else {
                    cellField.setText("");
                    cellField.setEditable(true);
                    cellField.setStyle("-fx-background-color: #ffffff; -fx-font-size: 16;");
                }

                // Solo permitir números del 1 al 6
                cellField.textProperty().addListener((obs, oldText, newText) -> {
                    if (!newText.matches("[1-6]?")) {
                        cellField.setText(oldText);
                    }
                });

                grid.add(cellField, col, row);
            }
        }

        Scene scene = new Scene(grid, 400, 400);
        primaryStage.setTitle("Sudoku 6x6 - Jugable");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

*/