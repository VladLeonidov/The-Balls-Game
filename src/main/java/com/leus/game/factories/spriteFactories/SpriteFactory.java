package com.leus.game.factories.spriteFactories;

import com.leus.game.graphics.sprites.AbstractSprite;

public interface SpriteFactory {
    AbstractSprite newSprite(int x, int y);
}
