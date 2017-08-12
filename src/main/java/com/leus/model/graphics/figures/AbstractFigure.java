package com.leus.model.graphics.figures;

import com.leus.model.GameField;
import com.leus.model.factories.spriteFactories.RandBallFactory;
import com.leus.model.factories.spriteFactories.SpriteFactory;
import com.leus.model.graphics.sprites.AbstractSprite;
import com.leus.view.GameFrame;

import java.awt.*;

public abstract class AbstractFigure {

    private final int startPositionX;
    private final int startPositionY;
    private final SpriteFactory spriteFactory;

    protected final AbstractSprite[] spritesInFigure;

    protected static AbstractSprite[][] gameFieldMatrix = GameField.getGameFieldMatrix();

    public AbstractFigure() {
        this((GameFrame.WIDTH_GAME_FRAME + GameFrame.ADD_TO_FRAME_SIZE_IN_WIDTH) / 2 - GameField.TILE_WIDTH - 2, 0, new AbstractSprite[2], new RandBallFactory());
    }

    public AbstractFigure(int startPositionX, int startPositionY, AbstractSprite[] spritesInFigure, SpriteFactory spriteFactory) {
        this.startPositionX = startPositionX;
        this.startPositionY = startPositionY;
        this.spritesInFigure = spritesInFigure;
        this.spriteFactory = spriteFactory;
    }

    public int getStartPositionX() {
        return startPositionX;
    }

    public int getStartPositionY() {
        return startPositionY;
    }

    public SpriteFactory getSpriteFactory() {
        return spriteFactory;
    }

    public AbstractSprite[] getSpritesInFigure() {
        return spritesInFigure;
    }

    public abstract void rotate();

    public abstract void moveLeft();

    public abstract void moveRight();

    public abstract void moveDown();

    public abstract boolean isFrozen();

    public abstract void leaveOnTheField();

    public void paint(Graphics g) {
        for (AbstractSprite sprite : spritesInFigure) {
            sprite.paint(g);
        }
    }

    public void fastMoveDown() {
        while (!isFrozen()) {
            moveDown();
        }
    }
}
