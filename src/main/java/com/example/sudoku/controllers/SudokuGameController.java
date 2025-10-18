package com.example.sudoku.controllers;

import com.example.sudoku.models.SudokuBoard;
import com.example.sudoku.utils.AlertsSudoku;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

/**
 * The main controller for the Sudoku game.
 * This class manages the interaction between the view and the game logic.
 * It is responsible for initializing the board, handling button events,
 * and communicating with the {@link SudokuBoard} class.
 * @author Victoria Yuan
 * @version 1.0
 */
public class SudokuGameController {
    /** Visual container for the Sudoku board. */
    @FXML
    private GridPane boardContainer;
    /** Button to generate a new Sudoku. */
    @FXML
    private Button btnNuevo;
    /** Button to check the current state of the board. */
    @FXML
    private Button btnVerificar;
    /** Button to automatically solve the Sudoku. */
    @FXML
    private Button btnResolver;
    /** Board size (6x6). */
    private static final int SIZE = 6;
    /** Sudoku board that handles logic and visualization. */
    private SudokuBoard board;
    /** Object to display alerts and confirmations. */
    private AlertsSudoku alerts;
    /**
     * Method called automatically when the FXML interface loads.
     * Initializes the dashboard, configures alerts, and adds the dashboard
     * to the view's visual container.
     */
    @FXML
    public void initialize() {
        board= new SudokuBoard(SIZE);
        alerts= new AlertsSudoku();
        boardContainer.getChildren().add(board);
        generateBoard();
    }
    /**
     * Generates a new Sudoku board.
     */
    private void generateBoard() {
        board.generateBoard(SIZE);
    }
    /**
     * Handles the "Nuevo Sudoku" button event.
     * Displays a confirmation alert before generating a new board.
     */
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
    /**
     * Handles the "Pista" button event.
     * Requests the board to reveal a cell with its correct value.
     */
    @FXML
    private void handlePista() {

        System.out.println("Mostrando Pista Sudoku...");
        board.getHint();
    }

    @FXML
    private void handleResolver() {
        System.out.println("Resolviendo Sudoku...");
        board.validateBoardOnDemand();
    }
}
