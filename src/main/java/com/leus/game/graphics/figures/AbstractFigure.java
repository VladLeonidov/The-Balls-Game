package com.leus.game.graphics.figures;

import com.leus.game.Game;
import com.leus.game.factories.spriteFactories.SpriteFactory;
import com.leus.game.graphics.sprites.AbstractSprite;
import com.leus.display.Display;

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
            if (currentSprite.getCoordinateY() / Game.TILE_HEIGHT == Display.HEIGHT_WINDOW_IN_TILES || GAME_FIELD_MATRIX[currentSprite.getCoordinateY() / Game.TILE_HEIGHT + 1][currentSprite.getCoordinateX() / Game.TILE_WIDTH] != null) {
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
        if (!isLeftWall()) {
            for (AbstractSprite currentSprite : spritesInFigure) {
                currentSprite.moveLeft();
            }
        }
    }

    public void moveRight() {
        if (!isRightWall()) {
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

    protected boolean isRightWall() {
        for (AbstractSprite currentSprite : spritesInFigure) {
            if (currentSprite.getCoordinateX() / Game.TILE_WIDTH == Display.WIDTH_WINDOW_IN_TILES - 1 ||
                    GAME_FIELD_MATRIX[currentSprite.getCoordinateY() / Game.TILE_HEIGHT][currentSprite.getCoordinateX() / Game.TILE_WIDTH + 1] != null) {
                return true;
            }
        }

        return false;
    }

    protected boolean isLeftWall() {
        for (AbstractSprite currentSprite : spritesInFigure) {
            if (currentSprite.getCoordinateX() / Game.TILE_WIDTH == 0 ||
                    GAME_FIELD_MATRIX[currentSprite.getCoordinateY() / Game.TILE_HEIGHT][currentSprite.getCoordinateX() / Game.TILE_WIDTH - 1] != null) {
                return true;
            }
        }

        return false;
    }
}
