package com.leus.model.factories.figureFactories;

import com.leus.model.factories.spriteFactories.SpriteFactory;
import com.leus.model.graphics.figures.HorizontalThreeBallFigure;

public class HorizontalThreeBallFigureFactory implements FigureFactory {
    private int startPositionX;
    private int startPositionY;
    private SpriteFactory creatorSprites;

    public HorizontalThreeBallFigureFactory(int startPositionX, int startPositionY, SpriteFactory creatorSprites) {
        this.startPositionX = startPositionX;
        this.startPositionY = startPositionY;
        this.creatorSprites = creatorSprites;
    }

    @Override
    public HorizontalThreeBallFigure createFigure() {
        return new HorizontalThreeBallFigure(startPositionX, startPositionY, creatorSprites);
    }
}
