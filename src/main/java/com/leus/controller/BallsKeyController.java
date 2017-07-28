package com.leus.controller;

import com.leus.model.GameField;
import com.leus.model.graphics.figures.AbstractFigure;
import com.leus.model.graphics.sprites.AbstractSprite;
import com.leus.view.displays.GameFrame;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BallsKeyController extends KeyAdapter {

    private GameField gameField = GameFrame.getGameFrame().getGameField();
    private JPanel gamePanel = gameField.getGamePanel();
    private AbstractFigure abstractFigure;
    private AbstractSprite[] spritesInFigure;

    @Override
    public void keyPressed(KeyEvent e) {

        abstractFigure = gameField.getFigure();
        spritesInFigure = abstractFigure.getSpritesInFigure();

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
        }

        gamePanel.repaint();
    }

    private void keyUp() {
        int countUnfrozenBalls = 0;
        for (AbstractSprite abstractSprite : spritesInFigure) {
            if (!abstractSprite.isFrozen()) {
                countUnfrozenBalls++;
            }
        }

        if (countUnfrozenBalls == spritesInFigure.length) {
            abstractFigure.rotate();
        }
    }

    private void keyLeft() {
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

    private void keyRight() {
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

    private void keyDown() {
        for (AbstractSprite abstractSprite : spritesInFigure) {
            if (!abstractSprite.isFrozen()) {
                abstractFigure.fastMoveDown();
            }
        }
    }
}
