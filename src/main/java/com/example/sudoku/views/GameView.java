package com.example.sudoku.views;

import com.example.sudoku.controllers.SudokuGameController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GameView extends Stage {
    private final SudokuGameController sudokuController;

    public GameView() throws IOException {

        //System.out.println(getClass().getResource("/com/example/typewordsgame/sudoku.fxml"));

        FXMLLoader loader = new FXMLLoader();
        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("/com/example/sudoku/sudokuGame.fxml")
        );

        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        this.setScene(scene);
        this.setTitle("Sudoku 6*6");
        this.sudokuController = loader.getController();

        this.show();

    }
    public SudokuGameController getSudokuController(){return sudokuController;};
}
