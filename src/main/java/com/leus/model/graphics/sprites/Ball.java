package com.leus.model.graphics.sprites;

import java.awt.*;

public class Ball extends AbstractSprite {

    private ColorBalls color;

    public Ball(int coordinateX, int coordinateY, ColorBalls color) {
        super(coordinateX, coordinateY);
        this.color = color;
    }

    public ColorBalls getColor() {
        return color;
    }

    public boolean isOutField() {
        /*if (gameFieldMatrix[getCoordinateY() / GameField.TILE_HEIGHT][getCoordinateX() / GameField.TILE_WIDTH] != null &&
            gameFieldMatrix[getCoordinateY() / GameField.TILE_HEIGHT + 1][getCoordinateX() / GameField.TILE_WIDTH] != null) {
            return true;
        }*/

        return false;
    }

    public void paint(Graphics g) {
        g.drawImage(color.getImg(), getCoordinateX(), getCoordinateY(), null);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + getCoordinateX();
        result = 31 * result + getCoordinateY();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Ball)) {
            return false;
        }

        Ball that = (Ball) obj;

        return this.getCoordinateX() == that.getCoordinateX() && this.getCoordinateY() == that.getCoordinateY();
    }

    @Override
    public String toString() {
        return "Ball{x=" + getCoordinateX() + ", y=" + getCoordinateY() + ", color=" + color + '}';
    }
}
