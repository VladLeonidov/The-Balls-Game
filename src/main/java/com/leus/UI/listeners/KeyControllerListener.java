package com.leus.UI.listeners;

import java.awt.event.KeyEvent;

public interface KeyControllerListener {
    void onEvent(KeyEvent e);

    boolean isActive();
}
