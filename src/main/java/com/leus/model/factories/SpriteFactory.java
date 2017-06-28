package com.leus.model.factories;

import com.leus.model.graphics.sprites.Sprite;

public interface SpriteFactory {
    Sprite newSprite(int x, int y);
}
