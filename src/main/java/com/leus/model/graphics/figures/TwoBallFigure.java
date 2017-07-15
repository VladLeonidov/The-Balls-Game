package com.leus.model.graphics.figures;

import com.leus.model.factories.RandBallFactory;
import com.leus.model.fields.GameField;
import com.leus.model.graphics.sprites.Sprite;
import com.leus.utils.Creator;

public class TwoBallFigure extends Figure {

    private boolean isHorizontal = true;

    public TwoBallFigure() {
        super();
        spriteFactory = new RandBallFactory();
        spritesInFigure[0] = Creator.createBall(START_POSITION_X, START_POSITION_Y, spriteFactory);
        spritesInFigure[1] = Creator.createBall(START_POSITION_X + GameField.TILE_WIDTH, START_POSITION_Y, spriteFactory);
    }

    public boolean isHorizontal() {
        if (spritesInFigure[0].getY() == spritesInFigure[1].getY()) {
            isHorizontal = true;
        } else {
            isHorizontal = false;
        }

        return isHorizontal;
    }

    public void rotate() {
        if (!isLeftWall() && !isRightWall()) {
            rotateWithoutWall();
        } else {
            rotateAtWall();
        }

        //TODO: need normal algorithm for rotate a figure
    }

    public void moveLeft() {
        if (isCanMoveLeft()) {
            spritesInFigure[0].moveLeft();
            spritesInFigure[1].moveLeft();
        }
    }

    public void moveRight() {
        if (isCanMoveRight()) {
            spritesInFigure[0].moveRight();
            spritesInFigure[1].moveRight();
        }
    }

    public void moveDownFigure() {
        for (Sprite sprite : spritesInFigure) {
            sprite.moveDown();
        }
    }

    private void rotateWithoutWall() {
        if (isHorizontal()) {
            if (leftSpriteInFigure().getX() < spritesInFigure[1].getX()) {
                spritesInFigure[1].setX(spritesInFigure[0].getX());
                spritesInFigure[1].setY(spritesInFigure[0].getY() + GameField.TILE_HEIGHT);
            } else {
                spritesInFigure[1].setX(spritesInFigure[0].getX());
                spritesInFigure[1].setY(spritesInFigure[0].getY() - GameField.TILE_HEIGHT);
            }
        } else {
            if (topSpriteInFigure().getY() < spritesInFigure[1].getY()) {
                spritesInFigure[1].setY(spritesInFigure[0].getY());
                spritesInFigure[1].setX(spritesInFigure[0].getX() - GameField.TILE_WIDTH);
            } else {
                spritesInFigure[1].setY(spritesInFigure[0].getY());
                spritesInFigure[1].setX(spritesInFigure[0].getX() + GameField.TILE_WIDTH);
            }
        }
    }

    private void rotateAtWall() {

    }

    private boolean isLeftWall() {
        return false;
    }

    private boolean isRightWall() {
        return false;
    }

    private boolean isCanMoveLeft() {
        if (isHorizontal()) {
            Sprite leftSprite = leftSpriteInFigure();
            if (spritesOnField[leftSprite.getY() / GameField.TILE_HEIGHT][leftSprite.getX() / GameField.TILE_WIDTH - 1] != null) {
                return false;
            }
        } else {
            Sprite topSprite = topSpriteInFigure();
            if (spritesOnField[(topSprite.getY() + GameField.TILE_HEIGHT) / GameField.TILE_HEIGHT][topSprite.getX() / GameField.TILE_WIDTH - 1] != null) {
                return false;
            }
        }

        return true;
    }

    private boolean isCanMoveRight() {
        if (isHorizontal()) {
            Sprite leftSprite = leftSpriteInFigure();
            if (spritesOnField[leftSprite.getY() / GameField.TILE_HEIGHT][(leftSprite.getX() + GameField.TILE_WIDTH) / GameField.TILE_WIDTH + 1] != null) {
                return false;
            }
        } else {
            Sprite topSprite = topSpriteInFigure();
            if (spritesOnField[(topSprite.getY() + GameField.TILE_HEIGHT) / GameField.TILE_HEIGHT][topSprite.getX() / GameField.TILE_WIDTH + 1] != null) {
                return false;
            }
        }

        return true;
    }

    private Sprite leftSpriteInFigure() {
        Sprite leftSprite = spritesInFigure[0];

        if (leftSprite.getX() > spritesInFigure[1].getX()) {
            leftSprite = spritesInFigure[1];
        }

        return leftSprite;
    }

    private Sprite topSpriteInFigure() {
        Sprite topSprite = spritesInFigure[0];

        if (topSprite.getY() > spritesInFigure[1].getY()) {
            topSprite = spritesInFigure[1];
        }

        return topSprite;
    }
}
