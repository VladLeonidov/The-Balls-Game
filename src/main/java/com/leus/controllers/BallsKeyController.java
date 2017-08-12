package com.leus.controllers;

import com.leus.model.GameField;
import com.leus.model.graphics.figures.AbstractFigure;
import com.leus.view.GameFrame;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BallsKeyController extends KeyAdapter {

    private GameFrame gameFrame = GameFrame.getGameFrame(null, null, null);
    private GameField gameField = gameFrame.getGameField();
    private JFrame canvas = gameFrame.getCanvas();
    private JPanel gamePanel = gameField.getGamePanel();
    private AbstractFigure figure = gameField.getFigure();

    public void keyPressed(KeyEvent e) {
        figure = gameField.getFigure();

        if (!gameField.isGameOver()) {

            gamePanel.repaint();

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
