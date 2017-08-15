package com.leus.model.service;

import com.leus.model.factories.figureFactories.FigureFactory;
import com.leus.model.graphics.figures.AbstractFigure;

import java.util.Random;

public class FigureManager {

    private static FigureFactory[] registeredFigureFactories;

    public void registrationFigureFactories(FigureFactory... figureFactories) {
        registeredFigureFactories = figureFactories;
    }

    public AbstractFigure createFigure() {
        if (registeredFigureFactories == null) {
            throw new NullPointerException("No Registered FigureFactories");
        }

        return registeredFigureFactories[new Random().nextInt(registeredFigureFactories.length)].createFigure();
    }
}
