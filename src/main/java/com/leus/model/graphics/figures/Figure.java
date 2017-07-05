package com.leus.model.graphics.figures;


import com.leus.model.factories.SpriteFactory;
import com.leus.model.fields.GameField;
import com.leus.model.graphics.sprites.Sprite;
import com.leus.view.displays.GameFrame;

public abstract class Figure {

    public static int START_POSITION_X = (GameFrame.WIDTH_GAME_FRAME + GameFrame.FIELD_DX) / 2 - GameField.TILE_WIDTH - 2;
    public static int START_POSITION_Y = 0;

    private int figureCoordX = START_POSITION_X;
    private int figureCoordY = START_POSITION_Y;

    protected SpriteFactory spriteFactory;

    protected Sprite[] spritesInFigure;

    protected static Sprite[][] spritesOnField = GameField.getSpritesOnField();

    public Figure() {
        this(new Sprite[2]);
    }

    public Figure(Sprite[] spritesInFigure) {
        this.spritesInFigure = spritesInFigure;
    }

    public Sprite[] getSpritesInFigure() {
        return spritesInFigure;
    }

    public int getFigureCoordX() {
        return figureCoordX;
    }

    public void setFigureCoordX(int figureCoordX) {
        this.figureCoordX = figureCoordX;
    }

    public int getFigureCoordY() {
        return figureCoordY;
    }

    public void setFigureCoordY(int figureCoordY) {
        this.figureCoordY = figureCoordY;
    }

    public abstract void rotate();

    public abstract void moveLeft();

    public abstract void moveRight();

    public abstract void moveDownFigure();

    public void fastMoveDown() {
        for (Sprite sprite : spritesInFigure) {
            while (!sprite.isFrozen()) {
                sprite.moveDown();
            }
        }
    }
}
