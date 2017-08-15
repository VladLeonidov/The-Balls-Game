package com.leus.model.factories.figureFactories;

import com.leus.model.factories.spriteFactories.SpriteFactory;
import com.leus.model.graphics.figures.ThreeBallFigure;

public class ThreeBallFigureFactory implements FigureFactory {

    private int startPositionX;
    private int startPositionY;
    private SpriteFactory factory;

    public ThreeBallFigureFactory(int startPositionX, int startPositionY, SpriteFactory factory) {
        this.startPositionX = startPositionX;
        this.startPositionY = startPositionY;
        this.factory = factory;
    }

    @Override
    public ThreeBallFigure createFigure() {
        return new ThreeBallFigure(startPositionX, startPositionY, factory);
    }
}
