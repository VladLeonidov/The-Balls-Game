package com.leus.model.factories;

import com.leus.model.graphics.sprites.AbstractSprite;

public interface SpriteFactory {
    AbstractSprite newSprite(int x, int y);
}
