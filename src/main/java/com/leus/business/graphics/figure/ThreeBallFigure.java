package com.leus.business.graphics.figure;

import com.leus.business.Game;
import com.leus.business.graphics.sprite.factory.SpriteFactory;
import com.leus.business.graphics.sprite.AbstractSprite;
import com.leus.view.Display;

public final class ThreeBallFigure extends AbstractFigure {

    public ThreeBallFigure(int startPositionX, int startPositionY, SpriteFactory factory) {
        super(new AbstractSprite[3]);
        initializeSpritesInFigure(startPositionX, startPositionY, factory);
    }

    @Override
    public void rotate() {
        if (!isLeftWall() && !isRightWall() && isCanRotateOnTopFrame()) {
            rotateWithoutWall();
        } else if (isCanRotateOnTopFrame() && isSpaceToRotate()) {
            rotateAtWall();
        }
    }

    @Override
    protected void initializeSpritesInFigure(int startPositionX, int startPositionY, SpriteFactory factory) {
        spritesInFigure[0] = AbstractSprite.createSprite(startPositionX, startPositionY, factory);
        if ((int) (Math.random() * 10) % 2 == 0) {
            spritesInFigure[1] = AbstractSprite.createSprite(startPositionX + Game.TILE_WIDTH, startPositionY, factory);
            spritesInFigure[2] = AbstractSprite.createSprite(startPositionX + Game.TILE_WIDTH * 2, startPositionY, factory);
        } else {
            spritesInFigure[1] = AbstractSprite.createSprite(startPositionX, startPositionY + Game.TILE_HEIGHT, factory);
            spritesInFigure[2] = AbstractSprite.createSprite(startPositionX, startPositionY + Game.TILE_HEIGHT * 2, factory);
        }
    }

    private void rotateWithoutWall() {
        if (isHorizontal()) {
            AbstractSprite leftBall = leftSpriteInFigure();
            AbstractSprite rightBall = rightSpriteInFigure();
            if (leftBall.getCoordinateX() != spritesInFigure[1].getCoordinateX() && rightBall.getCoordinateX() != spritesInFigure[1].getCoordinateX()) {
                leftBall.setCoordinateX(spritesInFigure[1].getCoordinateX());
                leftBall.setCoordinateY(spritesInFigure[1].getCoordinateY() - Game.TILE_HEIGHT);
                rightBall.setCoordinateX(spritesInFigure[1].getCoordinateX());
                rightBall.setCoordinateY(spritesInFigure[1].getCoordinateY() + Game.TILE_HEIGHT);
            }
        } else {
            AbstractSprite topBall = topSpriteInFigure();
            AbstractSprite dawnBall = dawnSpriteInFigure();
            if (topBall.getCoordinateY() != spritesInFigure[1].getCoordinateY() && dawnBall.getCoordinateY() != spritesInFigure[1].getCoordinateY()) {
                topBall.setCoordinateY(spritesInFigure[1].getCoordinateY());
                topBall.setCoordinateX(spritesInFigure[1].getCoordinateX() + Game.TILE_HEIGHT);
                dawnBall.setCoordinateY(spritesInFigure[1].getCoordinateY());
                dawnBall.setCoordinateX(spritesInFigure[1].getCoordinateX() - Game.TILE_HEIGHT);
            }
        }
    }

    private void rotateAtWall() {
        AbstractSprite dawnBall = dawnSpriteInFigure();
        AbstractSprite topBall = topSpriteInFigure();
        if (isHorizontal()) {
            rotateWithoutWall();
        } else {
            if (isLeftWall() && !isRightWall()) {
                spritesInFigure[1].setCoordinateY(dawnBall.getCoordinateY());
                spritesInFigure[1].setCoordinateX(dawnBall.getCoordinateX() + Game.TILE_WIDTH);
                topBall.setCoordinateY(dawnBall.getCoordinateY());
                topBall.setCoordinateX(dawnBall.getCoordinateX() + Game.TILE_WIDTH * 2);
            } else if (isRightWall() && !isLeftWall()) {
                spritesInFigure[1].setCoordinateY(topBall.getCoordinateY());
                spritesInFigure[1].setCoordinateX(topBall.getCoordinateX() - Game.TILE_WIDTH);
                dawnBall.setCoordinateY(topBall.getCoordinateY());
                dawnBall.setCoordinateX(topBall.getCoordinateX() - Game.TILE_WIDTH * 2);
            }
        }
    }

