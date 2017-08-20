package com.leus.model.service.cleaners;

import com.leus.model.graphics.sprites.AbstractSprite;

@FunctionalInterface
public interface CleaningSpritesStrategy {
    void clearSprites(AbstractSprite[][] gameFieldMatrix);
}
