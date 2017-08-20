package com.leus.model.graphics.figures;

import com.leus.model.GameField;
import com.leus.model.factories.spriteFactories.SpriteFactory;
import com.leus.model.graphics.sprites.AbstractSprite;
import com.leus.utils.CreatorOfSprites;

public class ThreeBallFigure extends AbstractFigure {

    public ThreeBallFigure(int startPositionX, int startPositionY, SpriteFactory factory) {
        super(new AbstractSprite[3]);
        initializeSpritesInFigure(startPositionX, startPositionY, factory);
    }

    @Override
    public void rotate() {

    }

    @Override
    protected void initializeSpritesInFigure(int startPositionX, int startPositionY, SpriteFactory factory) {
        spritesInFigure[0] = CreatorOfSprites.createSprite(startPositionX, startPositionY, factory);
        spritesInFigure[1] = CreatorOfSprites.createSprite(startPositionX, startPositionY + GameField.TILE_HEIGHT, factory);
        spritesInFigure[2] = CreatorOfSprites.createSprite(startPositionX, startPositionY + GameField.TILE_HEIGHT * 2, factory);
    }

    @Override
    protected boolean isCanMoveRight() {
        return true;
    }

    @Override
    protected boolean isCanMoveLeft() {
        return true;
    }
}
