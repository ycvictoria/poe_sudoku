package com.example.sudoku.controllers;

import com.example.sudoku.models.SudokuGenerator;
import com.example.sudoku.views.SudokuBoard;
import com.example.sudoku.views.SudokuCell;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.Random;

public class SudokuGameController {

    @FXML
    private GridPane boardContainer;

    @FXML
    private Button btnNuevo;

    @FXML
    private Button btnVerificar;

    @FXML
    private Button btnResolver;

    private static final int SIZE = 6;

    private SudokuBoard board;
    @FXML
    public void initialize() {
        board= new SudokuBoard(SIZE);
        boardContainer.getChildren().add(board);
        generateBoard();
    }

   private void generateBoard() {
    board.generateBoard(SIZE);
    }


    @FXML
    private void handleNuevoSudoku() {
        System.out.println("Generando nuevo Sudoku...");

        //Aqui insertar el dialogo alerta para confirmar si genera nuevo sudoku

        //si confirma, genera nuevo sudoku
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
