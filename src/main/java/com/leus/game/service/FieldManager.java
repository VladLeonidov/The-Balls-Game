package com.leus.game.service;

import com.leus.game.graphics.sprites.AbstractSprite;
import com.leus.game.service.cleaners.CleanerBalls;

public class FieldManager {

    private static final CleanerBalls DEFAULT_CLEANER = new CleanerBalls();

    private CleanerBalls cleaner;

    public FieldManager() {
        this(DEFAULT_CLEANER);
    }

    public FieldManager(CleanerBalls cleaner) {
        this.cleaner = cleaner;
    }

    public void clearSpriteFromField(AbstractSprite[][] gameFieldMatrix) {
        cleaner.clearSprites(gameFieldMatrix);
    }

    public boolean isCleanedChainBalls() {
        return cleaner.isCleanedChainBalls();
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
