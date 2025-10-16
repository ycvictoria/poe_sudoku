package com.example.sudoku.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.util.Optional;

/**
 * Clase abstracta adaptadora que implementa GameEventListener.
 * Define métodos vacíos para los eventos del juego
 * y proporciona implementaciones por defecto para las alertas.
 */
public abstract class AlertAdapter implements GameAlertHandler {

    // ==== Eventos del juego (sobrescribir según necesidad) ====
    @Override public void onStart() {}
    @Override public void onPause() {}
    @Override public void onResume() {}
    @Override public void onEnd() {}
    @Override public void onError(String errorMessage) {
        showError("Error", errorMessage);
    }

    // ==== Implementaciones por defecto de los métodos de alerta ====
    @Override
    public void showInfo(String title, String message) {
        showAlert(Alert.AlertType.INFORMATION, title, message);
    }

    @Override
    public void showError(String title, String message) {
        showAlert(Alert.AlertType.ERROR, title, message);
    }

    @Override
    public void showWarning(String title, String message) {
        showAlert(Alert.AlertType.WARNING, title, message);
    }

    @Override
    public boolean showConfirmation(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

    // ==== Método interno auxiliar ====
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    boolean showConfirmationNewGame() {
        return false;
    }
}

/**
 * Interfaz que define los eventos y métodos de alerta del juego.
 */
interface GameAlertHandler {

    // ==== Eventos de juego ====
    void onStart();
    void onPause();
    void onResume();
    void onEnd();
    void onError(String errorMessage);

    // ==== Métodos de alerta generalizados ====
    void showInfo(String title, String message);
    void showError(String title, String message);
    void showWarning(String title, String message);
    boolean showConfirmation(String title, String message);
}
