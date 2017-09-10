package com.leus.model.graphics.figures;

import com.leus.model.Game;
import com.leus.model.factories.spriteFactories.SpriteFactory;
import com.leus.model.graphics.sprites.AbstractSprite;
import com.leus.utils.CreatorOfSprites;

public class VerticalThreeBallFigure extends AbstractFigure {

    public VerticalThreeBallFigure(int startPositionX, int startPositionY, SpriteFactory factory) {
        super(new AbstractSprite[3]);
        initializeSpritesInFigure(startPositionX, startPositionY, factory);
    }

    @Override
    public void rotate() {
        int coordYtmp1 = spritesInFigure[0].getCoordinateY();
        int coordYtmp2 = spritesInFigure[1].getCoordinateY();
        spritesInFigure[0].setCoordinateY(spritesInFigure[2].getCoordinateY());
        spritesInFigure[1].setCoordinateY(coordYtmp1);
        spritesInFigure[2].setCoordinateY(coordYtmp2);
    }

    @Override
    protected void initializeSpritesInFigure(int startPositionX, int startPositionY, SpriteFactory factory) {
        spritesInFigure[0] = CreatorOfSprites.createSprite(startPositionX, startPositionY, factory);
        spritesInFigure[1] = CreatorOfSprites.createSprite(startPositionX, startPositionY + Game.TILE_HEIGHT, factory);
        spritesInFigure[2] = CreatorOfSprites.createSprite(startPositionX, startPositionY + Game.TILE_HEIGHT * 2, factory);
    }
}
