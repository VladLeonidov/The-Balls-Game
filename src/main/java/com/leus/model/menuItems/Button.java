package com.leus.model.menuItems;

import com.leus.model.listeners.ButtonListener;
import com.leus.model.listeners.CursorListener;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Button extends MenuItem {
    private List<ButtonListener> listeners = new ArrayList<>();
    private final Image buttonImg;

    public Button(Image buttonImg, int coordinateX, int coordinateY, int width, int height) {
        super(coordinateX, coordinateY, width, height);
        this.buttonImg = buttonImg;
    }

    public void addListener(ButtonListener listener) {
        listeners.add(listener);
    }

    public void removeListener(ButtonListener listener) {
        listeners.remove(listener);
    }

    public void push() {
        for (ButtonListener listener : listeners) {
            //System.out.println(this + " pushed");
            listener.onEvent();
        }
    }

    public void paint(Graphics g) {
        g.drawImage(buttonImg, getCoordinateX(), getCoordinateY(), getWidth(), getHeight(), null);
    }

    public void clear() {
        buttonImg.flush();
    }

    @Override
    public String toString() {
        return "Button(x=" + getCoordinateX() + ", y=" + getCoordinateY() +
                ", height=" + getHeight() + ", width=" + getWidth() + ")";
    }

    public class CursorListenerImpl implements CursorListener {
        @Override
        public void onEvent() {
            push();
        }

        @Override
        public String toString() {
            return Button.this.toString();
        }
    }
}
