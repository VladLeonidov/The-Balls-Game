package com.leus.run;

import com.leus.controllers.BallsKeyController;
import com.leus.model.GameField;
import com.leus.model.factories.figureFactories.ThreeBallFigureFactory;
import com.leus.model.factories.figureFactories.TwoBallFigureFactory;
import com.leus.model.factories.spriteFactories.RandBallFactory;
import com.leus.model.service.FieldManager;
import com.leus.model.service.cleaners.CleanerBalls;
import com.leus.view.PcGameFrameBuilder;
import com.leus.view.panels.GamePanel;

public class Runner {
    public static void main(String[] args) {
        int startPositionX = PcGameFrameBuilder.getWidthGameFrame() / 2 - GameField.TILE_WIDTH;
        int startPositionY = 0;

        BallsKeyController controller = new BallsKeyController(GameField.getGameField(new FieldManager(new CleanerBalls()), new TwoBallFigureFactory(startPositionX, startPositionY, new RandBallFactory()), new ThreeBallFigureFactory(startPositionX, startPositionY, new RandBallFactory())), new GamePanel());
        PcGameFrameBuilder pcGameFrameBuilder = new PcGameFrameBuilder();
        pcGameFrameBuilder.addKeyController(controller);
        pcGameFrameBuilder.addPanel(controller.getGamePanel());
        pcGameFrameBuilder.buildPcGameFrame();
    }
}
