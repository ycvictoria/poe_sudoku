package com.example.sudoku.views;

import com.example.sudoku.controllers.CellChangeListener;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import com.example.sudoku.utils.AlertsSudoku;


/**
 * Represents an individual cell on the Sudoku board.
 * Extends {@link TextField} to allow entry of numbers between 1 and 9,
 * and also applies validations and visual styles based on the game state.
 * Each cell knows its position on the board (row and column),
 * if editable, its current value, and can notify changes via
 * a {@link CellChangeListener}.
 * @author Victoria Yuan
 * @author  Karen Hoyos
 * @version 1.0
 */
public class SudokuCell extends TextField {

    private  final int row;
    private  final int col;
    private boolean isEditable;
    private  int value;
    private CellChangeListener listener;

    /**
     * Creates a new Sudoku cell with the specified parameters.
     * @param row the cell's row
     * @param col the cell's column
     * @param isEditable indicates whether the cell can be edited by the user
     * @param value the cell's initial value (0 if empty)
     */
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
            setStyle("-fx-background-color: #ffffff; -fx-border-color: #b0b0b0;");
        } else {
            setEditable(isEditable);
            setStyle("-fx-background-color: white; -fx-border-color: #808080;");
        }

        configureTextListener();
    }
    /**
     * Configures a listener that detects when the user types in the cell.
     * Only accepts numbers from 1 to 6 and notifies the {@link CellChangeListener}.
     */
    private void configureTextListener() {
        textProperty().addListener((obs, oldVal, newVal) -> {
            AlertsSudoku alert = new AlertsSudoku();
            if (newVal.isEmpty()) {
                this.value = 0;
                if (listener != null) listener.onValueChanged(row, col, 0);
                return;
            }
            if (newVal.matches("[1-6]")) {
                this.value = Integer.parseInt(newVal);
                if (listener != null) listener.onValueChanged(row, col, value);
            } else {
                setText(oldVal);
                alert.showAlert(
                        javafx.scene.control.Alert.AlertType.ERROR,
                        "Entrada inválida",
                        "Solo se permiten números del 1 al 6."
                );
            }
        });
    }
    /** @return the cell row. */
    public int getRow() {
        return row;
    }
    /** @return the cell's column. */
    public int getCol() {
        return col;
    }
    /** @return the current value of the cell. */
    public int getValue(){return value;}
    /**
     * Changes the internal and visual value of the cell.
     * @param newValue new value to set (0 for empty)
     */
    public void setValue(int newValue){
        this.value = newValue;
        System.out.println("setValue() -> value = " + this.value);
        if (value != 0) {
            setText(String.valueOf(value));

        } else {
            setText("");
        }


    }
    /**
     * Displays a track with a suggested number.
     * Changes the background color to yellow and locks editing.
     * @param num suggested number to display
     */
    public void showHint(int num) {
        setStyle("-fx-background-color: #FCFF26; -fx-border-color: #808080;");
        setValue(num);
        setEditable(false);


    }
    /**
     * Changes the color of the cell depending on valid or not.
     * @param isValid true if the value is correct, false if it is invalid.
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
    /**
     * Assigns an external listener that will be notified
     * when the cell value changes.
     * @param listener object that implements {@link CellChangeListener}.
     */
    public void setChangeListener(CellChangeListener listener) {
        this.listener = listener;
    }

}
