package com.leus.model.factories.controllerFactories;

import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;

public interface ControllerAbstractFactory {

    KeyAdapter createKeyController();

    MouseAdapter createMouseController();

    //TouchPadAdapter createTouchPadController(); In swing api is not touch pad api =(
}
