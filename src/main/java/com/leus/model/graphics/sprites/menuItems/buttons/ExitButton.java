package com.leus.model.graphics.sprites.menuItems.buttons;

import com.leus.view.displays.PcDisplay;

import java.awt.*;

public class ExitButton extends AbstractButton {

    PcDisplay display;

    public ExitButton(Image buttonImg, int coordinateX, int coordinateY, int width, int height, PcDisplay display) {
        super(buttonImg, coordinateX, coordinateY, width, height);
        this.display = display;
    }

    @Override
    public void push() {
        display.destroy();
        System.exit(0);
    }
}
