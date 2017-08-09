package com.leus.model.factories.figuresFactories;

import com.leus.model.graphics.figures.TwoBallFigure;

public class TwoBallFigureFactory implements FigureFactory {

    public TwoBallFigure createFigure() {
        return new TwoBallFigure();
    }
}
