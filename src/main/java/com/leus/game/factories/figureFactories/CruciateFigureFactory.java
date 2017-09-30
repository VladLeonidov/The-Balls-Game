package com.leus.game.factories.figureFactories;

import com.leus.game.factories.spriteFactories.SpriteFactory;
import com.leus.game.graphics.figures.AbstractFigure;
import com.leus.game.graphics.figures.CruciateFigure;

public class CruciateFigureFactory implements FigureFactory {
    private int startPositionX;
    private int startPositionY;
    private SpriteFactory creatorSprites;

    public CruciateFigureFactory(int startPositionX, int startPositionY, SpriteFactory creatorSprites) {
        this.startPositionX = startPositionX;
        this.startPositionY = startPositionY;
        this.creatorSprites = creatorSprites;
    }

    @Override
    public AbstractFigure createFigure() {
        return new CruciateFigure(startPositionX, startPositionY, creatorSprites);
    }
}
