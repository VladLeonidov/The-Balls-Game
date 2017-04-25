package com.leus.game.graphics;

import com.leus.displays.GameFrame;
import com.leus.game.fields.GameField;

import java.awt.event.KeyEvent;

public class Figure {

    private Ball firstBall;
    private Ball secondBall;

    private int x;
    private int y;

    private boolean isLeftSecondBall = true;
    private boolean isRightSecondBall = true;
    private boolean isVertical;

    private Ball[][] ballsOnField = GameField.getBallsOnField();

    public boolean isVertical() {
        return isVertical;
    }

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
        this.y = firstBall.getY();
    }

    public void rotate() {
        if (isRotate()) {
            rotateWithoutWall();
        } else {
            rotateAtWall();
        }
    }

    public void move(int description) {
        if (!isWall(description)) {
            x += (description - 38) * GameField.TILE_WIDTH;
            if (isRightSecondBall) {
                firstBall.setX(x);
                secondBall.setX(x + GameField.TILE_WIDTH);
            } else if (isLeftSecondBall) {
                firstBall.setX(x + GameField.TILE_WIDTH);
                secondBall.setX(x);
            } else if (isVertical) {
                firstBall.setX(x);
                secondBall.setX(x);
            }
        }
    }

    public void fastMoveDown() {
        while (!firstBall.isFrozen() && !secondBall.isFrozen()) {
            firstBall.moveDown();
            secondBall.moveDown();
        }
    }

    private boolean isWall(int description) {
        if (isLeftWall(description)) {
            return true;
        } else if (isRightWall(description)) {
            return true;
        }

        return false;
    }

    private boolean isLeftWall(int description) {
        if (isVertical) {
            if (description == KeyEvent.VK_LEFT && (x < 0 + GameField.TILE_WIDTH || ballsOnField[firstBall.getY() / GameField.TILE_HEIGHT][firstBall.getX() / GameField.TILE_WIDTH - 1] != null))
                return true;
        } else if (isRightSecondBall) {
            if (description == KeyEvent.VK_LEFT && (x < 0 + GameField.TILE_WIDTH || ballsOnField[firstBall.getY() / GameField.TILE_HEIGHT][firstBall.getX() / GameField.TILE_WIDTH - 1] != null))
                return true;
        } else if (isLeftSecondBall) {
            if (description == KeyEvent.VK_LEFT && (x < 0 + GameField.TILE_WIDTH || ballsOnField[secondBall.getY() / GameField.TILE_HEIGHT][secondBall.getX() / GameField.TILE_WIDTH - 1] != null))
                return true;
        }

        return false;
    }

    private boolean isRightWall(int description) {
        if (isVertical) {
            if (description == KeyEvent.VK_RIGHT && (x > GameFrame.WIDTH_GAME_FRAME - GameField.TILE_WIDTH || ballsOnField[secondBall.getY() / GameField.TILE_HEIGHT][secondBall.getX() / GameField.TILE_WIDTH + 1] != null))
                return true;
        } else if (isRightSecondBall) {
            if (description == KeyEvent.VK_RIGHT && (x > GameFrame.WIDTH_GAME_FRAME - GameField.TILE_WIDTH * 2 || ballsOnField[secondBall.getY() / GameField.TILE_HEIGHT][secondBall.getX() / GameField.TILE_WIDTH + 1] != null))
                return true;
        } else if (isLeftSecondBall) {
            if (description == KeyEvent.VK_RIGHT && (x > GameFrame.WIDTH_GAME_FRAME - GameField.TILE_WIDTH * 2 || ballsOnField[firstBall.getY() / GameField.TILE_HEIGHT][firstBall.getX() / GameField.TILE_WIDTH + 1] != null))
                return true;
        }

        return false;
    }

    private boolean isRotate() {
        if (secondBall.getY() > firstBall.getY()) {
            return isRotateLeft();
        } else if (secondBall.getY() < firstBall.getY()) {
            return isRotateRight();
        }

        return true;
    }

    private boolean isRotateLeft() {
        if ((secondBall.getX() / GameField.TILE_WIDTH - 1 < 0) ||
            (ballsOnField[secondBall.getY() / GameField.TILE_HEIGHT][secondBall.getX() / GameField.TILE_WIDTH - 1] != null)) {
            return false;
        }

        return true;
    }

    private boolean isRotateRight() {
        if ((secondBall.getX() / GameField.TILE_WIDTH + 1 > GameFrame.FIELD_WIDTH_IN_TILE - 1) ||
            (ballsOnField[secondBall.getY() / GameField.TILE_HEIGHT][secondBall.getX() / GameField.TILE_WIDTH + 1] != null)) {
            return false;
        }

        return true;
    }

    private void rotateAtWall() {
        if ((secondBall.getY() == firstBall.getY()) && (secondBall.getX() > firstBall.getX())) {
            rotateDawn();
        } else if ((secondBall.getY() == firstBall.getY()) && (secondBall.getX() < firstBall.getX())) {
            rotateTop();
        } else if ((secondBall.getY() > firstBall.getY()) && (secondBall.getX() == firstBall.getX())) {
            rotateLeftAtWall();
        } else if ((secondBall.getY() < firstBall.getY()) && (secondBall.getX() == firstBall.getX())) {
            rotateRightAtWall();
        }
    }

    private void rotateWithoutWall() {
        if ((secondBall.getY() == firstBall.getY()) && (secondBall.getX() > firstBall.getX())) {
            rotateDawn();
        } else if ((secondBall.getY() == firstBall.getY()) && (secondBall.getX() < firstBall.getX())) {
            rotateTop();
        } else if ((secondBall.getY() > firstBall.getY()) && (secondBall.getX() == firstBall.getX())) {
            rotateLeft();
        } else if ((secondBall.getY() < firstBall.getY()) && (secondBall.getX() == firstBall.getX())) {
            rotateRight();
        }
    }

    private void rotateDawn() {
        secondBall.setX(firstBall.getX());
        secondBall.setY(firstBall.getY() + GameField.TILE_HEIGHT);
        x = firstBall.getX();
        y = firstBall.getY();
        isLeftSecondBall = false;
        isRightSecondBall = false;
        isVertical = true;
    }

    private void rotateTop() {
        secondBall.setX(firstBall.getX());
        secondBall.setY(firstBall.getY() - GameField.TILE_HEIGHT);
        x = secondBall.getX();
        y = secondBall.getY();
        isLeftSecondBall = false;
        isRightSecondBall = false;
        isVertical = true;
    }

    private void rotateLeft() {
        secondBall.setY(firstBall.getY());
        secondBall.setX(firstBall.getX() - GameField.TILE_WIDTH);
        x = secondBall.getX();
        y = secondBall.getY();
        isLeftSecondBall = true;
        isRightSecondBall = false;
        isVertical = false;
    }

    private void rotateLeftAtWall() {
        if (!(ballsOnField[secondBall.getY() / GameField.TILE_HEIGHT][secondBall.getX() / GameField.TILE_WIDTH + 1] != null) ||
            !(ballsOnField[firstBall.getY() / GameField.TILE_HEIGHT][firstBall.getX() / GameField.TILE_WIDTH + 1] != null)) {
            firstBall.setX(firstBall.getX() + GameField.TILE_WIDTH);
            secondBall.setX(secondBall.getX() + GameField.TILE_WIDTH);
            rotateLeft();
        }
    }

    private void rotateRight() {
        secondBall.setY(firstBall.getY());
        secondBall.setX(firstBall.getX() + GameField.TILE_WIDTH);
        x = firstBall.getX();
        y = firstBall.getY();
        isLeftSecondBall = false;
        isRightSecondBall = true;
        isVertical = false;
    }

    private void rotateRightAtWall() {
        if (!(ballsOnField[secondBall.getY() / GameField.TILE_HEIGHT][secondBall.getX() / GameField.TILE_WIDTH - 1] != null) ||
            !(ballsOnField[firstBall.getY() / GameField.TILE_HEIGHT][firstBall.getX() / GameField.TILE_WIDTH - 1] != null)) {
            firstBall.setX(firstBall.getX() - GameField.TILE_WIDTH);
            secondBall.setX(secondBall.getX() - GameField.TILE_WIDTH);
            rotateRight();
        }
    }
}
