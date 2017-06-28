package com.leus.model.fields;


import com.leus.model.graphics.figures.Figure;
import com.leus.model.graphics.figures.TwoBallFigure;
import com.leus.model.graphics.sprites.Sprite;
import com.leus.utils.Creator;
import com.leus.view.displays.GameFrame;
import com.leus.view.panels.GamePanel;

public class GameField implements Runnable {

    public static final int TILE_WIDTH = 32;
    public static final int TILE_HEIGHT = 32;
    public static final long DELAY = 750;
    public static final int MINIMAL_COUNT_BALLS_FOR_CLEAR = 4;

    private static Sprite[][] spritesOnField = new Sprite[GameFrame.FIELD_HEIGHT_IN_TILE + 2][GameFrame.FIELD_WIDTH_IN_TILE];

    private boolean isGameOver = false;
    private Figure figure;
    private GamePanel gamePanel = new GamePanel();
    private Sprite[] spritesInFigure;

    public static Sprite[][] getSpritesOnField() {
        return spritesOnField;
    }

    public boolean isGameOver() {
        for (Sprite sprite : spritesInFigure) {
            isGameOver = sprite.isOutField();
        }

        return isGameOver;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public Figure getFigure() {
        return figure;
    }

    public void run() {
        initialize();
        startGame();
    }

    protected void initialize() {
        figure = Creator.createFigure(new TwoBallFigure());

        spritesInFigure = figure.getSpritesInFigure();

        for (int i = 0; i < spritesInFigure.length; i++) {
            spritesOnField[spritesInFigure[i].getY() / TILE_HEIGHT][spritesInFigure[i].getX() / TILE_WIDTH] = spritesInFigure[i];
        }
    }

    private void startGame() {
        while (!isGameOver()) {
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (Sprite sprite : spritesInFigure) {
                if (!sprite.isFrozen()) {
                    figure.moveDownFigure();
                } else {
                    sprite.leaveOnTheField();
                }
            }

            int count = 0;
            for (Sprite sprite : spritesInFigure) {
                if (sprite.isFrozen()) {
                    count++;
                }
            }

            if (count == spritesInFigure.length) {
                initialize();
            }
            /*if (figure.getFirstBall().isFrozen() && !figure.getSecondBall().isFrozen()) {
                figure.getFirstBall().leaveOnTheField();
                checkLines();
                if (!figure.isVertical()) {
                    figure.getSecondBall().moveDown();
                }
            } else if (!figure.getFirstBall().isFrozen() && figure.getSecondBall().isFrozen()) {
                figure.getSecondBall().leaveOnTheField();
                checkLines();
                if (!figure.isVertical()) {
                    figure.getFirstBall().moveDown();
                }
            } else if (figure.getFirstBall().isFrozen() && figure.getSecondBall().isFrozen()) {
                figure.getFirstBall().leaveOnTheField();
                figure.getSecondBall().leaveOnTheField();
                checkLines();
                initialize();
            } else {
                figure.getFirstBall().moveDown();
                figure.getSecondBall().moveDown();
            }*/
            gamePanel.repaint();
        }
    }

    private void checkLines() {
        //TODO: Need Wave or A* algorithm
    }
}
