package com.leus.model.graphics.figures;


import com.leus.model.factories.RandBallFactory;
import com.leus.model.factories.SpriteFactory;
import com.leus.model.GameField;
import com.leus.model.graphics.sprites.AbstractSprite;
import com.leus.view.displays.GameFrame;

public abstract class AbstractFigure {

    private int startPositionX;
    private int startPositionY;
    private SpriteFactory spriteFactory;

    protected AbstractSprite[] spritesInFigure;
    protected AbstractSprite[][] spritesOnField = GameFrame.getGameFrame().getGameField().getSpritesOnField();

    public AbstractFigure() {
        this((GameFrame.WIDTH_GAME_FRAME + GameFrame.FIELD_ADD_TO_WIDTH) / 2 - GameField.TILE_WIDTH - 2, 0, new AbstractSprite[2], new RandBallFactory());
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

    public void setStartPositionX(int startPositionX) {
        this.startPositionX = startPositionX;
    }

    public int getStartPositionY() {
        return startPositionY;
    }

    public void setStartPositionY(int startPositionY) {
        this.startPositionY = startPositionY;
    }

    public SpriteFactory getSpriteFactory() {
        return spriteFactory;
    }

    public void setSpriteFactory(SpriteFactory spriteFactory) {
        this.spriteFactory = spriteFactory;
    }

    public AbstractSprite[] getSpritesInFigure() {
        return spritesInFigure;
    }

    public void setSpritesInFigure(AbstractSprite[] spritesInFigure) {
        this.spritesInFigure = spritesInFigure;
    }

    public abstract void rotate();

    public abstract void moveLeft();

    public abstract void moveRight();

    public abstract void moveDownFigure();

    public void fastMoveDown() {
        for (AbstractSprite abstractSprite : spritesInFigure) {
            while (!abstractSprite.isFrozen()) {
                abstractSprite.moveDown();
            }
        }
    }
}
