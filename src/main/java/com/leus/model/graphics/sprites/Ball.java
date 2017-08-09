package com.leus.model.graphics.sprites;


import java.awt.*;

public class Ball extends AbstractSprite {

    private ColorBalls color;

    public Ball(int x, int y, ColorBalls color) {
        super(x, y);
        this.color = color;
    }

    public ColorBalls getColor() {
        return color;
    }

    public boolean isOutField() {
        /*if (gameFieldMatrix[getY() / GameField.TILE_HEIGHT][getX() / GameField.TILE_WIDTH] != null &&
            gameFieldMatrix[getY() / GameField.TILE_HEIGHT + 1][getX() / GameField.TILE_WIDTH] != null) {
            return true;
        }*/

        return false;
    }

    public void paint(Graphics g) {
        g.drawImage(color.getImg(), getX(), getY(), null);
    }

    @Override
    public int hashCode() {
        return 31 * getX() + 37 * getY();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        Ball that = (Ball) obj;

        return this.getX() == that.getX() && this.getY() == that.getY();
    }
}
