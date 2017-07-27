package com.leus.model.service;

import com.leus.model.fields.GameField;
import com.leus.model.graphics.sprites.AbstractSprite;

public class FieldService {

    private AbstractSprite[][] spritesOnField = GameField.getGameFieldInstance().getSpritesOnField();

    public void clearLineFromField(ClearerLines clearer) {
        clearer.clearLines();
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
