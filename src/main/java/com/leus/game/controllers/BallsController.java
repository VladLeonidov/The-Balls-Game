package com.leus.game.controllers;

import com.leus.displays.GameFrame;
import com.leus.game.fields.GameField;
import com.leus.game.graphics.Figure;
import com.leus.panels.GamePanel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BallsController extends KeyAdapter {

    private GameField gameField = GameFrame.getGameField();
    private GamePanel gamePanel = gameField.getGamePanel();
    private Figure figure;

    @Override
    public void keyPressed(KeyEvent e) {

        figure = gameField.getFigure();

        if (!gameField.isGameOver()) {
            if (!figure.getFirstBall().isFrozen() && !figure.getSecondBall().isFrozen()) {
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    figure.fastMoveDown();
                }
            }

            if (!figure.getFirstBall().isFrozen() && !figure.getSecondBall().isFrozen()) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    figure.rotate();
                }
            }

            if (!figure.getFirstBall().isFrozen() && !figure.getSecondBall().isFrozen()) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    figure.move(e.getKeyCode());
                }
            }

            gamePanel.repaint();
        }
    }
}
