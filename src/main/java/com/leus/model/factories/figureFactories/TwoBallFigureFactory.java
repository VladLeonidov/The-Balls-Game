package com.leus.model.factories.figureFactories;

import com.leus.model.factories.spriteFactories.SpriteFactory;
import com.leus.model.graphics.figures.TwoBallFigure;

public class TwoBallFigureFactory implements FigureFactory {

    private int startPositionX;
    private int startPositionY;
    private SpriteFactory factory;

    public TwoBallFigureFactory(int startPositionX, int startPositionY, SpriteFactory factory) {
        this.startPositionX = startPositionX;
        this.startPositionY = startPositionY;
        this.factory = factory;
    }

    public TwoBallFigure createFigure() {
        return new TwoBallFigure(startPositionX, startPositionY, factory);
    }
}
