package com.example.sudoku.controllers;


/**
 * Interface that defines a listener for detecting changes to a Sudoku board cell.
 * Classes that implement this interface must define what happens when a cell's value
 *
 * @author Victoria Yuan
 * @version 1.0
 */
public interface CellChangeListener {
    /**
     * Executed when a cell on the board changes value.
     * @param row the row of the cell that changed.
     * @param col the column of the cell that changed.
     * @param newValue the new value assigned to the cell
     */
    void onValueChanged(int row, int col, int newValue);
}