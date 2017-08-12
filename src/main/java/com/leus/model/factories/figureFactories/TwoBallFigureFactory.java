package com.leus.model.factories.figureFactories;

import com.leus.model.graphics.figures.TwoBallFigure;

public class TwoBallFigureFactory implements FigureFactory {

    public TwoBallFigure createFigure() {
        return new TwoBallFigure();
    }
}
