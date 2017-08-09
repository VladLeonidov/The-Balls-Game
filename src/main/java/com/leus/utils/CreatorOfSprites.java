package com.leus.utils;

import com.leus.model.factories.spriteFactories.SpriteFactory;
import com.leus.model.graphics.sprites.AbstractSprite;

public class CreatorOfSprites {
    /**
     *
     * @param x - positions of coordinate x
     * @param y - positions of coordinate y
     * @param factory - factory implements SpriteFactory and create sprite to coordinates x, y
     * @return - new Sprite
     * @throws NullPointerException - if factory is null
     */
    public static AbstractSprite createSprite(int x, int y, SpriteFactory factory) {
        return factory.newSprite(x, y);
    }
}
