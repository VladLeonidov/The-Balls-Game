package com.leus.model.factories.controllerFactories;

import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;

public class BallsControllerFactory implements ControllerAbstractFactory {

    public KeyAdapter createKeyController() {
        return new BallsKeyControllerFactory().createKeyController();
    }

    public MouseAdapter createMouseController() {
        return new BallsMouseControllerFactory().createMouseController();
    }
}
