package com.leus.business.graphics.sprite.factory;

import com.leus.business.graphics.sprite.AbstractSprite;

public interface SpriteFactory {
    AbstractSprite newSprite(int x, int y);
}
