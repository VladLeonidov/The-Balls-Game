package com.leus.model.graphics.figures;

import com.leus.model.Game;
import com.leus.model.factories.spriteFactories.SpriteFactory;
import com.leus.model.graphics.sprites.AbstractSprite;
import com.leus.utils.CreatorOfSprites;

public final class CruciateFigure extends AbstractFigure {

    public CruciateFigure(int startPositionX, int startPositionY, SpriteFactory creatorSprites) {
        super(new AbstractSprite[5]);
        initializeSpritesInFigure(startPositionX, startPositionY, creatorSprites);
    }

    @Override
    public void rotate() {
        AbstractSprite topBall = topSpriteInFigure();
        AbstractSprite dawnBall = dawnSpriteInFigure();
        AbstractSprite leftBall = leftSpriteInFigure();
        AbstractSprite rightBall = rightSpriteInFigure();

        topBall.setCoordinateY(spritesInFigure[1].getCoordinateY());
        topBall.setCoordinateX(spritesInFigure[1].getCoordinateX() + Game.TILE_WIDTH);
        leftBall.setCoordinateX(spritesInFigure[1].getCoordinateX());
        leftBall.setCoordinateY(spritesInFigure[1].getCoordinateY() - Game.TILE_HEIGHT);
        rightBall.setCoordinateX(spritesInFigure[1].getCoordinateX());
        rightBall.setCoordinateY(spritesInFigure[1].getCoordinateY() + Game.TILE_HEIGHT);
        dawnBall.setCoordinateY(spritesInFigure[1].getCoordinateY());
        dawnBall.setCoordinateX(spritesInFigure[1].getCoordinateX() - Game.TILE_WIDTH);
    }

    @Override
    protected void initializeSpritesInFigure(int startPositionX, int startPositionY, SpriteFactory creatorSprites) {
        spritesInFigure[0] = CreatorOfSprites.createSprite(startPositionX, startPositionY, creatorSprites);
        spritesInFigure[1] = CreatorOfSprites.createSprite(startPositionX, startPositionY + Game.TILE_HEIGHT, creatorSprites);
        spritesInFigure[2] = CreatorOfSprites.createSprite(startPositionX - Game.TILE_WIDTH, startPositionY + Game.TILE_HEIGHT, creatorSprites);
        spritesInFigure[3] = CreatorOfSprites.createSprite(startPositionX + Game.TILE_WIDTH, startPositionY + Game.TILE_HEIGHT, creatorSprites);
        spritesInFigure[4] = CreatorOfSprites.createSprite(startPositionX, startPositionY + Game.TILE_HEIGHT * 2, creatorSprites);
    }

    private AbstractSprite topSpriteInFigure() {
        AbstractSprite result = spritesInFigure[0];
        for (AbstractSprite elem : spritesInFigure) {
            if (elem.getCoordinateY() < spritesInFigure[1].getCoordinateY()) {
                result = elem;
            }
        }

        return result;
    }

    private AbstractSprite dawnSpriteInFigure() {
        AbstractSprite result = spritesInFigure[0];
        for (AbstractSprite elem : spritesInFigure) {
            if (elem.getCoordinateY() > spritesInFigure[1].getCoordinateY()) {
                result = elem;
            }
        }

        return result;
    }

    private AbstractSprite leftSpriteInFigure() {
        AbstractSprite result = spritesInFigure[0];
        for (AbstractSprite elem : spritesInFigure) {
            if (elem.getCoordinateX() < spritesInFigure[1].getCoordinateX()) {
                result = elem;
            }
        }

        return result;
    }

    private AbstractSprite rightSpriteInFigure() {
        AbstractSprite result = spritesInFigure[0];
        for (AbstractSprite elem : spritesInFigure) {
            if (elem.getCoordinateX() > spritesInFigure[1].getCoordinateX()) {
                result = elem;
            }
        }

        return result;
    }
}
