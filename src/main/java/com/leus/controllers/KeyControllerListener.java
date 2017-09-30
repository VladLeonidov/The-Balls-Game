package com.leus.controllers;

import java.awt.event.KeyEvent;

/**
 * It is the interface-listener for {@link KeyController}, implement it if you want get event from {@link KeyController}.
 * @see KeyController
 */
public interface KeyControllerListener {

    /**
     * The event received from KeyController.
     * @param e key code of a keyboard
     * @see KeyEvent
     */
    void onEvent(KeyEvent e);

    /**
     * The one who implements this interface must specify if it is active.
     * @return true if active and false if not active
     */
    boolean isActive();
}
