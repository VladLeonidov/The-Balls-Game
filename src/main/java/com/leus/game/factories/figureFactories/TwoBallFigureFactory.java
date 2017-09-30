package com.leus.game.factories.figureFactories;

import com.leus.game.factories.spriteFactories.SpriteFactory;
import com.leus.game.graphics.figures.TwoBallFigure;

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
