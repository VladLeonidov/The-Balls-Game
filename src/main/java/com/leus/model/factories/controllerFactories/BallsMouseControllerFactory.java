package com.leus.model.factories.controllerFactories;

import com.leus.controllers.BallsMouseController;

public class BallsMouseControllerFactory implements MouseControllerFactory {

    public BallsMouseController createMouseController() {
        return new BallsMouseController();
    }
}
