package com.leus.controller;

import com.leus.model.fields.GameField;
import com.leus.model.graphics.figures.AbstractFigure;
import com.leus.model.graphics.sprites.AbstractSprite;
import com.leus.view.displays.GameFrame;
import com.leus.view.panels.GamePanel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BallsKeyController extends KeyAdapter {

    private GameField gameField = GameFrame.getGameField();
    private GamePanel gamePanel = gameField.getGamePanel();
    private AbstractFigure abstractFigure;
    private AbstractSprite[] spritesInFigure;

    @Override
    public void keyPressed(KeyEvent e) {

        abstractFigure = gameField.getAbstractFigure();
        spritesInFigure = abstractFigure.getSpritesInFigure();

        if (!gameField.isGameOver()) {
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                for (AbstractSprite abstractSprite : spritesInFigure) {
                    if (!abstractSprite.isFrozen()) {
                        abstractFigure.fastMoveDown();
                    }
                }
            }


            if (e.getKeyCode() == KeyEvent.VK_UP) {
                int countUnfrosenBalls = 0;
                for (AbstractSprite abstractSprite : spritesInFigure) {
                    if (!abstractSprite.isFrozen()) {
                        countUnfrosenBalls++;
                    }
                }

                if (countUnfrosenBalls == spritesInFigure.length) {
                    abstractFigure.rotate();
                }
            }

            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                int countUnfrozenBalls = 0;
                for (AbstractSprite abstractSprite : spritesInFigure) {
                    if (!abstractSprite.isFrozen()) {
                        countUnfrozenBalls++;
                    }
                }

                if (countUnfrozenBalls == spritesInFigure.length) {
                    abstractFigure.moveLeft();
                }
            }

            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                int countUnfrozenBalls = 0;
                for (AbstractSprite abstractSprite : spritesInFigure) {
                    if (!abstractSprite.isFrozen()) {
                        countUnfrozenBalls++;
                    }
                }

                if (countUnfrozenBalls == spritesInFigure.length) {
                    abstractFigure.moveRight();
                }
            }
        }

        gamePanel.repaint();
    }
}
