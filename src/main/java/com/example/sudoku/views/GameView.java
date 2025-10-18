package com.example.sudoku.views;

import com.example.sudoku.controllers.SudokuGameController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
/**
 * The main window of the Sudoku game.
 * This class extends {@link Stage} and loads the game's visual interface
 * from the FXML file. It also maintains a reference to the
 * associated {@link SudokuGameController} controller,
 * allowing interaction with the game logic.
 * @author Victoria Yuan
 * @version 1.0
 */
public class GameView extends Stage {
    /** Controller associated with the Sudoku board. */
    private final SudokuGameController sudokuController;
    /**
     * Creates a new Sudoku game window.
     * Loads the corresponding FXML file, configures the scene,
     * sets the window title, and displays it on the screen.
     * @throws IOException if an error occurs while loading the FXML file.
     */
    public GameView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("/com/example/sudoku/sudokuGame.fxml")
        );

        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 500, 500);
        this.setScene(scene);
        this.setTitle("Sudoku 6*6");
        this.setResizable(false);
        this.centerOnScreen();
        this.sudokuController = loader.getController();

        this.show();

    }
    /**
     * Returns the Sudoku game controller.
     * @return an instance of {@link SudokuGameController}.
     */
    public SudokuGameController getSudokuController(){return sudokuController;};

}

