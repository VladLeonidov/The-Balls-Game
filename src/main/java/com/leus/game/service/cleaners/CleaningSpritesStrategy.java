package com.leus.game.service.cleaners;

import com.leus.game.graphics.sprites.AbstractSprite;

@FunctionalInterface
public interface CleaningSpritesStrategy {
    void clearSprites(AbstractSprite[][] gameFieldMatrix);
}
