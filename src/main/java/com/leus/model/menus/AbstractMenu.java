package com.leus.model.menus;

import com.leus.model.listeners.DeactivateListener;
import com.leus.model.listeners.ButtonListener;
import com.leus.model.menuItems.Button;
import com.leus.model.menuItems.Cursor;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractMenu implements DeactivateListener {
    private final List<DeactivateListener> listeners = new ArrayList<>();
    private final Image backGround;
    private final Cursor cursor;
    private final Button[] buttons;
    private boolean active;

    public AbstractMenu(Image backGround, Cursor cursor, Button... buttons) {
        this.backGround = backGround;
        this.cursor = cursor;
        this.buttons = buttons;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Image getBackGround() {
        return backGround;
    }

    public Button[] getButtons() {
        return buttons;
    }

    public Cursor getCursor() {
        return cursor;
    }

    public void addListener(DeactivateListener listener) {
        listeners.add(listener);
    }

    public void removeListener(DeactivateListener listener) {
        listeners.remove(listener);
    }

    public abstract void paint(Graphics g);

    public abstract void clear();

    @Override
    public String toString() {
        return "Menu{" + "cursor=" + cursor +
                ", buttons=" + Arrays.toString(buttons) +
                ", deactivate=" + active + '}';
    }

    @Override
    public void deactivate() {
        active = false;
        cursor.setActive(false);
        //System.out.println(this + " deactivate");
    }

    public class ButtonListenerImpl implements ButtonListener {
        @Override
        public void onEvent() {
            for (DeactivateListener listener : listeners) {
                listener.deactivate();
            }
            active = true;
            cursor.setActive(true);
            //System.out.println(AbstractMenu.this.toString() + " deactivate");
        }
    }
}
