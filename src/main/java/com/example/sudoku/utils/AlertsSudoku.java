package com.example.sudoku.utils;

public class AlertsSudoku extends AlertAdapter {

    public boolean showConfirmationNewGame( ) {
        String title= "NUEVO JUEGO";
        String message= "¿QUIERES INICIAR \nUN NUEVO SUDOKU?\nRecuerda que los datos actuales se borrarán";
        return super.showConfirmation(title, message);
    }

}
