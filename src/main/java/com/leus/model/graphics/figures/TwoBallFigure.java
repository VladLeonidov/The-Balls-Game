package com.leus.model.graphics.figures;

import com.leus.model.factories.RandBallFactory;
import com.leus.model.fields.GameField;
import com.leus.model.graphics.sprites.Sprite;
import com.leus.utils.Creator;

public class TwoBallFigure extends Figure {

    public TwoBallFigure() {
        super();
        spriteFactory = new RandBallFactory();
        spritesInFigure[0] = Creator.createBall(START_POSITION_X, START_POSITION_Y, spriteFactory);
        spritesInFigure[1] = Creator.createBall(START_POSITION_X + GameField.TILE_WIDTH, START_POSITION_Y, spriteFactory);
    }

    public void rotate() {
        //TODO: need normal algorithm for rotate a figure
    }

    public void moveLeft() {
        setFigureCoordX(getFigureCoordX() - GameField.TILE_WIDTH / 2);
        spritesInFigure[0].setX(getFigureCoordX());
        spritesInFigure[1].setX(getFigureCoordX() + GameField.TILE_WIDTH);
    }

    public void moveRight() {
        setFigureCoordX(getFigureCoordX() + GameField.TILE_WIDTH / 2);
        spritesInFigure[0].setX(getFigureCoordX());
        spritesInFigure[1].setX(getFigureCoordX() + GameField.TILE_WIDTH);
    }

    public void moveDownFigure() {
        for (Sprite sprite : spritesInFigure) {
            sprite.moveDown();
        }

        //setFigureCoordY(spritesInFigure[0].getY());
    }
}
