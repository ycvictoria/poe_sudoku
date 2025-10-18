package com.example.sudoku.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Sudoku game welcome window controller.
 * @author Karen Hoyos
 * @version 1.0
 */
public class WelcomeController {

    /** Stage associated with the welcome window. */
    private Stage stage;

    /**
     * Handles the action of the "Start Game" button.
     */
    @FXML
    private void handleIniciarJuego() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sudoku/sudokuGame.fxml"));
            Scene gameScene = new Scene(loader.load());

            if (stage != null) {
                stage.setScene(gameScene);
                stage.setTitle("Sudoku 6x6");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the action of the "How to Play" button.
     */
    @FXML
    private void handleAyuda() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cómo jugar");
        alert.setHeaderText("Instrucciones del Sudoku");
        alert.setContentText(
                "Llena el tablero de 6x6 con números del 1 al 6 sin repetir en filas, columnas ni regiones.\n\n" +
                        "- Haz clic en 'Iniciar Juego' para comenzar.\n" +
                        "- Llena cada celda respetando las reglas del Sudoku 6x6.\n" +
                        "- Puedes usar el botón 'Pista' para ayudarte.\n" +
                        "- Usa 'Resolver' solo si deseas ver la solución completa.\n\n" +
                        "¡Diviértete!"
        );
        alert.showAndWait();
    }

    /**
     * Assigns the window's Stage to the controller.
     * @param stage Stage of the welcome window
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
