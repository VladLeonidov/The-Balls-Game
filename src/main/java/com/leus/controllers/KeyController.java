package com.leus.controllers;

import com.leus.display.Display;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * The class extends KeyAdapter and inform listeners about a gotten event from a keyboard.
 * @see KeyAdapter
 */
public class KeyController extends KeyAdapter {

    private Display window;
    private List<KeyControllerListener> listeners = new ArrayList<>();

    /**
     * Creates a new KeyController with specified Display.
     * @param window the game window for repainting gotten event.
     */
    public KeyController(Display window) {
        this.window = window;
    }

    /**
     * Add a listener to the list listeners.
     * @param listener
     */
    public void addListener(KeyControllerListener listener) {
        listeners.add(listener);
    }

    /**
     * Remove a listener to the list listeners.
     * @param listener
     */
    public void removeListener(KeyControllerListener listener) {
        listeners.remove(listener);
    }

    /**
     * Method inform listeners about a gotten event from keyboard,
     * event will get only the active listener, repainting the game window.
     * @param e key code of a keyboard
     * @see KeyControllerListener
     */
    @Override
    public void keyPressed(KeyEvent e) {
        for (KeyControllerListener listener : listeners) {
            if (listener.isActive()) {
                listener.onEvent(e);
            }
        }

        window.repaint();
    }
}
