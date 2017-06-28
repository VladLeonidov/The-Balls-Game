package com.leus.utils;

import com.leus.model.factories.SpriteFactory;
import com.leus.model.graphics.sprites.Sprite;
import com.leus.model.graphics.figures.Figure;

public class Creator {

    public static Sprite createBall(int x, int y, SpriteFactory factory) {
        return factory.newSprite(x, y);
    }

    public static Figure createFigure(Figure figure) {
        return figure;
    }
}