    private boolean isHorizontal() {
        return spritesInFigure[0].getCoordinateY() == spritesInFigure[1].getCoordinateY() &&
                spritesInFigure[1].getCoordinateY() == spritesInFigure[2].getCoordinateY() &&
                spritesInFigure[0].getCoordinateY() == spritesInFigure[2].getCoordinateY() &&
                spritesInFigure[0].getCoordinateX() != spritesInFigure[1].getCoordinateX() &&
                spritesInFigure[1].getCoordinateX() != spritesInFigure[2].getCoordinateX() &&
                spritesInFigure[0].getCoordinateX() != spritesInFigure[2].getCoordinateX();
    }

    private boolean isCanRotateOnTopFrame() {
        return spritesInFigure[1].getCoordinateY() != 0;
    }

    private boolean isSpaceToRotate() {
        if (!isHorizontal() && isLeftWall()) {
            for (AbstractSprite elem : spritesInFigure) {
                if ((elem.getCoordinateX() / Game.TILE_WIDTH + 1) == Display.WIDTH_WINDOW_IN_TILES ||
                    (elem.getCoordinateX() / Game.TILE_WIDTH + 2) == Display.WIDTH_WINDOW_IN_TILES ||
                    GAME_FIELD_MATRIX[elem.getCoordinateY() / Game.TILE_HEIGHT][elem.getCoordinateX() / Game.TILE_WIDTH + 1] != null ||
                    GAME_FIELD_MATRIX[elem.getCoordinateY() / Game.TILE_HEIGHT][elem.getCoordinateX() / Game.TILE_WIDTH + 2] != null) {
                    return false;
                }
            }
        } else if (!isHorizontal() && isRightWall()) {
            for (AbstractSprite elem : spritesInFigure) {
                if ((elem.getCoordinateX() / Game.TILE_WIDTH - 1) == 0 ||
                    (elem.getCoordinateX() / Game.TILE_WIDTH - 2) == 0 ||
                    GAME_FIELD_MATRIX[elem.getCoordinateY() / Game.TILE_HEIGHT][elem.getCoordinateX() / Game.TILE_WIDTH - 1] != null ||
                    GAME_FIELD_MATRIX[elem.getCoordinateY() / Game.TILE_HEIGHT][elem.getCoordinateX() / Game.TILE_WIDTH - 2] != null) {
                    return false;
                }
            }
        }

        return true;
    }

    private AbstractSprite leftSpriteInFigure() {
        for (AbstractSprite elem : spritesInFigure) {
            if (elem.getCoordinateX() < spritesInFigure[1].getCoordinateX()) {
                return elem;
            }
        }

        return spritesInFigure[1];
    }

    private AbstractSprite rightSpriteInFigure() {
        for (AbstractSprite elem : spritesInFigure) {
            if (elem.getCoordinateX() > spritesInFigure[1].getCoordinateX()) {
                return elem;
            }
        }

        return spritesInFigure[1];
    }

    private AbstractSprite topSpriteInFigure() {
        for (AbstractSprite elem : spritesInFigure) {
            if (elem.getCoordinateY() < spritesInFigure[1].getCoordinateY()) {
                return elem;
            }
        }

        return spritesInFigure[1];
    }

    private AbstractSprite dawnSpriteInFigure() {
        for (AbstractSprite elem : spritesInFigure) {
            if (elem.getCoordinateY() > spritesInFigure[1].getCoordinateY()) {
                return elem;
            }
        }

        return spritesInFigure[1];
    }
}
