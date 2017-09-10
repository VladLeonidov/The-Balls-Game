package com.leus.model.graphics.figures;

import com.leus.model.Game;
import com.leus.model.factories.spriteFactories.SpriteFactory;
import com.leus.utils.CreatorOfSprites;

public class HorizontalThreeBallFigure extends VerticalThreeBallFigure {

    public HorizontalThreeBallFigure(int startPositionX, int startPositionY, SpriteFactory factory) {
        super(startPositionX, startPositionY, factory);
    }

    @Override
    public void rotate() {
        int coordXtmp1 = spritesInFigure[0].getCoordinateX();
        int coordXtmp2 = spritesInFigure[1].getCoordinateX();
        spritesInFigure[0].setCoordinateX(spritesInFigure[2].getCoordinateX());
        spritesInFigure[1].setCoordinateX(coordXtmp1);
        spritesInFigure[2].setCoordinateX(coordXtmp2);
    }

    @Override
    protected void initializeSpritesInFigure(int startPositionX, int startPositionY, SpriteFactory factory) {
        spritesInFigure[0] = CreatorOfSprites.createSprite(startPositionX, startPositionY, factory);
        spritesInFigure[1] = CreatorOfSprites.createSprite(startPositionX + Game.TILE_WIDTH, startPositionY, factory);
        spritesInFigure[2] = CreatorOfSprites.createSprite(startPositionX + Game.TILE_WIDTH * 2, startPositionY, factory);
    }
}
