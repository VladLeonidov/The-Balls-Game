package com.leus.run;

import com.leus.model.GameField;
import com.leus.model.factories.controllerFactories.BallsControllerFactory;
import com.leus.model.factories.figureFactories.TwoBallFigureFactory;
import com.leus.model.service.CleanerBalls;
import com.leus.model.service.FieldManager;
import com.leus.view.GameFrame;
import com.leus.view.panels.GamePanel;
import javax.swing.JFrame;

public class Runner {
    public static void main(String[] args) {
        GameFrame gameFrame = GameFrame.getGameFrame(new GameField(new GamePanel(), new FieldManager(new CleanerBalls()), new TwoBallFigureFactory()), new JFrame(),"Balls");
        gameFrame.addKeyController(new BallsControllerFactory());
        gameFrame.createFrame();
    }
}
