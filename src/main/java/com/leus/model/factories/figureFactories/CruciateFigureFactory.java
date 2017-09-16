package com.leus.model.factories.figureFactories;

import com.leus.model.factories.spriteFactories.SpriteFactory;
import com.leus.model.graphics.figures.AbstractFigure;
import com.leus.model.graphics.figures.CruciateFigure;

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
