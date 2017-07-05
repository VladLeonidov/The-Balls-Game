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
    public static final long DELAY = 500;
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
        initializeGameLoop();
        gameLoop();
    }

    protected void initializeGameLoop() {
        figure = Creator.createFigure(new TwoBallFigure());

        spritesInFigure = figure.getSpritesInFigure();

        for (int i = 0; i < spritesInFigure.length; i++) {
            spritesOnField[spritesInFigure[i].getY() / TILE_HEIGHT][spritesInFigure[i].getX() / TILE_WIDTH] = spritesInFigure[i];
        }
    }

    protected void gameLoop() {
        while (!isGameOver()) {
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (isAllFrosenSprite(spritesInFigure)) {
                leaveFrosenSprite(spritesInFigure);
                initializeGameLoop();
            } else if (isFrosenSprite(spritesInFigure)) {
                for (Sprite sprite : spritesInFigure) {
                    if (sprite.isFrozen()) {
                        sprite.leaveOnTheField();
                    } else {
                        sprite.moveDown();
                    }
                }
            } else {
                figure.moveDownFigure();
            }

            gamePanel.repaint();
        }
    }

    private void checkLines() {
        //TODO: Need Wave or A* algorithm
    }

    private boolean isFrosenSprite(Sprite[] spritesInFigure) {
        for (Sprite sprite : spritesInFigure) {
            if (sprite.isFrozen()) {
                return true;
            }
        }

        return false;
    }

    private boolean isAllFrosenSprite(Sprite[] spritesInFigure) {
        int count = 0;
        for (Sprite sprite : spritesInFigure) {
            if (sprite.isFrozen()) {
                count++;
            }
        }

        if (count == spritesInFigure.length) {
            return true;
        }

        return false;
    }

    private void leaveFrosenSprite(Sprite[] spritesInFigure) {
        for (Sprite sprite : spritesInFigure) {
            if (sprite.isFrozen()) {
                sprite.leaveOnTheField();
            }
        }
    }
}
