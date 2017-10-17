package com.leus.business.service.cleaner;

import com.leus.business.graphics.sprite.AbstractSprite;

@FunctionalInterface
public interface CleaningSpritesStrategy {
    void clearChainsSprites(AbstractSprite[][] gameFieldMatrix);
}
