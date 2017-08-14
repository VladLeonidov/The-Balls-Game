package com.leus.model;

import com.leus.model.factories.figureFactories.FigureFactory;
import com.leus.model.graphics.figures.AbstractFigure;
import com.leus.model.graphics.sprites.AbstractSprite;
import com.leus.model.service.FieldManager;
import com.leus.model.service.FigureManager;
import com.leus.view.GameFrame;
import com.leus.view.panels.GamePanel;

import javax.swing.*;

public class GameField {

    public static final int TILE_WIDTH = 32;
    public static final int TILE_HEIGHT = 32;

    private static long delay = 500;
    private static AbstractSprite[][] gameFieldMatrix = new AbstractSprite[GameFrame.FIELD_HEIGHT_IN_TILE + 1][GameFrame.FIELD_WIDTH_IN_TILE];
    private static FigureManager figureManager = GameFrame.getFigureManager();

    private JPanel gamePanel;
    private AbstractFigure figure;
    private FieldManager fieldManager;
    private AbstractSprite[] spritesInFigure;

    public GameField(GamePanel gamePanel, FieldManager fieldManager, FigureFactory figureFactory) {
        figureManager.registrationFigureFactory(figureFactory);
        figure = figureManager.createFigure();
        this.gamePanel = gamePanel;
        this.fieldManager = fieldManager;
        this.spritesInFigure = figure.getSpritesInFigure();
    }

    public static AbstractSprite[][] getGameFieldMatrix() {
        return gameFieldMatrix;
    }

    public static long getDelay() {
        return delay;
    }

    public JPanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(JPanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public AbstractFigure getFigure() {
        return figure;
    }

    public boolean isGameOver() {
        for (AbstractSprite abstractSprite : spritesInFigure) {
            if (abstractSprite.isOutField()) {
                return true;
            }
        }

        return false;
    }

    public void startGameProcess() {
        gameLoop();
    }

    private void gameLoop() {
        while (!isGameOver()) {

            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (figure.isFrozen()) {
                figure.leaveOnTheField();
                fieldManager.moveDownSpritesInAir(gameFieldMatrix);
                fieldManager.clearSpriteFromField(gameFieldMatrix);
                while (fieldManager.isSpritesInAir(getGameFieldMatrix())) {
                    fieldManager.moveDownSpritesInAir(gameFieldMatrix);
                    fieldManager.clearSpriteFromField(gameFieldMatrix);
                }
                upSpeedGame();
                figure = figureManager.createFigure();
                spritesInFigure = figure.getSpritesInFigure();
            } else {
                figure.moveDown();
            }

            gamePanel.repaint();
        }
    }

    private void upSpeedGame() {
        /*if (ScoreManager.getScore() % 500 == 0 && delay > 200) {
            delay -= 50;
        }*/
    }
}
