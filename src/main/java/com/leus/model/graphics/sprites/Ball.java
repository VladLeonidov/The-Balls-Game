package com.leus.model.graphics.sprites;


import com.leus.model.fields.GameField;
import com.leus.view.displays.GameFrame;

import java.awt.*;

public class Ball extends AbstractSprite {

    private Image ballImg;

    public Ball(int x, int y, Image ballImg) {
        super(x, y);
        this.ballImg = ballImg;
    }

    public Image getBallImg() {
        return ballImg;
    }

    public boolean isFrozen() {
        if ((ballsOnField[getY() / GameField.TILE_HEIGHT + 1][getX() / GameField.TILE_WIDTH] != null) ||
            ((getY() / GameField.TILE_HEIGHT) == GameFrame.FIELD_HEIGHT_IN_TILE)) {
            return true;
        }

        return false;
    }

    public boolean isOutField() {
        /*if (ballsOnField[getY() / GameField.TILE_HEIGHT][getX() / GameField.TILE_WIDTH] != null &&
            ballsOnField[getY() / GameField.TILE_HEIGHT + 1][getX() / GameField.TILE_WIDTH] != null) {
            return true;
        }*/

        return false;
    }

    public void paint(Graphics g) {
        g.drawImage(ballImg, getX(), getY(), null);
    }
}
