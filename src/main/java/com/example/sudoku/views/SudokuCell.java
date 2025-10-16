package com.example.sudoku.views;

import javafx.scene.control.TextField;
import javafx.scene.text.Font;

public class SudokuCell extends TextField {

    private  final int row;
    private  final int col;
    private boolean isEditable;
    private  int value;

    public SudokuCell(int row, int col, boolean isEditable, int value) {
        this.row = row;
        this.col = col;
        this.value=value;
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

        configureTextListener();
    }
    /**
     * Method listener of the user typing.
     */
    private void configureTextListener() {
        textProperty().addListener((obs, oldVal, newVal) -> {
            //If user clear the cell
            if (newVal.isEmpty()) {
                this.value = 0;
                return;
            }
            //Only allows input -> 1-6 digit
            if (newVal.matches("[1-6]")) {
                this.value = Integer.parseInt(newVal);
                System.out.println("Nuevo valor ingresado: " + this.value);
            } else {
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

    public int getValue(){return value;}

    public void setValue(int newValue){
        this.value = newValue;
        System.out.println("setValue() -> value = " + this.value);
        if (value != 0) {
            setText(String.valueOf(value));
        } else {
            setText("");
        }


    }


    public void showHint(int num) {
        setStyle("-fx-background-color: #FCFF26; -fx-border-color: #808080;");
        setValue(num);
        setText(String.valueOf(num));
        System.out.println(value);
        setEditable(false);


    }
}
