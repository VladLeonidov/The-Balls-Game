package com.leus.model;

import com.leus.model.factories.figureFactories.FigureFactory;
import com.leus.model.graphics.figures.AbstractFigure;
import com.leus.model.graphics.sprites.AbstractSprite;
import com.leus.model.listeners.ActiveListener;
import com.leus.model.listeners.ButtonListener;
import com.leus.model.listeners.GameOverListener;
import com.leus.model.listeners.KeyControllerListener;
import com.leus.model.service.FieldManager;
import com.leus.model.service.FigureManager;
import com.leus.model.service.scores.ScoreManager;
import com.leus.view.displays.Display;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {

    public static final int TILE_WIDTH = 32;
    public static final int TILE_HEIGHT = 32;

    private static long delay = 500;

    private static final AbstractSprite[][] GAME_FIELD_MATRIX = new AbstractSprite[Display.getHeightWindowInTile() + 1][Display.getWidthWindowInTile()];
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

    private List<ActiveListener> listeners = new ArrayList<>();
    private List<GameOverListener> gameOverListeners = new ArrayList<>();
    private FigureManager figureManager = new FigureManager();
    private UpSpeedGameStrategy strategySpeedGame = DEFAULT_UP_SPEED_GAME;
    private AbstractFigure figure;
    private FieldManager fieldManager;
    private AbstractSprite[] spritesInFigure;
    private JPanel canvas;

    public void setCanvas(JPanel canvas) {
        this.canvas = canvas;
    }

    private boolean active;
    private boolean gameOver;

    public Game(FieldManager fieldManager, FigureFactory... figureFactories) {
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

        while (!isGameOver()) {

            canvas.repaint();
            System.out.println("repaint");

            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (figure.isFrozen()) {
                figure.leaveOnTheField();

                fieldManager.moveDownSpritesInAir(GAME_FIELD_MATRIX);
                fieldManager.clearSpriteFromField(GAME_FIELD_MATRIX);

                while (fieldManager.isSpritesInAir(GAME_FIELD_MATRIX)) {
                    fieldManager.moveDownSpritesInAir(GAME_FIELD_MATRIX);
                    fieldManager.clearSpriteFromField(GAME_FIELD_MATRIX);
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
        }
    }

    @Override
    public String toString() {
        return "Game{" + "figure=" + figure +
                ", active=" + active + '}';
    }

    public void paint(Graphics g) {
        figure.paint(g);
        for (AbstractSprite[] line : GAME_FIELD_MATRIX) {
            for (AbstractSprite elem : line) {
                if (elem != null) {
                    elem.paint(g);
                }
            }
        }
    }

    public void addListener(ActiveListener listener) {
        listeners.add(listener);
    }

    public void removeListener(ActiveListener listener) {
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
                for (GameOverListener listener : gameOverListeners) {
                    listener.onEvent();
                }
                return true;
            }
        }

        return false;
    }

    public void restart() {
        Arrays.fill(GAME_FIELD_MATRIX, null);
        delay = 500;
        ScoreManager.resetScore();
        start();
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
            for (ActiveListener listener : listeners) {
                listener.active();
            }

            active = true;
            System.out.println(Game.this.toString());
            if (!gameOver) {
                start();
            } else {
                restart();
            }
        }
    }

    public class ActiveListenerImpl implements ActiveListener {
        @Override
        public void active() {
            active = false;
        }
    }
}
