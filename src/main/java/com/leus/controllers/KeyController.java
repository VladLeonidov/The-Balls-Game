package com.leus.controllers;

import com.leus.display.Display;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class KeyController extends KeyAdapter {

    private Display window;
    private List<KeyControllerListener> listeners = new ArrayList<>();

    public KeyController(Display window) {
        this.window = window;
    }

    public void addListener(KeyControllerListener listener) {
        listeners.add(listener);
    }

    public void removeListener(KeyControllerListener listener) {
        listeners.remove(listener);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        for (KeyControllerListener listener : listeners) {
            if (listener.isActive()) {
                listener.onEvent(e);
                break;
            }
        }

        window.repaint();
    }
}
