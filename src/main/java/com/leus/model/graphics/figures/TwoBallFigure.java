package com.leus.model.graphics.figures;

import com.leus.model.GameField;
import com.leus.model.factories.spriteFactories.SpriteFactory;
import com.leus.model.graphics.sprites.AbstractSprite;
import com.leus.utils.CreatorOfSprites;
import com.leus.view.displays.PcDisplay;

public class TwoBallFigure extends AbstractFigure {

    public TwoBallFigure(int startPositionX, int startPositionY, SpriteFactory factory) {
        super(new AbstractSprite[2]);
        initializeSpritesInFigure(startPositionX, startPositionY, factory);
    }

    @Override
    public void rotate() {
        if (!isLeftWall() && !isRightWall()) {
            rotateWithoutWall();
        } else {
            rotateAtWall();
        }
    }

    @Override
    protected void initializeSpritesInFigure(int startPositionX, int startPositionY, SpriteFactory creatorSprites) {
        spritesInFigure[0] = CreatorOfSprites.createSprite(startPositionX, startPositionY, creatorSprites);
        spritesInFigure[1] = CreatorOfSprites.createSprite(startPositionX + GameField.TILE_WIDTH, startPositionY, creatorSprites);
    }

    @Override
    protected boolean isCanMoveLeft() {
        if ((spritesInFigure[0].getCoordinateX() <= 5) || (spritesInFigure[1].getCoordinateX() <= 5)) {
            return false;
        } else {
            if (isHorizontal()) {
                AbstractSprite leftAbstractSprite = leftSpriteInFigure();
                if (gameFieldMatrix[leftAbstractSprite.getCoordinateY() / GameField.TILE_HEIGHT][leftAbstractSprite.getCoordinateX() / GameField.TILE_WIDTH - 1] != null) {
                    return false;
                }
            } else {
                AbstractSprite topAbstractSprite = topSpriteInFigure();
                if (gameFieldMatrix[(topAbstractSprite.getCoordinateY() + GameField.TILE_HEIGHT) / GameField.TILE_HEIGHT][topAbstractSprite.getCoordinateX() / GameField.TILE_WIDTH - 1] != null) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    protected boolean isCanMoveRight() {
        if ((spritesInFigure[0].getCoordinateX() >= PcDisplay.getWidthWindow() - GameField.TILE_WIDTH) || (spritesInFigure[1].getCoordinateX() >= PcDisplay.getWidthWindow() - GameField.TILE_WIDTH)) {
            return false;
        } else {
            if (isHorizontal()) {
                AbstractSprite leftAbstractSprite = leftSpriteInFigure();
                if (gameFieldMatrix[leftAbstractSprite.getCoordinateY() / GameField.TILE_HEIGHT][(leftAbstractSprite.getCoordinateX() + GameField.TILE_WIDTH) / GameField.TILE_WIDTH + 1] != null) {
                    return false;
                }
            } else {
                AbstractSprite topAbstractSprite = topSpriteInFigure();
                if (gameFieldMatrix[(topAbstractSprite.getCoordinateY() + GameField.TILE_HEIGHT) / GameField.TILE_HEIGHT][topAbstractSprite.getCoordinateX() / GameField.TILE_WIDTH + 1] != null) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isHorizontal() {
        return spritesInFigure[0].getCoordinateY() == spritesInFigure[1].getCoordinateY() || spritesInFigure[0].getCoordinateX() != spritesInFigure[1].getCoordinateX();
    }

    private void rotateWithoutWall() {
        if (isHorizontal()) {
            if (leftSpriteInFigure().getCoordinateX() < spritesInFigure[1].getCoordinateX()) {
                spritesInFigure[1].setCoordinateX(spritesInFigure[0].getCoordinateX());
                spritesInFigure[1].setCoordinateY(spritesInFigure[0].getCoordinateY() + GameField.TILE_HEIGHT);
            } else {
                spritesInFigure[1].setCoordinateX(spritesInFigure[0].getCoordinateX());
                spritesInFigure[1].setCoordinateY(spritesInFigure[0].getCoordinateY() - GameField.TILE_HEIGHT);
            }
        } else {
            if (topSpriteInFigure().getCoordinateY() < spritesInFigure[1].getCoordinateY()) {
                spritesInFigure[1].setCoordinateY(spritesInFigure[0].getCoordinateY());
                spritesInFigure[1].setCoordinateX(spritesInFigure[0].getCoordinateX() - GameField.TILE_WIDTH);
            } else {
                spritesInFigure[1].setCoordinateY(spritesInFigure[0].getCoordinateY());
                spritesInFigure[1].setCoordinateX(spritesInFigure[0].getCoordinateX() + GameField.TILE_WIDTH);
            }
        }
    }

    private void rotateAtWall() {
        if (isHorizontal()) {
            rotateWithoutWall();
        } else {
            if (isLeftWall()) {
                if (topSpriteInFigure().getCoordinateY() < spritesInFigure[1].getCoordinateY()) {
                    spritesInFigure[0].setCoordinateY(spritesInFigure[1].getCoordinateY());
                    spritesInFigure[0].setCoordinateX(spritesInFigure[1].getCoordinateX() + GameField.TILE_WIDTH);
                } else {
                    spritesInFigure[1].setCoordinateY(spritesInFigure[0].getCoordinateY());
                    spritesInFigure[1].setCoordinateX(spritesInFigure[0].getCoordinateX() + GameField.TILE_WIDTH);
                }
            } else if (isRightWall()) {
                if (topSpriteInFigure().getCoordinateY() < spritesInFigure[1].getCoordinateY()) {
                    spritesInFigure[1].setCoordinateY(spritesInFigure[0].getCoordinateY());
                    spritesInFigure[1].setCoordinateX(spritesInFigure[0].getCoordinateX() - GameField.TILE_WIDTH);
                } else {
                    spritesInFigure[0].setCoordinateY(spritesInFigure[1].getCoordinateY());
                    spritesInFigure[0].setCoordinateX(spritesInFigure[1].getCoordinateX() - GameField.TILE_WIDTH);
                }
            }
        }
    }

    private boolean isLeftWall() {
        return (spritesInFigure[0].getCoordinateX() <= 5) || (spritesInFigure[1].getCoordinateX() <= 5)
                || (gameFieldMatrix[spritesInFigure[0].getCoordinateY() / GameField.TILE_HEIGHT][spritesInFigure[0].getCoordinateX() / GameField.TILE_HEIGHT - 1] != null
                || gameFieldMatrix[spritesInFigure[1].getCoordinateY() / GameField.TILE_HEIGHT][spritesInFigure[1].getCoordinateX() / GameField.TILE_HEIGHT - 1] != null);

    }

    private boolean isRightWall() {
        return (spritesInFigure[0].getCoordinateX() >= PcDisplay.getWidthWindow() - GameField.TILE_WIDTH) || (spritesInFigure[1].getCoordinateX() >= PcDisplay.getWidthWindow() - GameField.TILE_WIDTH)
                || (gameFieldMatrix[spritesInFigure[0].getCoordinateY() / GameField.TILE_HEIGHT][spritesInFigure[0].getCoordinateX() / GameField.TILE_HEIGHT + 1] != null
                || gameFieldMatrix[spritesInFigure[1].getCoordinateY() / GameField.TILE_HEIGHT][spritesInFigure[1].getCoordinateX() / GameField.TILE_HEIGHT + 1] != null);

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
