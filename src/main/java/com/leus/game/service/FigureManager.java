package com.leus.game.service;

import com.leus.game.factories.figureFactories.FigureFactory;
import com.leus.game.graphics.figures.AbstractFigure;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FigureManager {

    private List<FigureFactory> registeredFigureFactories = new ArrayList<>();

    public void addFigureFactory(FigureFactory figureFactory) {
        registeredFigureFactories.add(figureFactory);
    }

    public void removeFigureFactory(FigureFactory figureFactory) {
        registeredFigureFactories.remove(figureFactory);
    }

    public AbstractFigure createRandomFigure() {
        if (registeredFigureFactories.isEmpty()) {
            throw new IllegalStateException("No Registered FigureFactories");
        }

        return registeredFigureFactories.get(new Random().nextInt(registeredFigureFactories.size())).createFigure();
    }
}
