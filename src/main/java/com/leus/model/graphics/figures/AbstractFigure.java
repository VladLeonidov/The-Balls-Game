package com.leus.model.graphics.figures;

import com.leus.model.Game;
import com.leus.model.factories.spriteFactories.SpriteFactory;
import com.leus.model.graphics.sprites.AbstractSprite;
import com.leus.view.displays.Display;

import java.awt.*;
import java.util.Arrays;

public abstract class AbstractFigure {

    protected static final AbstractSprite[][] GAME_FIELD_MATRIX = Game.getGameFieldMatrix();
    protected AbstractSprite[] spritesInFigure;

    public AbstractFigure(AbstractSprite[] spritesInFigure) {
        this.spritesInFigure = spritesInFigure;
    }

    public AbstractSprite[] getSpritesInFigure() {
        return spritesInFigure;
    }

    public abstract void rotate();

    public boolean isFrozen() {
        for (AbstractSprite currentSprite : spritesInFigure) {
            if (currentSprite.getCoordinateY() / Game.TILE_HEIGHT == Display.getHeightWindowInTile() || GAME_FIELD_MATRIX[currentSprite.getCoordinateY() / Game.TILE_HEIGHT + 1][currentSprite.getCoordinateX() / Game.TILE_WIDTH] != null) {
                return true;
            }
        }

        return false;
    }

    public void leaveOnTheField() {
        for (AbstractSprite currentSprite : spritesInFigure) {
            GAME_FIELD_MATRIX[currentSprite.getCoordinateY() / Game.TILE_HEIGHT][currentSprite.getCoordinateX() / Game.TILE_WIDTH] = currentSprite;
        }
    }

    public void moveLeft() {
        if (isCanMoveLeft()) {
            for (AbstractSprite currentSprite : spritesInFigure) {
                currentSprite.moveLeft();
            }
        }
    }

    public void moveRight() {
        if (isCanMoveRight()) {
            for (AbstractSprite currentSprite : spritesInFigure) {
                currentSprite.moveRight();
            }
        }
    }

    public void paint(Graphics g) {
        for (AbstractSprite currentSprite : spritesInFigure) {
            currentSprite.paint(g);
        }
    }

    public void moveDown() {
        for (AbstractSprite abstractSprite : spritesInFigure) {
            abstractSprite.moveDown();
        }
    }

    public void fastMoveDown() {
        while (!isFrozen()) {
            moveDown();
        }
    }

    @Override
    public String toString() {
        return "Figure" + Arrays.toString(spritesInFigure);
    }

    protected abstract void initializeSpritesInFigure(int startPositionX, int startPositionY, SpriteFactory creatorSprites);

    protected boolean isCanMoveRight() {
        for (AbstractSprite currentSprite : spritesInFigure) {
            if (currentSprite.getCoordinateX() / Game.TILE_WIDTH == Display.getWidthWindowInTile() - 1 ||
                    GAME_FIELD_MATRIX[currentSprite.getCoordinateY() / Game.TILE_HEIGHT][currentSprite.getCoordinateX() / Game.TILE_WIDTH + 1] != null) {
                return false;
            }
        }

        return true;
    }

    protected boolean isCanMoveLeft() {
        for (AbstractSprite currentSprite : spritesInFigure) {
            if (currentSprite.getCoordinateX() / Game.TILE_WIDTH == 0 ||
                    GAME_FIELD_MATRIX[currentSprite.getCoordinateY() / Game.TILE_HEIGHT][currentSprite.getCoordinateX() / Game.TILE_WIDTH - 1] != null) {
                return false;
            }
        }

        return true;
    }
}
