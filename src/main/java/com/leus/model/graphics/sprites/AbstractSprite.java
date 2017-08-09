package com.leus.model.graphics.sprites;

import com.leus.model.GameField;

import java.awt.*;

public abstract class AbstractSprite {

    protected static AbstractSprite[][] gameFieldMatrix = GameField.getGameFieldMatrix();

    private int x;
    private int y;

    public AbstractSprite(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public abstract boolean isOutField();

    public abstract void paint(Graphics g);

    public void moveLeft() {
        x -= GameField.TILE_WIDTH;
    }

    public void moveRight() {
        x += GameField.TILE_WIDTH;
    }

    public void moveDown() {
        y += GameField.TILE_HEIGHT;
    }

    public void clear() {
        gameFieldMatrix[y / GameField.TILE_HEIGHT][x / GameField.TILE_WIDTH] = null;
    }

}
