package com.leus.model.graphics.sprites;

import com.leus.model.GameField;
import java.awt.Graphics;

public abstract class AbstractSprite {

    public static final int SPEED = GameField.TILE_HEIGHT;

    protected static AbstractSprite[][] gameFieldMatrix = GameField.getGameFieldMatrix();

    private int coordinateX;
    private int coordinateY;

    public AbstractSprite(int coordinateX, int coordinateY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }

    public void setCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }

    public abstract boolean isOutField();

    public abstract void paint(Graphics g);

    public void moveLeft() {
        coordinateX -= SPEED;
    }

    public void moveRight() {
        coordinateX += SPEED;
    }

    public void moveDown() {
        coordinateY += SPEED;
    }

    public void clear() {
        gameFieldMatrix[coordinateY / GameField.TILE_HEIGHT][coordinateX / GameField.TILE_WIDTH] = null;
    }

}
