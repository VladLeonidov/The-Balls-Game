package com.leus.game.graphics;

import com.leus.displays.GameFrame;
import com.leus.game.fields.GameField;

import java.awt.event.KeyEvent;

public class Figure {

    private Ball firstBall;
    private Ball secondBall;

    private int x;
    private int y;

    private int[][] matrix = GameField.getMatrix();

    public Ball getFirstBall() {
        return firstBall;
    }

    public Ball getSecondBall() {
        return secondBall;
    }

    public Figure(Ball firstBall, Ball secondBall) {
        this.firstBall = firstBall;
        this.secondBall = secondBall;
        this.x = firstBall.getX();
        this.y = secondBall.getY();
    }

    public void rotate() {
        //TODO: Need implement rotate figure
    }

    public void move(int description) {
        if (!isWall(description)) {
            x += (description - 38) * GameField.TILE_WIDTH;
            firstBall.setX(x);
            secondBall.setX(x + GameField.TILE_WIDTH);
        }
    }

    public void fastMoveDown() {
        while (!firstBall.isFrozen() && !secondBall.isFrozen()) {
            firstBall.moveDown();
            secondBall.moveDown();
        }
    }

    private boolean isWall(int description) {
        if (description == KeyEvent.VK_LEFT && (x < 0 + GameField.TILE_WIDTH || matrix[firstBall.getY() / GameField.TILE_HEIGHT][firstBall.getX() / GameField.TILE_WIDTH - 1] > 0))
            return true;

        if (description == KeyEvent.VK_RIGHT && (x > GameFrame.WIDTH_GAME_FRAME - GameField.TILE_WIDTH * 2 || matrix[secondBall.getY() / GameField.TILE_HEIGHT][secondBall.getX() / GameField.TILE_WIDTH + 1] > 0))
            return true;

        return false;
    }
}
