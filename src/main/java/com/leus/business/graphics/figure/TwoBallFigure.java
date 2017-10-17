package com.leus.business.graphics.figure;

import com.leus.business.Game;
import com.leus.business.graphics.sprite.factory.SpriteFactory;
import com.leus.business.graphics.sprite.AbstractSprite;

public final class TwoBallFigure extends AbstractFigure {

    public TwoBallFigure(int startPositionX, int startPositionY, SpriteFactory factory) {
        super(new AbstractSprite[2]);
        initializeSpritesInFigure(startPositionX, startPositionY, factory);
    }

    @Override
    public void rotate() {
        if (!isLeftWall() && !isRightWall() && isCanRotateOnTopFrame()) {
            rotateWithoutWall();
        } else if (isCanRotateOnTopFrame()) {
            rotateAtWall();
        }
    }

    @Override
    protected void initializeSpritesInFigure(int startPositionX, int startPositionY, SpriteFactory creatorSprites) {
        spritesInFigure[0] = AbstractSprite.createSprite(startPositionX, startPositionY, creatorSprites);
        if ((int) (Math.random() * 10) % 2 == 0) {
            spritesInFigure[1] = AbstractSprite.createSprite(startPositionX + Game.TILE_WIDTH, startPositionY, creatorSprites);
        } else {
            spritesInFigure[1] = AbstractSprite.createSprite(startPositionX, startPositionY + Game.TILE_WIDTH, creatorSprites);
        }
    }

    private boolean isHorizontal() {
        return spritesInFigure[0].getCoordinateY() == spritesInFigure[1].getCoordinateY() && spritesInFigure[0].getCoordinateX() != spritesInFigure[1].getCoordinateX();
    }

    private boolean isCanRotateOnTopFrame() {
        return spritesInFigure[0].getCoordinateY() != 0;
    }

    private void rotateWithoutWall() {
        if (isHorizontal()) {
            if (leftSpriteInFigure().getCoordinateX() < spritesInFigure[1].getCoordinateX()) {
                spritesInFigure[1].setCoordinateX(spritesInFigure[0].getCoordinateX());
                spritesInFigure[1].setCoordinateY(spritesInFigure[0].getCoordinateY() + Game.TILE_HEIGHT);
            } else {
                spritesInFigure[1].setCoordinateX(spritesInFigure[0].getCoordinateX());
                spritesInFigure[1].setCoordinateY(spritesInFigure[0].getCoordinateY() - Game.TILE_HEIGHT);
            }
        } else {
            if (topSpriteInFigure().getCoordinateY() < spritesInFigure[1].getCoordinateY()) {
                spritesInFigure[1].setCoordinateY(spritesInFigure[0].getCoordinateY());
                spritesInFigure[1].setCoordinateX(spritesInFigure[0].getCoordinateX() - Game.TILE_WIDTH);
            } else {
                spritesInFigure[1].setCoordinateY(spritesInFigure[0].getCoordinateY());
                spritesInFigure[1].setCoordinateX(spritesInFigure[0].getCoordinateX() + Game.TILE_WIDTH);
            }
        }
    }

    private void rotateAtWall() {
        if (isHorizontal()) {
            rotateWithoutWall();
        } else {
            if (isLeftWall() && !isRightWall()) {
                if (topSpriteInFigure().getCoordinateY() < spritesInFigure[1].getCoordinateY()) {
                    spritesInFigure[0].setCoordinateY(spritesInFigure[1].getCoordinateY());
                    spritesInFigure[0].setCoordinateX(spritesInFigure[1].getCoordinateX() + Game.TILE_WIDTH);
                } else {
                    spritesInFigure[1].setCoordinateY(spritesInFigure[0].getCoordinateY());
                    spritesInFigure[1].setCoordinateX(spritesInFigure[0].getCoordinateX() + Game.TILE_WIDTH);
                }
            } else if (isRightWall() && !isLeftWall()) {
                if (topSpriteInFigure().getCoordinateY() < spritesInFigure[1].getCoordinateY()) {
                    spritesInFigure[1].setCoordinateY(spritesInFigure[0].getCoordinateY());
                    spritesInFigure[1].setCoordinateX(spritesInFigure[0].getCoordinateX() - Game.TILE_WIDTH);
                } else {
                    spritesInFigure[0].setCoordinateY(spritesInFigure[1].getCoordinateY());
                    spritesInFigure[0].setCoordinateX(spritesInFigure[1].getCoordinateX() - Game.TILE_WIDTH);
                }
            }
        }
    }

    private AbstractSprite leftSpriteInFigure() {
        AbstractSprite leftAbstractSprite = spritesInFigure[0];

        if (leftAbstractSprite.getCoordinateX() > spritesInFigure[1].getCoordinateX()) {
            leftAbstractSprite = spritesInFigure[1];
        }

        return leftAbstractSprite;
    }

    private AbstractSprite topSpriteInFigure() {
        AbstractSprite topAbstractSprite = spritesInFigure[0];

        if (topAbstractSprite.getCoordinateY() > spritesInFigure[1].getCoordinateY()) {
            topAbstractSprite = spritesInFigure[1];
        }

        return topAbstractSprite;
    }
}
