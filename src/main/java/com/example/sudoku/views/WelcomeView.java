package com.example.sudoku.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeView extends Stage {

    public WelcomeView() throws IOException {
        //  this.setTitle("Craps Game");
        System.out.println(getClass().getResource("/com/example/typewordsgame/sudoku.fxml"));

        FXMLLoader fxmlLoader = new FXMLLoader(

                getClass().getResource("/com/example/sudoku/sudoku.fxml")
        );

        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        this.setScene(scene);


    }
}
