package com.leus.controllers;

import com.leus.model.GameField;
import com.leus.model.graphics.figures.AbstractFigure;
import com.leus.view.displays.PcDisplay;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BallsKeyController extends KeyAdapter {

    private GameField gameField;
    private PcDisplay window;
    private AbstractFigure figure;

    public BallsKeyController(GameField gameField, PcDisplay window) {
        this.gameField = gameField;
        this.window = window;
    }

    public GameField getGameField() {
        return gameField;
    }

    public PcDisplay getWindow() {
        return window;
    }

    public void keyPressed(KeyEvent e) {
        figure = gameField.getFigure();

        if (!gameField.isGameOver()) {

            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                keyDown();
            }

            if (e.getKeyCode() == KeyEvent.VK_UP) {
                keyUp();
            }

            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                keyLeft();
            }

            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                keyRight();
            }

            window.getCanvas().repaint();
        }
    }

    private void keyUp() {
        if (!figure.isFrozen()) {
            figure.rotate();
        }
    }

    private void keyLeft() {
        if (!figure.isFrozen()) {
            figure.moveLeft();
        }
    }

    private void keyRight() {
        if (!figure.isFrozen()) {
            figure.moveRight();
        }
    }

    private void keyDown() {
        if (!figure.isFrozen()) {
            figure.fastMoveDown();
        }
    }
}
