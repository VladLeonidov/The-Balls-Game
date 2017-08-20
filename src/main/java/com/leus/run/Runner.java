package com.leus.run;

import com.leus.controllers.BallsKeyController;
import com.leus.model.GameField;
import com.leus.model.factories.figureFactories.ThreeBallFigureFactory;
import com.leus.model.factories.figureFactories.TwoBallFigureFactory;
import com.leus.model.factories.spriteFactories.RandBallFactory;
import com.leus.model.service.FieldManager;
import com.leus.view.displays.PcDisplay;
import com.leus.view.panels.GamePanel;

public class Runner {
    public static void main(String[] args) {
        int figureStartPositionX = PcDisplay.getWidthWindow() / 2 - GameField.TILE_WIDTH;
        int figureStartPositionY = 0;

        PcDisplay pcDisplay = PcDisplay.getDisplay();
        GameField gameField = new GameField(new FieldManager(), new TwoBallFigureFactory(figureStartPositionX, figureStartPositionY, new RandBallFactory()), new ThreeBallFigureFactory(figureStartPositionX, figureStartPositionY, new RandBallFactory()));
        BallsKeyController controller = new BallsKeyController(gameField, pcDisplay);
        pcDisplay.create(new GamePanel(gameField),8, 16, "Balls");
        pcDisplay.addKeyController(controller);
        gameField.startGameProcess(controller.getWindow().getCanvas());
    }
}
