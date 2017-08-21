package com.leus.model.factories.figureFactories;

import com.leus.model.factories.spriteFactories.SpriteFactory;
import com.leus.model.graphics.figures.VerticalThreeBallFigure;

public class VerticalThreeBallFigureFactory implements FigureFactory {

    private int startPositionX;
    private int startPositionY;
    private SpriteFactory creatorSprites;

    public VerticalThreeBallFigureFactory(int startPositionX, int startPositionY, SpriteFactory creatorSprites) {
        this.startPositionX = startPositionX;
        this.startPositionY = startPositionY;
        this.creatorSprites = creatorSprites;
    }

    @Override
    public VerticalThreeBallFigure createFigure() {
        return new VerticalThreeBallFigure(startPositionX, startPositionY, creatorSprites);
    }
}
