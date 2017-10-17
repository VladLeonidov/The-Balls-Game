package com.leus.business.graphics.figure.factory;

import com.leus.business.graphics.sprite.factory.SpriteFactory;
import com.leus.business.graphics.figure.AbstractFigure;
import com.leus.business.graphics.figure.CruciateFigure;

public class CruciateFigureFactory implements FigureFactory {
    private int startPositionX;
    private int startPositionY;
    private SpriteFactory creatorSprites;

    public CruciateFigureFactory(int startPositionX, int startPositionY, SpriteFactory creatorSprites) {
        this.startPositionX = startPositionX;
        this.startPositionY = startPositionY;
        this.creatorSprites = creatorSprites;
    }

    @Override
    public AbstractFigure createFigure() {
        return new CruciateFigure(startPositionX, startPositionY, creatorSprites);
    }
}
