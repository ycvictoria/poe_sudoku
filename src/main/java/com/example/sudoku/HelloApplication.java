package com.example.sudoku;

import com.example.sudoku.views.GameView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.example.sudoku.views.WelcomeView;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        GameView gameView= new GameView();
        gameView.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
