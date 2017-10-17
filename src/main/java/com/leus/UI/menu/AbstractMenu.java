package com.leus.UI.menu;

import com.leus.UI.menuItem.ButtonListener;
import com.leus.business.DeactivateListener;
import com.leus.UI.menuItem.Cursor;
import com.leus.UI.menuItem.MenuItem;
import com.leus.view.Display;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class AbstractMenu implements DeactivateListener {
    private final List<DeactivateListener> listeners = new ArrayList<>();
    private final Set<MenuItem> menuItems = new HashSet<>();
    private final Image backGround;
    private Cursor cursor;
    private boolean active;

    public AbstractMenu(Image backGround) {
        this.backGround = backGround;
    }

    public AbstractMenu(Image backGround, Cursor cursor) {
        this.backGround = backGround;
        this.cursor = cursor;
    }

    public Cursor getCursor() {
        return cursor;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void addMenuItem(MenuItem item) {
        menuItems.add(item);
    }

    public void removeItem(MenuItem item) {
        menuItems.remove(item);
    }

    public void addListener(DeactivateListener listener) {
        listeners.add(listener);
    }

    public void removeListener(DeactivateListener listener) {
        listeners.remove(listener);
    }

    public void paint(Graphics g) {
        if (backGround != null) {
            g.drawImage(backGround, 0, 0, Display.WIDTH_WINDOW, Display.HEIGHT_WINDOW, null);
        }

        for (MenuItem item : menuItems) {
            item.paint(g);
        }

        cursor.paint(g);
    }

    public void clear() {
        backGround.flush();
        for (MenuItem item : menuItems) {
            item.clear();
        }
    }

    @Override
    public String toString() {
        return  "Menu{" +
                "menuItems" + menuItems.toString() +
                ", active=" + active + '}';
    }

    @Override
    public void deactivate() {
        active = false;
        if (cursor != null) {
            cursor.setActive(false);
        }
    }

    public class ButtonListenerImpl implements ButtonListener {
        @Override
        public void onEvent() {
            for (DeactivateListener listener : listeners) {
                listener.deactivate();
            }

            if (!active) {
                active = true;
            }
            if (cursor != null && !cursor.isActive()) {
                cursor.setActive(true);
            }
        }
    }
}
