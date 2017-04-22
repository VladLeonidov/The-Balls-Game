package com.leus.game.graphics;

import com.leus.displays.GameFrame;
import com.leus.enums.ColorBalls;
import com.leus.game.factories.BallsFactory;
import com.leus.game.fields.GameField;

import java.awt.*;
import java.util.List;

public class Ball {

    private static int id;
    private final int countId = id++;

    private static int[][] coordinateBalls = new int[GameFrame.FIELD_HEIGHT_IN_TILE * GameFrame.FIELD_WIDTH_IN_TILE][2];
    private static int[][] coordinateForClearBalls = GameField.getCoordinateForClearBalls();
    private static List<Ball> ballsOnField = GameField.getBallsOnField();

    private int x;
    private int y;
    private boolean isFrosen;

    private ColorBalls colorBall;
    private static int[][] glass = GameField.getGlass();

    public Ball(int x, int y, ColorBalls colorBall) {
        this.x = x;
        this.y = y;
        this.colorBall = colorBall;
    }

    public static int[][] getCoordinateBalls() {
        return coordinateBalls;
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
        coordinateBalls[countId][0] = y;
        coordinateBalls[countId][1] = x;
    }

    public void moveDown() {
        y += GameField.TILE_HEIGHT;
    }

    public void paint(Graphics g) {
        g.drawImage(colorBall.getImg(), x, y, null);
    }

    public void dispose() {
        for (int i = 0; i < coordinateBalls.length; i++) {
            for (int j = 0; j < coordinateForClearBalls.length; j++) {
                if ((coordinateBalls[i][0] == coordinateForClearBalls[i][0]) &&
                    (coordinateBalls[i][1] == coordinateForClearBalls[i][1])) {
                     for (Ball ball : ballsOnField) {
                         if (coordinateForClearBalls[i][0] == ball.getY() &&
                             coordinateForClearBalls[i][0] == ball.getX()) {
                             ballsOnField.remove(ball);
                         }
                     }
                }
            }
        }
    }

    public static Ball createBall(int x, int y, BallsFactory factory) {
        return factory.newBall(x, y);
    }
}
