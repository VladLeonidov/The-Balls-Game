package com.leus.model.service;

import com.leus.model.factories.figureFactories.FigureFactory;
import com.leus.model.graphics.figures.AbstractFigure;

public class FigureManager {

    private static FigureFactory registeredFigureFactory;

    public void registrationFigureFactory(FigureFactory figureFactory) {
        if (figureFactory == null) {
            throw new NullPointerException("The figureFactory cannot by null");
        }

        registeredFigureFactory = figureFactory;
    }

    public AbstractFigure createFigure() {
        if (registeredFigureFactory == null) {
            throw new NullPointerException("The figureFactory by null");
        }

        return registeredFigureFactory.createFigure();
    }
}
