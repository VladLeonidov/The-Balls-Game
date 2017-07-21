package com.leus.utils;

import com.leus.model.factories.SpriteFactory;
import com.leus.model.graphics.sprites.AbstractSprite;
import com.leus.model.graphics.figures.AbstractFigure;

public class Creator {

    public static AbstractSprite createBall(int x, int y, SpriteFactory factory) {
        return factory.newSprite(x, y);
    }

    public static AbstractFigure createFigure(AbstractFigure abstractFigure) {
        return abstractFigure;
    }
}
