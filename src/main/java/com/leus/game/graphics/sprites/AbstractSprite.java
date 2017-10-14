package com.leus.game.graphics.sprites;

import com.leus.game.Game;
import com.leus.game.factories.spriteFactories.SpriteFactory;

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

    public static AbstractSprite createSprite(int x, int y, SpriteFactory factory) {
        if (factory == null) {
            throw new NullPointerException("Factory can't be null");
        }
        return factory.newSprite(x, y);
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
