package com.leus.game.graphics;

import com.leus.displays.GameFrame;
import com.leus.enums.ColorBalls;
import com.leus.game.factories.BallsFactory;
import com.leus.game.fields.GameField;

import java.awt.*;

public class Ball {

    private static int id;
    private final int countId = id++;

    private int x;
    private int y;
    private boolean isFrosen;

    private ColorBalls colorBall;

    private static Ball[][] ballsOnField = GameField.getBallsOnField();
    private static int[][] glass = GameField.getGlass();

    public Ball(int x, int y, ColorBalls colorBall) {
        this.x = x;
        this.y = y;
        this.colorBall = colorBall;
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

    public boolean isFrozen() {
        if ((glass[y / GameField.TILE_HEIGHT + 1][x / GameField.TILE_WIDTH] > 0) ||
                ((y / GameField.TILE_HEIGHT) == GameFrame.FIELD_HEIGHT_IN_TILE)) {
            return true;
        }

        return false;
    }

    public boolean isOutField() {
        if (glass[y / GameField.TILE_HEIGHT][x / GameField.TILE_WIDTH] > 0) {
            return true;
        }

        return false;
    }

    public void leaveOnTheField() {
        glass[y / GameField.TILE_HEIGHT][x / GameField.TILE_WIDTH] = colorBall.getNumber();
        ballsOnField[y / GameField.TILE_HEIGHT][x / GameField.TILE_WIDTH] = this;
    }

    public void moveDown() {
        y += GameField.TILE_HEIGHT;
    }

    public void paint(Graphics g) {
        g.drawImage(colorBall.getImg(), x, y, null);
    }

    public static Ball createBall(int x, int y, BallsFactory factory) {
        return factory.newBall(x, y);
    }
}
