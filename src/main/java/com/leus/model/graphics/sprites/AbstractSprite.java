package com.leus.model.graphics.sprites;

import com.leus.model.Game;
import java.awt.Graphics;

public abstract class AbstractSprite {

    public static final int SPEED = Game.TILE_HEIGHT;

    protected static AbstractSprite[][] gameFieldMatrix = Game.getGameFieldMatrix();

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

    public abstract void clear();

    public void moveLeft() {
        coordinateX -= SPEED;
    }

    public void moveRight() {
        coordinateX += SPEED;
    }

    public void moveDown() {
        coordinateY += SPEED;
    }

}
