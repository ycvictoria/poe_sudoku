package com.example.sudoku.views;

import javafx.scene.control.TextField;
import javafx.scene.text.Font;

public class SudokuCell extends TextField {

    private  final int row;
    private  final int col;
    private boolean isEditable;

    public SudokuCell(int row, int col, boolean isEditable, int value) {
        this.row = row;
        this.col = col;
        this.isEditable = isEditable;

        setPrefSize(50, 50);
        setFont(new Font(18));
        setAlignment(javafx.geometry.Pos.CENTER);

        if (value != 0) {
            setText(String.valueOf(value));
            setEditable(false);
            setStyle("-fx-background-color: #e0e0e0; -fx-border-color: #808080;");
        } else {
            setEditable(isEditable);
            setStyle("-fx-background-color: white; -fx-border-color: #808080;");
        }

        // Asegurar que solo acepte un dígito del 1 al 6
        textProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.matches("[1-6]?")) {
                setText(oldVal);
            }
        });
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }


}
