module com.example.sudoku {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;


    opens com.example.sudoku to javafx.fxml;
    opens com.example.sudoku.controllers to javafx.fxml;
    opens com.example.sudoku.views to javafx.fxml;
    exports com.example.sudoku;
    opens com.example.sudoku.models to javafx.fxml;
}