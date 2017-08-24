package com.leus.model.graphics.sprites.menuItems.buttons;

import com.leus.model.GameField;
import com.leus.model.GameMenu;
import com.leus.view.displays.PcDisplay;

import java.awt.*;

public class StartButton extends AbstractButton {

    private GameField gameField;
    private PcDisplay display;

    public StartButton(Image buttonImg, int coordinateX, int coordinateY, int width, int height, GameField gameField, PcDisplay display) {
        super(buttonImg, coordinateX, coordinateY, width, height);
        this.gameField = gameField;
        this.display = display;
    }

    @Override
    public void push() {
        GameMenu.setFocusMenu(false);
        display.getCanvas().repaint();
    }
}
