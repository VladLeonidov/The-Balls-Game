package com.leus.model;

import com.leus.model.factories.figureFactories.FigureFactory;
import com.leus.model.graphics.figures.AbstractFigure;
import com.leus.model.graphics.sprites.AbstractSprite;
import com.leus.model.service.FieldManager;
import com.leus.model.service.FigureManager;
import com.leus.view.PcGameFrameBuilder;

import java.awt.*;
import java.util.Arrays;

public class GameField {

    public static final int TILE_WIDTH = 32;
    public static final int TILE_HEIGHT = 32;

    private static GameField gameField;
    private static long delay = 500;
    private static AbstractSprite[][] gameFieldMatrix = new AbstractSprite[PcGameFrameBuilder.getFieldHeightInTile() + 1][PcGameFrameBuilder.getFieldWidthInTile()];

    private FigureManager figureManager = new FigureManager();
    private AbstractFigure figure;
    private FieldManager fieldManager;
    private AbstractSprite[] spritesInFigure;

    private GameField(FieldManager fieldManager, FigureFactory... figureFactories) {
        if (figureFactories.length == 0) {
            throw new IllegalArgumentException("No FigureFactories" + Arrays.toString(figureFactories));
        }

        figureManager.registrationFigureFactories(figureFactories);
        figure = figureManager.createFigure();
        this.fieldManager = fieldManager;
        this.spritesInFigure = figure.getSpritesInFigure();
    }

    public static AbstractSprite[][] getGameFieldMatrix() {
        return gameFieldMatrix;
    }

    public static long getDelay() {
        return delay;
    }

    public static GameField getGameField(FieldManager fieldManager, FigureFactory... figureFactory) {
        if (gameField == null) {
            gameField = new GameField(fieldManager, figureFactory);
        }

        return gameField;
    }

    public AbstractFigure getFigure() {
        return figure;
    }

    public boolean isGameOver() {
        for (AbstractSprite elem : spritesInFigure) {
            if (elem.isOutField()) {
                return true;
            }
        }

        return false;
    }

    public void startGameProcess(Component panel) {
        gameLoop(panel);
    }

    private void gameLoop(Component panel) {
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

            panel.repaint();
        }
    }

    private void upSpeedGame() {
        /*if (ScoreManager.getScore() % 500 == 0 && delay > 200) {
            delay -= 50;
        }*/
    }
}
