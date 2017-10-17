package com.leus.business.service;

import com.leus.business.graphics.sprite.AbstractSprite;
import com.leus.business.service.cleaner.CleanerBalls;

public class FieldManager {

    private static final CleanerBalls DEFAULT_CLEANER = new CleanerBalls();

    private CleanerBalls cleaner;

    public FieldManager() {
        this(DEFAULT_CLEANER);
    }

    public FieldManager(CleanerBalls cleaner) {
        this.cleaner = cleaner;
    }

    public void clearChainsSpritesFromField(AbstractSprite[][] field) {
        cleaner.clearChainsSprites(field);
    }

    public void moveDownSpritesInSpace(AbstractSprite[][] field) {
        for (int i = 0; i < field.length - 1; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] != null && field[i + 1][j] == null) {
                    field[i][j].moveDown();
                    field[i + 1][j] = field[i][j];
                    field[i][j] = null;
                }
            }
        }
    }

    public boolean isSpritesInSpace(AbstractSprite[][] field) {
        for (int i = 0; i < field.length - 1; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] != null && field[i + 1][j] == null) {
                    return true;
                }
            }
        }

        return false;
    }
}
