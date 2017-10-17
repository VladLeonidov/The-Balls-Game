package com.leus.business.graphics.figure.factory;

import com.leus.business.graphics.sprite.factory.SpriteFactory;
import com.leus.business.graphics.figure.ThreeBallFigure;

public class ThreeBallFigureFactory implements FigureFactory {
    private int startPositionX;
    private int startPositionY;
    private SpriteFactory creatorSprites;

    public ThreeBallFigureFactory(int startPositionX, int startPositionY, SpriteFactory creatorSprites) {
        this.startPositionX = startPositionX;
        this.startPositionY = startPositionY;
        this.creatorSprites = creatorSprites;
    }

    @Override
    public ThreeBallFigure createFigure() {
        return new ThreeBallFigure(startPositionX, startPositionY, creatorSprites);
    }
}
