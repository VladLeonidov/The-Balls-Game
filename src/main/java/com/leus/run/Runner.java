package com.leus.run;


import com.leus.model.GameField;
import com.leus.model.factories.figuresFactories.TwoBallFigureFactory;
import com.leus.model.service.CleanerBalls;
import com.leus.model.service.FieldManager;
import com.leus.view.GameFrame;
import com.leus.view.panels.GamePanel;

import javax.swing.*;

public class Runner {
    public static void main(String[] args) {
        GameFrame.getGameFrame(new GameField(new GamePanel(), new FieldManager(new CleanerBalls()), new TwoBallFigureFactory()), new JFrame(), "Balls").createFrame();
    }
}
