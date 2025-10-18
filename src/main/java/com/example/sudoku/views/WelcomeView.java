package com.example.sudoku.views;

import com.example.sudoku.controllers.WelcomeController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
/**
 * Class that represents the welcome view of the Sudoku game.
 * This class extends {@link Stage}, which means it acts like a JavaFX window.
 * Its function is to load and display the interface defined in the {@code welcomeView.fxml} file.
 * @author Victoria Yuan
 * @author Karen Hoyos
 * @version 2.0
 */
public class WelcomeView extends Stage {
    /**
     * Creates a new Sudoku welcome window and loads its interface
     * from the corresponding FXML file.
     *
     * @throws IOException if an error occurs while loading the FXML file.
     */
    public WelcomeView() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("/com/example/sudoku/welcomeView.fxml")
        );

        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 352, 450);
        this.setScene(scene);
        this.setTitle("Sudoku 6*6");
        this.setResizable(false);
        this.centerOnScreen();
        this.show();
        WelcomeController controller = fxmlLoader.getController();
        controller.setStage(this);



    }
}
