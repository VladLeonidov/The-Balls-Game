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

    public void clearChainsSpritesFromField(AbstractSprite[][] field) {
        cleaner.clearChainsSprites(field);
    }

    public void moveDownSpritesInAir(AbstractSprite[][] field) {
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

    public boolean isSpritesInAir(AbstractSprite[][] field) {
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
