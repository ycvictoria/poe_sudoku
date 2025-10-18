package com.example.sudoku;

import com.example.sudoku.views.WelcomeView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
/**
 * Main class for the Sudoku game.
 * Its function is to initialize the game's main window by creating an instance
 * of gameView and displaying it on the screen.
 * @author Victoria Yuan
 * @version 1.0
 */
public class HelloApplication extends Application {
    /**
     * JavaFX application startup method.
     * Runs automatically after the JavaFX environment has been initialized.
     * In this case, it creates and displays a new Sudoku window via {@link WelcomeView}.
     * @param stage The main window provided by the JavaFX system.
     * @throws Exception if an error occurs while initializing the game window.
     */
    @Override

    public void start(Stage stage) throws Exception {

        new WelcomeView().show();
    }
    /**
     * Standard application entry point.
     * @param args Command-line arguments (optional).
     */
    public static void main(String[] args) {
        launch();
    }


}
