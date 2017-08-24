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

    private static long delay = 500;

    private static final AbstractSprite[][] GAME_FIELD_MATRIX = new AbstractSprite[PcDisplay.getHeightWindowInTile() + 1][PcDisplay.getWidthWindowInTile()];
    private static final UpSpeedGameStrategy DEFAULT_UP_SPEED_GAME = () -> {
        long currentScore = ScoreManager.getScore();
        boolean lowScore = currentScore > 1000 && currentScore < 2000;
        boolean midScore = currentScore > 2000 && currentScore < 3000;
        boolean hightScore = currentScore > 3000 && currentScore < 6000;
        boolean veryHightScore = currentScore > 6000 && currentScore < 9000;
        boolean superScore = currentScore > 9000;
        if (lowScore) {
            delay = 450;
        } else if (midScore) {
            delay = 375;
        } else if (hightScore) {
            delay = 300;
        } else if (veryHightScore) {
            delay = 200;
        } else if (superScore) {
            delay = 125;
        }
    };

    private FigureManager figureManager = new FigureManager();
    private UpSpeedGameStrategy upSpeedGame = DEFAULT_UP_SPEED_GAME;
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
        return Arrays.copyOf(GAME_FIELD_MATRIX, GAME_FIELD_MATRIX.length);
    }

    public AbstractFigure getFigure() {
        return figure;
    }

    public void setUpSpeedGame(UpSpeedGameStrategy speedGameStrategy) {
        this.upSpeedGame = speedGameStrategy;
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

                fieldManager.moveDownSpritesInAir(GAME_FIELD_MATRIX, canvas);
                fieldManager.clearSpriteFromField(GAME_FIELD_MATRIX);

                while (fieldManager.isSpritesInAir(getGameFieldMatrix())) {
                    fieldManager.moveDownSpritesInAir(GAME_FIELD_MATRIX, canvas);
                    fieldManager.clearSpriteFromField(GAME_FIELD_MATRIX);
                }

                if (ScoreManager.isCanResetFactor()) {
                    ScoreManager.resetFactor();
                }

                upSpeedGame.upSpeedGame();

                figure = figureManager.createFigure();
                spritesInFigure = figure.getSpritesInFigure();
            } else {
                figure.moveDown();
            }

            canvas.repaint();
        }
    }
}
