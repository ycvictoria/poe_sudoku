package com.example.sudoku.controllers;

public interface CellChangeListener {
    void onValueChanged(int row, int col, int newValue);
}