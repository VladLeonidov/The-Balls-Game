package com.leus.controller;

import com.leus.model.fields.GameField;
import com.leus.model.graphics.figures.Figure;
import com.leus.model.graphics.sprites.Sprite;
import com.leus.view.displays.GameFrame;
import com.leus.view.panels.GamePanel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BallsKeyController extends KeyAdapter {

    private GameField gameField = GameFrame.getGameField();
    private GamePanel gamePanel = gameField.getGamePanel();
    private Figure figure;
    private Sprite[] spritesInFigure;

    @Override
    public void keyPressed(KeyEvent e) {

        figure = gameField.getFigure();
        spritesInFigure = figure.getSpritesInFigure();

        if (!gameField.isGameOver()) {
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                for (Sprite sprite : spritesInFigure) {
                    if (!sprite.isFrozen()) {
                        figure.fastMoveDown();
                    }
                }
            }


            if (e.getKeyCode() == KeyEvent.VK_UP) {
                int countUnfrosenBalls = 0;
                for (Sprite sprite : spritesInFigure) {
                    if (!sprite.isFrozen()) {
                        countUnfrosenBalls++;
                    }
                }

                if (countUnfrosenBalls == spritesInFigure.length) {
                    figure.rotate();
                }
            }

            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                int countUnfrozenBalls = 0;
                for (Sprite sprite : spritesInFigure) {
                    if (!sprite.isFrozen()) {
                        countUnfrozenBalls++;
                    }
                }

                if (countUnfrozenBalls == spritesInFigure.length) {
                    figure.moveLeft();
                }
            }

            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                int countUnfrozenBalls = 0;
                for (Sprite sprite : spritesInFigure) {
                    if (!sprite.isFrozen()) {
                        countUnfrozenBalls++;
                    }
                }

                if (countUnfrozenBalls == spritesInFigure.length) {
                    figure.moveRight();
                }
            }
        }

        gamePanel.repaint();
    }
}
