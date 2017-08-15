package com.leus.model.graphics.figures;

import com.leus.model.GameField;
import com.leus.model.factories.spriteFactories.SpriteFactory;
import com.leus.model.graphics.sprites.AbstractSprite;

import java.awt.*;

public abstract class AbstractFigure {

    AbstractSprite[] spritesInFigure;

    public AbstractFigure(AbstractSprite[] spritesInFigure) {
        this.spritesInFigure = spritesInFigure;
    }

    protected static AbstractSprite[][] gameFieldMatrix = GameField.getGameFieldMatrix();

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

    protected abstract void initializeSpritesInFigure(int startPositionX, int startPositionY, SpriteFactory factory);
}
