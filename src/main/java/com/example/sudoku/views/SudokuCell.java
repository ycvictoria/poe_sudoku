package com.example.sudoku.views;

import com.example.sudoku.controllers.CellChangeListener;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

public class SudokuCell extends TextField {

    private  final int row;
    private  final int col;
    private boolean isEditable;
    private  int value;
    private CellChangeListener listener;

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
    /**
     * Listener que detecta cambios de texto y notifica al tablero.
     */
    private void configureTextListener() {
        textProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.isEmpty()) {
                this.value = 0;
                if (listener != null) listener.onValueChanged(row, col, 0);
                return;
            }

            if (newVal.matches("[1-9]")) { // acepta dígitos válidos
                this.value = Integer.parseInt(newVal);
                if (listener != null) listener.onValueChanged(row, col, value);
            } else {
                // Rechazar entrada inválida
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
        //setText(String.valueOf(num));
        setEditable(false);


    }
    /**
     * Changes the color of the cell depending on valid or not.
     */
    public void setValid(boolean isValid) {
        if (!isEditable) {
            // Don´t modify initial hints
            setStyle("-fx-background-color: #e0e0e0; -fx-border-color: #808080;");
            return;
        }

        if (isValid) {
            // Valid cell or empty yet
            setStyle("-fx-background-color: white; -fx-border-color: #808080;");
        } else {
            // Incorrect cell
            setStyle("-fx-background-color: #ffb3b3; -fx-border-color: #ff0000;");
        }
    }


    public void setChangeListener(CellChangeListener listener) {
        this.listener = listener;
    }

}
