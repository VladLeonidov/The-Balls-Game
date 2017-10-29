package com.leus.view;

import com.leus.UI.menu.AbstractMenu;
import com.leus.business.Game;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private Game game;
    private AbstractMenu[] menus;

    public GamePanel(Game game, AbstractMenu... menus) {
        if (game == null) {
            throw new NullPointerException("Game can't be null");
        }

        this.game = game;
        this.menus = menus;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (game.isActive()) {
            game.paint(g);
            repaint();
        } else {
            if (menus != null && menus.length != 0) {
                for (AbstractMenu menu : menus) {
                    if (menu.isActive()) {
                        menu.paint(g);
                    }
                }
            }
        }
    }
}
