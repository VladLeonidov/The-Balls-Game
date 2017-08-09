package com.leus.model;


import com.leus.model.factories.figuresFactories.FigureFactory;
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
    public static final long DELAY = 500;

    private static AbstractSprite[][] gameFieldMatrix = new AbstractSprite[GameFrame.FIELD_HEIGHT_IN_TILE + 1][GameFrame.FIELD_WIDTH_IN_TILE];
    private static FigureManager figureManager = GameFrame.getFigureManager();
    private JPanel gamePanel;
    private AbstractFigure figure;
    private AbstractSprite[] spritesInFigure;
    private FieldManager fieldManager;

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
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (figure.isFrozen()) {
                figure.leaveOnTheField();
                fieldManager.clearSpriteFromField(gameFieldMatrix);
                fieldManager.moveDownSpritesInAir(gameFieldMatrix);
                fieldManager.clearSpriteFromField(gameFieldMatrix);
                figure = figureManager.createFigure();
                spritesInFigure = figure.getSpritesInFigure();
            } else {
                figure.moveDown();
            }

            getGamePanel().repaint();
        }
    }
}
