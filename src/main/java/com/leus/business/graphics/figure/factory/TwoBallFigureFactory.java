package com.leus.business.graphics.figure.factory;

import com.leus.business.graphics.sprite.factory.SpriteFactory;
import com.leus.business.graphics.figure.TwoBallFigure;

public class TwoBallFigureFactory implements FigureFactory {

    private int startPositionX;
    private int startPositionY;
    private SpriteFactory creatorSprites;

    public TwoBallFigureFactory(int startPositionX, int startPositionY, SpriteFactory creatorSprites) {
        this.startPositionX = startPositionX;
        this.startPositionY = startPositionY;
        this.creatorSprites = creatorSprites;
    }

    @Override
    public TwoBallFigure createFigure() {
        return new TwoBallFigure(startPositionX, startPositionY, creatorSprites);
    }
}
