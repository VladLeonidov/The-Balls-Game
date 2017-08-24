package com.leus.model.graphics.sprites.menuItems.buttons;

import com.leus.model.GameMenu;
import com.leus.model.ScoreMenu;

import javax.swing.*;
import java.awt.*;

public class ScoreButton extends AbstractButton {

    private JPanel panel;

    public ScoreButton(Image buttonImg, int coordinateX, int coordinateY, int width, int height, JPanel panel) {
        super(buttonImg, coordinateX, coordinateY, width, height);
        this.panel = panel;
    }

    @Override
    public void push() {
        GameMenu.setFocusMenu(false);
        ScoreMenu.setFocusMenu(true);
    }
}
