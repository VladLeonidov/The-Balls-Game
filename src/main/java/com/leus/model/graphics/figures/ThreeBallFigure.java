package com.leus.model.graphics.figures;

import com.leus.model.GameField;
import com.leus.model.factories.spriteFactories.SpriteFactory;
import com.leus.model.graphics.sprites.AbstractSprite;
import com.leus.utils.CreatorOfSprites;
import com.leus.view.PcGameFrameBuilder;

public class ThreeBallFigure extends AbstractFigure {

    public ThreeBallFigure(int startPositionX, int startPositionY, SpriteFactory factory) {
        super(new AbstractSprite[3]);
        initializeSpritesInFigure(startPositionX, startPositionY, factory);
    }

    @Override
    public void rotate() {

    }

    @Override
    public void moveLeft() {
        for (AbstractSprite elem : spritesInFigure) {
            elem.moveLeft();
        }
    }

    @Override
    public void moveRight() {
        for (AbstractSprite elem : spritesInFigure) {
            elem.moveRight();
        }
    }

    @Override
    public void moveDown() {
        for (AbstractSprite abstractSprite : spritesInFigure) {
            abstractSprite.moveDown();
        }
    }

    @Override
    public boolean isFrozen() {
        for (AbstractSprite sprite : spritesInFigure) {
            if (sprite.getY() / GameField.TILE_HEIGHT == PcGameFrameBuilder.getFieldHeightInTile() || gameFieldMatrix[sprite.getY() / GameField.TILE_HEIGHT + 1][sprite.getX() / GameField.TILE_WIDTH] != null) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void leaveOnTheField() {
        for (AbstractSprite sprite : spritesInFigure) {
            gameFieldMatrix[sprite.getY() / GameField.TILE_HEIGHT][sprite.getX() / GameField.TILE_WIDTH] = sprite;
        }
    }

    @Override
    protected void initializeSpritesInFigure(int startPositionX, int startPositionY, SpriteFactory factory) {
        spritesInFigure[0] = CreatorOfSprites.createSprite(startPositionX, startPositionY, factory);
        spritesInFigure[1] = CreatorOfSprites.createSprite(startPositionX, startPositionY + GameField.TILE_HEIGHT, factory);
        spritesInFigure[2] = CreatorOfSprites.createSprite(startPositionX, startPositionY + GameField.TILE_HEIGHT * 2, factory);
    }
}
