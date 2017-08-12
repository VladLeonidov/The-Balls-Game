package com.leus.model.factories.controllerFactories;

import com.leus.controllers.BallsKeyController;

public class BallsKeyControllerFactory implements KeyControllerFactory {

    public BallsKeyController createKeyController() {
        return new BallsKeyController();
    }
}
