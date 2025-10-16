package com.example.sudoku.controllers;

import com.example.sudoku.models.SudokuBoard;
import com.example.sudoku.utils.AlertsSudoku;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

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

    private AlertsSudoku alerts;
    @FXML
    public void initialize() {
        board= new SudokuBoard(SIZE);
        alerts= new AlertsSudoku();
        boardContainer.getChildren().add(board);
        generateBoard();
    }

   private void generateBoard() {
    board.generateBoard(SIZE);
    }


    @FXML
    private void handleNuevoSudoku() {
        System.out.println("Generando nuevo Sudoku...");
        //Here insert alert to confirm new game
        boolean confirms= alerts.showConfirmationNewGame();
        //if confirms, generate new sudoku
        if(confirms){
            board.generateNewBoard(SIZE);
        }

    }

    @FXML
    private void handlePista() {

        System.out.println("Mostrando Pista Sudoku...");
        board.getHint();
    }

    @FXML
    private void handleResolver() {
        System.out.println("Resolviendo Sudoku...");

        // Aquí podrías poner un algoritmo de resolución
    }
}
