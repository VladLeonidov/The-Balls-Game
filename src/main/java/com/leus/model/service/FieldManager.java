package com.leus.model.service;

import com.leus.model.graphics.sprites.AbstractSprite;
import com.leus.view.displays.GameFrame;

public class FieldManager {

    private AbstractSprite[][] spritesOnField = GameFrame.getGameFrame().getGameField().getSpritesOnField();

    public void clearLineFromField(CleanerLines cleaner) {
        cleaner.clearLines();
    }

    public void moveDownSpriteForAir() {
        for (int i = 0; i < spritesOnField.length - 1; i++) {
            for (int j = 0; j < spritesOnField[i].length; j++) {
                if (spritesOnField[i + 1][j] != null) {
                    spritesOnField[i][j].moveDown();
                }
            }
        }
    }
}
