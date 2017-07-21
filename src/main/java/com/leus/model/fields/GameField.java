package com.leus.model.fields;


import com.leus.model.graphics.figures.AbstractFigure;
import com.leus.model.graphics.figures.TwoBallAbstractFigure;
import com.leus.model.graphics.sprites.AbstractSprite;
import com.leus.utils.Creator;
import com.leus.utils.FieldService;
import com.leus.utils.ScoreManager;
import com.leus.view.displays.GameFrame;
import com.leus.view.panels.GamePanel;

public class GameField implements Runnable {

    public static final int TILE_WIDTH = 32;
    public static final int TILE_HEIGHT = 32;
    public static final long DELAY = 500;
    public static final int MINIMAL_COUNT_BALLS_FOR_CLEAR = 4;

    private static AbstractSprite[][] spritesOnField = new AbstractSprite[GameFrame.FIELD_HEIGHT_IN_TILE + 2][GameFrame.FIELD_WIDTH_IN_TILE];

    private boolean isGameOver = false;
    private AbstractFigure abstractFigure;
    private GamePanel gamePanel = new GamePanel();
    private FieldService fieldService = new FieldService();
    private ScoreManager scoreManager = new ScoreManager();
    private AbstractSprite[] spritesInFigure;

    public static AbstractSprite[][] getSpritesOnField() {
        return spritesOnField;
    }

    public static void setSpritesOnField(AbstractSprite[][] spritesOnField) {
        GameField.spritesOnField = spritesOnField;
    }

    public boolean isGameOver() {
        for (AbstractSprite abstractSprite : spritesInFigure) {
            isGameOver = abstractSprite.isOutField();
        }

        return isGameOver;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public AbstractFigure getAbstractFigure() {
        return abstractFigure;
    }

    public void run() {
        initializeGameLoop();
        gameLoop();
    }

    protected void initializeGameLoop() {
        abstractFigure = Creator.createFigure(new TwoBallAbstractFigure());

        spritesInFigure = abstractFigure.getSpritesInFigure();

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

            if (isAllFrozenSprite(spritesInFigure)) {
                leaveFrozenSprite(spritesInFigure);
                initializeGameLoop();
            } else if (isFrozenSprite(spritesInFigure)) {
                for (AbstractSprite abstractSprite : spritesInFigure) {
                    if (abstractSprite.isFrozen()) {
                        abstractSprite.leaveOnTheField();
                    } else {
                        abstractSprite.moveDown();
                    }
                }
            } else {
                abstractFigure.moveDownFigure();
            }

            gamePanel.repaint();
        }
    }

    private boolean isFrozenSprite(AbstractSprite[] spritesInFigure) {
        for (AbstractSprite abstractSprite : spritesInFigure) {
            if (abstractSprite.isFrozen()) {
                return true;
            }
        }

        return false;
    }

    private boolean isAllFrozenSprite(AbstractSprite[] spritesInFigure) {
        int count = 0;
        for (AbstractSprite abstractSprite : spritesInFigure) {
            if (abstractSprite.isFrozen()) {
                count++;
            }
        }

        if (count == spritesInFigure.length) {
            return true;
        }

        return false;
    }

    private void leaveFrozenSprite(AbstractSprite[] spritesInFigure) {
        for (AbstractSprite abstractSprite : spritesInFigure) {
            if (abstractSprite.isFrozen()) {
                abstractSprite.leaveOnTheField();
            }
        }
    }
}
