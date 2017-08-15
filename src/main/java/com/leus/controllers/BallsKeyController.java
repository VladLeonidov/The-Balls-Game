package com.leus.controllers;

import com.leus.model.GameField;
import com.leus.model.graphics.figures.AbstractFigure;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BallsKeyController extends KeyAdapter {

    private GameField gameField;
    private JPanel gamePanel;
    private AbstractFigure figure;

    public BallsKeyController(GameField gameField, JPanel gamePanel) {
        this.gameField = gameField;
        this.gamePanel = gamePanel;
        this.gamePanel.setBackground(Color.white);
    }

    public JPanel getGamePanel() {
        return gamePanel;
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

            gamePanel.repaint();
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
