package com.leus.model;

import com.leus.UI.listeners.ButtonListener;
import com.leus.UI.listeners.DeactivateListener;
import com.leus.UI.listeners.GameOverListener;
import com.leus.UI.listeners.KeyControllerListener;
import com.leus.model.factories.figureFactories.FigureFactory;
import com.leus.model.graphics.figures.AbstractFigure;
import com.leus.model.graphics.sprites.AbstractSprite;
import com.leus.model.service.FieldManager;
import com.leus.model.service.FigureManager;
import com.leus.model.service.scores.ScoreManager;
import com.leus.utils.SettingsInitializer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {

    public static final int TILE_WIDTH = Integer.valueOf(SettingsInitializer.getPropertyValue("WidthTile"));
    public static final int TILE_HEIGHT = Integer.valueOf(SettingsInitializer.getPropertyValue("HeightTile"));

    private static int delay = 500;

    private static AbstractSprite[][] gameFieldMatrix =
            new AbstractSprite[Integer.valueOf(SettingsInitializer.getPropertyValue("HeightWindowInTile")) + 1]
                              [Integer.valueOf(SettingsInitializer.getPropertyValue("WidthWindowInTile"))];

    private static final UpSpeedGameStrategy DEFAULT_UP_SPEED_GAME = () -> {
        long currentScore = ScoreManager.getScore();
        boolean lowScore = currentScore > 1000 && currentScore < 2000;
        boolean midScore = currentScore > 2000 && currentScore < 3000;
        boolean highScore = currentScore > 3000 && currentScore < 6000;
        boolean veryHighScore = currentScore > 6000 && currentScore < 9000;
        boolean superScore = currentScore > 9000;
        if (lowScore) {
            delay = 450;
        } else if (midScore) {
            delay = 375;
        } else if (highScore) {
            delay = 300;
        } else if (veryHighScore) {
            delay = 200;
        } else if (superScore) {
            delay = 125;
        }
    };

    private List<DeactivateListener> listeners = new ArrayList<>();
    private List<GameOverListener> gameOverListeners = new ArrayList<>();
    private FigureManager figureManager = new FigureManager();
    private UpSpeedGameStrategy strategySpeedGame = DEFAULT_UP_SPEED_GAME;
    private AbstractFigure figure;
    private FieldManager fieldManager;
    private AbstractSprite[] spritesInFigure;
    private Timer timer = new Timer(delay, e -> startHelper());
    private boolean active;
    private boolean gameOver;

    public Game(FieldManager fieldManager, FigureFactory... figureFactories) {
        if (fieldManager == null ) {
            throw new NullPointerException("Field Manager can't be null");
        }

        if (figureFactories == null || figureFactories.length == 0) {
            throw new IllegalArgumentException("No FigureFactories");
        }

        figureManager.registrationFigureFactories(figureFactories);
        figure = figureManager.createFigure();
        this.fieldManager = fieldManager;
        this.spritesInFigure = figure.getSpritesInFigure();
    }

    public static AbstractSprite[][] getGameFieldMatrix() {
        return Arrays.copyOf(gameFieldMatrix, gameFieldMatrix.length);
    }

    public static void setSizeGameFieldMatrix(int widthFieldInTile, int heightFieldInTile) {
        if (widthFieldInTile <= 0 || heightFieldInTile <= 0) {
            throw new IllegalArgumentException("Width or height field In tile can't be 0 or negative" + widthFieldInTile + ", " + heightFieldInTile);
        }

        gameFieldMatrix = new AbstractSprite[heightFieldInTile + 1][widthFieldInTile];
    }

    public boolean isActive() {
        return active;
    }

    public AbstractFigure getFigure() {
        return figure;
    }

    public void setStrategySpeedGame(UpSpeedGameStrategy speedGameStrategy) {
        this.strategySpeedGame = speedGameStrategy;
    }

    public void start() {
        timer.start();
    }

    public void restart() {
        restartHelper();
        timer.restart();
    }

    private void startHelper() {

        if (!isGameOver()) {
            if (figure.isFrozen()) {
                figure.leaveOnTheField();

                fieldManager.moveDownSpritesInAir(gameFieldMatrix);
                fieldManager.clearSpriteFromField(gameFieldMatrix);

                while (fieldManager.isSpritesInAir(gameFieldMatrix)) {
                    fieldManager.moveDownSpritesInAir(gameFieldMatrix);
                    fieldManager.clearSpriteFromField(gameFieldMatrix);
                }

                if (ScoreManager.isCanResetFactor()) {
                    ScoreManager.resetFactor();
                }

                strategySpeedGame.upSpeedGame();

                figure = figureManager.createFigure();
                spritesInFigure = figure.getSpritesInFigure();
            } else {
                figure.moveDown();
            }

            timer.setDelay(delay);
        }
    }

    private void restartHelper() {
        delay = 500;
        gameOver = false;
        clearField();
        ScoreManager.resetScore();
    }

    @Override
    public String toString() {
        return "Game{" + "figure=" + figure +
                ", active=" + active + '}';
    }

    public void paint(Graphics g) {
        figure.paint(g);
        for (AbstractSprite[] line : gameFieldMatrix) {
            for (AbstractSprite elem : line) {
                if (elem != null) {
                    elem.paint(g);
                }
            }
        }

        ScoreManager.drawScore(g, ScoreManager.DEFAULT_POSITION_SCORE_X, ScoreManager.DEFAULT_POSITION_SCORE_Y, ScoreManager.DEFAULT_FONT_FOR_SCORE, ScoreManager.getFactorAsString());
    }

    public void addListener(DeactivateListener listener) {
        listeners.add(listener);
    }

    public void removeListener(DeactivateListener listener) {
        listeners.remove(listener);
    }

    public void addListener(GameOverListener listener) {
        gameOverListeners.add(listener);
    }

    public void removeListener(GameOverListener listener) {
        gameOverListeners.remove(listener);
    }

    private boolean isGameOver() {
        for (AbstractSprite elem : spritesInFigure) {
            if (elem.isOutField()) {
                active = false;
                gameOver = true;
                timer.stop();
                for (GameOverListener listener : gameOverListeners) {
                    listener.onEvent();
                }
                return true;
            }
        }

        return false;
    }

    private void clearField() {
        for (AbstractSprite[] line : gameFieldMatrix) {
            for (AbstractSprite elem : line) {
                if (elem != null) {
                    elem.clear();
                }
            }
        }
    }

    public class GameKeyListener implements KeyControllerListener {
        @Override
        public boolean isActive() {
            return active;
        }

        @Override
        public void onEvent(KeyEvent e) {
            if (active) {
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

    public class ButtonListenerImpl implements ButtonListener {
        @Override
        public void onEvent() {
            for (DeactivateListener listener : listeners) {
                listener.deactivate();
            }

            active = true;
            if (!gameOver) {
                start();
            } else {
                restart();
            }
        }
    }

    public class DeactivateListenerImpl implements DeactivateListener {
        @Override
        public void deactivate() {
            active = false;
        }
    }

}