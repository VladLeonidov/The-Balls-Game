package com.leus.model;

import com.leus.model.factories.figureFactories.FigureFactory;
import com.leus.model.graphics.figures.AbstractFigure;
import com.leus.model.graphics.sprites.AbstractSprite;
import com.leus.model.service.FieldManager;
import com.leus.model.service.FigureManager;
import com.leus.model.service.scores.ScoreManager;
import com.leus.view.displays.PcDisplay;

import javax.swing.*;
import java.util.Arrays;

public class GameField {

    public static final int TILE_WIDTH = 32;
    public static final int TILE_HEIGHT = 32;

    private static AbstractSprite[][] gameFieldMatrix = new AbstractSprite[PcDisplay.getHeightWindowInTile() + 1][PcDisplay.getWidthWindowInTile()];
    private static long delay = 500;

    private FigureManager figureManager = new FigureManager();
    private AbstractFigure figure;
    private FieldManager fieldManager;
    private AbstractSprite[] spritesInFigure;

    public GameField(FieldManager fieldManager, FigureFactory... figureFactories) {
        if (fieldManager == null || figureFactories == null) {
            throw new NullPointerException("Field Manager or Figure Factories is null: " + fieldManager + ", " + Arrays.toString(figureFactories));
        }

        if (figureFactories.length == 0) {
            throw new IllegalArgumentException("No FigureFactories");
        }

        figureManager.registrationFigureFactories(figureFactories);
        figure = figureManager.createFigure();
        this.fieldManager = fieldManager;
        this.spritesInFigure = figure.getSpritesInFigure();
    }

    public static AbstractSprite[][] getGameFieldMatrix() {
        return gameFieldMatrix;
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

    public void startGameProcess(JPanel canvas) {
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

                if (ScoreManager.isCanResetFactor()) {
                    ScoreManager.resetFactor();
                }

                figure = figureManager.createFigure();
                spritesInFigure = figure.getSpritesInFigure();
            } else {
                figure.moveDown();
            }

            canvas.repaint();
        }
    }

    private void upSpeedGame() {
        /*if (ScoreManager.getScore() % 500 == 0 && delay > 200) {
            delay -= 50;
        }*/
    }
}
