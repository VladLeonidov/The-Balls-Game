package com.leus.controller;

import java.awt.event.KeyEvent;

public interface KeyControllerListener {

    void onEvent(KeyEvent e);

    boolean isActive();
}
