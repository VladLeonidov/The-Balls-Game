package com.leus.UI.menuItems;

import com.leus.controllers.KeyControllerListener;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Cursor extends MenuItem {

    private final int speed;
    private final Image cursorImg;
    private boolean active;
    private List<CursorListener> listeners = new ArrayList<>();
    private CursorListener currentListener;
    private int indexListeners;

    public Cursor(Image cursorImg, int coordinateX, int coordinateY, int width, int height, int speed) {
        super(coordinateX, coordinateY, width, height);
        this.cursorImg = cursorImg;
        this.speed = speed;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void addListener(CursorListener listener) {
        listeners.add(listener);
        if (currentListener == null) {
            currentListener = listener;
        }
    }

    public void removeListener(CursorListener listener) {
        listeners.remove(listener);
    }

    private void moveUp() {
        if (listeners.isEmpty()) {
            throw new IllegalStateException("No listeners");
        }

        if (indexListeners > 0) {
            setCoordinateY(getCoordinateY() - speed);
            currentListener = listeners.get(--indexListeners);
        }
    }

    private void moveDown() {
        if (listeners.isEmpty()) {
            throw new IllegalStateException("No listeners");
        }

        if (indexListeners < listeners.size() - 1) {
            setCoordinateY(getCoordinateY() + speed);
            currentListener = listeners.get(++indexListeners);
        }
    }

    private void push() {
        if (listeners.isEmpty()) {
            throw new IllegalStateException("No listeners");
        }

        currentListener.onEvent();
    }

    public void paint(Graphics g) {
        g.drawImage(cursorImg, getCoordinateX(), getCoordinateY(), getWidth(), getHeight(), null);
    }

    public void clear() {
        cursorImg.flush();
    }

    @Override
    public String toString() {
        return "Cursor{x=" + getCoordinateX() + ", y=" + getCoordinateY() +
                ", width=" + getWidth() + ", height=" + getHeight() +
                ", speed=" + speed + ", active=" + active +
                ", focus=" + currentListener + "}";
    }

    @Override
    public int hashCode() {
        int result = getCoordinateX();
        result = 31 * result + getCoordinateX();
        result = 31 * result + getCoordinateY();
        result = 31 * result + getHeight();
        result = 31 * result + getWidth();
        result = 31 * result + speed;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Cursor)) {
            return false;
        }

        Cursor that = (Cursor) obj;

        return  this.getWidth() == that.getWidth() && this.getHeight() ==  that.getHeight() &&
                this.speed == that.speed && listeners.equals(that.listeners);
    }

    public class KeyControllerListenerImpl implements KeyControllerListener {

        @Override
        public void onEvent(KeyEvent e) {
            if (active) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    moveUp();
                }

                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    moveDown();
                }

                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    push();
                }
            }
        }

        @Override
        public boolean isActive() {
            return active;
        }

        @Override
        public int hashCode() {
            return Cursor.this.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            return Cursor.this.equals(obj);
        }

        @Override
        public String toString() {
            return Cursor.this.toString();
        }
    }
}
