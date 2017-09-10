package com.leus.model.service;

import com.leus.model.graphics.sprites.AbstractSprite;
import com.leus.model.service.cleaners.CleanerBalls;
import com.leus.model.service.cleaners.CleaningSpritesStrategy;

public class FieldManager {

    private static final CleaningSpritesStrategy DEFAULT_CLEANER = new CleanerBalls();

    private CleaningSpritesStrategy cleaner;

    public FieldManager() {
        this(DEFAULT_CLEANER);
    }

    public FieldManager(CleaningSpritesStrategy cleaner) {
        this.cleaner = cleaner;
    }

    public void clearSpriteFromField(AbstractSprite[][] gameFieldMatrix) {
        cleaner.clearSprites(gameFieldMatrix);
    }

    public void moveDownSpritesInAir(AbstractSprite[][] gameFieldMatrix) {
        for (int i = 0; i < gameFieldMatrix.length - 1; i++) {
            for (int j = 0; j < gameFieldMatrix[i].length; j++) {
                if (gameFieldMatrix[i][j] != null && gameFieldMatrix[i + 1][j] == null) {
                    gameFieldMatrix[i][j].moveDown();
                    gameFieldMatrix[i + 1][j] = gameFieldMatrix[i][j];
                    gameFieldMatrix[i][j] = null;
                }
            }
        }
    }

    public boolean isSpritesInAir(AbstractSprite[][] gameFieldMatrix) {
        for (int i = 0; i < gameFieldMatrix.length - 1; i++) {
            for (int j = 0; j < gameFieldMatrix[i].length; j++) {
                if (gameFieldMatrix[i][j] != null && gameFieldMatrix[i + 1][j] == null) {
                    return true;
                }
            }
        }

        return false;
    }
}
