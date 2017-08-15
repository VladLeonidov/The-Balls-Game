package com.leus.model.graphics.figures;

import com.leus.model.GameField;
import com.leus.model.factories.spriteFactories.SpriteFactory;
import com.leus.model.graphics.sprites.AbstractSprite;
import com.leus.utils.CreatorOfSprites;
import com.leus.view.PcGameFrameBuilder;

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
    public void moveLeft() {
        if (isCanMoveLeft()) {
            spritesInFigure[0].moveLeft();
            spritesInFigure[1].moveLeft();
        }
    }

    @Override
    public void moveRight() {
        if (isCanMoveRight()) {
            spritesInFigure[0].moveRight();
            spritesInFigure[1].moveRight();
        }
    }

    @Override
    public void moveDown() {
        for (AbstractSprite abstractSprite : spritesInFigure) {
            abstractSprite.moveDown();
        }
    }

    @Override
    public boolean isFrozen() {
        for (AbstractSprite sprite : spritesInFigure) {
            if (sprite.getY() / GameField.TILE_HEIGHT == PcGameFrameBuilder.getFieldHeightInTile() || gameFieldMatrix[sprite.getY() / GameField.TILE_HEIGHT + 1][sprite.getX() / GameField.TILE_WIDTH] != null) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void leaveOnTheField() {
        for (AbstractSprite sprite : spritesInFigure) {
            gameFieldMatrix[sprite.getY() / GameField.TILE_HEIGHT][sprite.getX() / GameField.TILE_WIDTH] = sprite;
        }
    }

    @Override
    protected void initializeSpritesInFigure(int startPositionX, int startPositionY, SpriteFactory factory) {
        spritesInFigure[0] = CreatorOfSprites.createSprite(startPositionX, startPositionY, factory);
        spritesInFigure[1] = CreatorOfSprites.createSprite(startPositionX + GameField.TILE_WIDTH, startPositionY, factory);
    }

    private boolean isHorizontal() {
        return spritesInFigure[0].getY() == spritesInFigure[1].getY() || spritesInFigure[0].getX() != spritesInFigure[1].getX();
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
        return (spritesInFigure[0].getX() <= 5) || (spritesInFigure[1].getX() <= 5)
                || (gameFieldMatrix[spritesInFigure[0].getY() / GameField.TILE_HEIGHT][spritesInFigure[0].getX() / GameField.TILE_HEIGHT - 1] != null
                || gameFieldMatrix[spritesInFigure[1].getY() / GameField.TILE_HEIGHT][spritesInFigure[1].getX() / GameField.TILE_HEIGHT - 1] != null);

    }

    private boolean isRightWall() {
        return (spritesInFigure[0].getX() >= PcGameFrameBuilder.getWidthGameFrame() - GameField.TILE_WIDTH) || (spritesInFigure[1].getX() >= PcGameFrameBuilder.getWidthGameFrame() - GameField.TILE_WIDTH)
                || (gameFieldMatrix[spritesInFigure[0].getY() / GameField.TILE_HEIGHT][spritesInFigure[0].getX() / GameField.TILE_HEIGHT + 1] != null
                || gameFieldMatrix[spritesInFigure[1].getY() / GameField.TILE_HEIGHT][spritesInFigure[1].getX() / GameField.TILE_HEIGHT + 1] != null);

    }

    private boolean isCanMoveLeft() {
        if ((spritesInFigure[0].getX() <= 5) || (spritesInFigure[1].getX() <= 5)) {
            return false;
        } else {
            if (isHorizontal()) {
                AbstractSprite leftAbstractSprite = leftSpriteInFigure();
                if (gameFieldMatrix[leftAbstractSprite.getY() / GameField.TILE_HEIGHT][leftAbstractSprite.getX() / GameField.TILE_WIDTH - 1] != null) {
                    return false;
                }
            } else {
                AbstractSprite topAbstractSprite = topSpriteInFigure();
                if (gameFieldMatrix[(topAbstractSprite.getY() + GameField.TILE_HEIGHT) / GameField.TILE_HEIGHT][topAbstractSprite.getX() / GameField.TILE_WIDTH - 1] != null) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isCanMoveRight() {
        if ((spritesInFigure[0].getX() >= PcGameFrameBuilder.getWidthGameFrame() - GameField.TILE_WIDTH) || (spritesInFigure[1].getX() >= PcGameFrameBuilder.getWidthGameFrame() - GameField.TILE_WIDTH)) {
            return false;
        } else {
            if (isHorizontal()) {
                AbstractSprite leftAbstractSprite = leftSpriteInFigure();
                if (gameFieldMatrix[leftAbstractSprite.getY() / GameField.TILE_HEIGHT][(leftAbstractSprite.getX() + GameField.TILE_WIDTH) / GameField.TILE_WIDTH + 1] != null) {
                    return false;
                }
            } else {
                AbstractSprite topAbstractSprite = topSpriteInFigure();
                if (gameFieldMatrix[(topAbstractSprite.getY() + GameField.TILE_HEIGHT) / GameField.TILE_HEIGHT][topAbstractSprite.getX() / GameField.TILE_WIDTH + 1] != null) {
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
