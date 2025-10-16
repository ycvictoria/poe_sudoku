package com.example.sudoku.utils;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class CountdownTimer {

    private Timeline timeline;
    private int tiempoRestante;
    private Runnable onTick;     // qué hacer cada segundo
    private Runnable onTimeout;  // qué hacer cuando termina

    public CountdownTimer(int tiempoInicial, Runnable onTick, Runnable onTimeout) {
        this.tiempoRestante = tiempoInicial;
        this.onTick = onTick;
        this.onTimeout = onTimeout;
    }

    public void start() {
        if (timeline != null) timeline.stop();

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            tiempoRestante--;
            if (onTick != null) onTick.run();

            if (tiempoRestante <= 0) {
                stop();
                if (onTimeout != null) onTimeout.run();
            }
        }));
        timeline.setCycleCount(tiempoRestante);
        timeline.play();
    }

    public void stop() {
        if (timeline != null) timeline.stop();
    }

    public int getTiempoRestante() {
        return tiempoRestante;
    }

    public void reset(int nuevoTiempo) {
        stop();
        this.tiempoRestante = nuevoTiempo;
    }
}
