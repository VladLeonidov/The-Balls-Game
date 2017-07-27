package com.leus.model.graphics.figures;

import com.leus.model.fields.GameField;
import com.leus.model.graphics.sprites.AbstractSprite;
import com.leus.utils.CreatorOfSprites;
import com.leus.view.displays.GameFrame;

public class TwoBallFigure extends AbstractFigure {

    public TwoBallFigure() {
        super();
        spritesInFigure[0] = CreatorOfSprites.createSprite(getStartPositionX(), getStartPositionY(), getSpriteFactory());
        spritesInFigure[1] = CreatorOfSprites.createSprite(getStartPositionX() + GameField.TILE_WIDTH, getStartPositionY(), getSpriteFactory());
    }

    public void rotate() {
        if (!isLeftWall() && !isRightWall()) {
            rotateWithoutWall();
        } else {
            rotateAtWall();
        }
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
        for (AbstractSprite abstractSprite : spritesInFigure) {
            abstractSprite.moveDown();
        }
    }

    private boolean isHorizontal() {
        if (spritesInFigure[0].getY() != spritesInFigure[1].getY()) {
            return false;
        }

        return true;
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
        if (isHorizontal()) {
            rotateWithoutWall();
        } else {
            if (isLeftWall()) {
                if (topSpriteInFigure().getY() < spritesInFigure[1].getY()) {
                    spritesInFigure[0].setY(spritesInFigure[1].getY());
                    spritesInFigure[0].setX(spritesInFigure[1].getX() + GameField.TILE_WIDTH);
                } else {
                    spritesInFigure[1].setY(spritesInFigure[0].getY());
                    spritesInFigure[1].setX(spritesInFigure[0].getX() + GameField.TILE_WIDTH);
                }
            } else if (isRightWall()) {
                if (topSpriteInFigure().getY() < spritesInFigure[1].getY()) {
                    spritesInFigure[1].setY(spritesInFigure[0].getY());
                    spritesInFigure[1].setX(spritesInFigure[0].getX() - GameField.TILE_WIDTH);
                } else {
                    spritesInFigure[0].setY(spritesInFigure[1].getY());
                    spritesInFigure[0].setX(spritesInFigure[1].getX() - GameField.TILE_WIDTH);
                }
            }
        }
    }

    private boolean isLeftWall() {
        if ((spritesInFigure[0].getX() <= 5) || (spritesInFigure[1].getX() <= 5)
            || (spritesOnField[spritesInFigure[0].getY() / GameField.TILE_HEIGHT][spritesInFigure[0].getX() / GameField.TILE_HEIGHT - 1] != null
            || spritesOnField[spritesInFigure[1].getY() / GameField.TILE_HEIGHT][spritesInFigure[1].getX() / GameField.TILE_HEIGHT - 1] != null)) {
            return true;
        }

        return false;
    }

    private boolean isRightWall() {
        if ((spritesInFigure[0].getX() >= GameFrame.WIDTH_GAME_FRAME - GameField.TILE_WIDTH) || (spritesInFigure[1].getX() >= GameFrame.WIDTH_GAME_FRAME - GameField.TILE_WIDTH)
            || (spritesOnField[spritesInFigure[0].getY() / GameField.TILE_HEIGHT][spritesInFigure[0].getX() / GameField.TILE_HEIGHT + 1] != null
            || spritesOnField[spritesInFigure[1].getY() / GameField.TILE_HEIGHT][spritesInFigure[1].getX() / GameField.TILE_HEIGHT + 1] != null)) {
            return true;
        }

        return false;
    }

    private boolean isCanMoveLeft() {
        if ((spritesInFigure[0].getX() <= 5) || (spritesInFigure[1].getX() <= 5)) {
            return false;
        } else {
            if (isHorizontal()) {
                AbstractSprite leftAbstractSprite = leftSpriteInFigure();
                if (spritesOnField[leftAbstractSprite.getY() / GameField.TILE_HEIGHT][leftAbstractSprite.getX() / GameField.TILE_WIDTH - 1] != null) {
                    return false;
                }
            } else {
                AbstractSprite topAbstractSprite = topSpriteInFigure();
                if (spritesOnField[(topAbstractSprite.getY() + GameField.TILE_HEIGHT) / GameField.TILE_HEIGHT][topAbstractSprite.getX() / GameField.TILE_WIDTH - 1] != null) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isCanMoveRight() {
        if ((spritesInFigure[0].getX() >= GameFrame.WIDTH_GAME_FRAME - GameField.TILE_WIDTH) || (spritesInFigure[1].getX() >= GameFrame.WIDTH_GAME_FRAME - GameField.TILE_WIDTH)) {
            return false;
        } else {
            if (isHorizontal()) {
                AbstractSprite leftAbstractSprite = leftSpriteInFigure();
                if (spritesOnField[leftAbstractSprite.getY() / GameField.TILE_HEIGHT][(leftAbstractSprite.getX() + GameField.TILE_WIDTH) / GameField.TILE_WIDTH + 1] != null) {
                    return false;
                }
            } else {
                AbstractSprite topAbstractSprite = topSpriteInFigure();
                if (spritesOnField[(topAbstractSprite.getY() + GameField.TILE_HEIGHT) / GameField.TILE_HEIGHT][topAbstractSprite.getX() / GameField.TILE_WIDTH + 1] != null) {
                    return false;
                }
            }
        }

        return true;
    }

    private AbstractSprite leftSpriteInFigure() {
        AbstractSprite leftAbstractSprite = spritesInFigure[0];

        if (leftAbstractSprite.getX() > spritesInFigure[1].getX()) {
            leftAbstractSprite = spritesInFigure[1];
        }

        return leftAbstractSprite;
    }

    private AbstractSprite topSpriteInFigure() {
        AbstractSprite topAbstractSprite = spritesInFigure[0];

        if (topAbstractSprite.getY() > spritesInFigure[1].getY()) {
            topAbstractSprite = spritesInFigure[1];
        }

        return topAbstractSprite;
    }
}
