package com.leus.view.panels;

import com.leus.model.Game;
import com.leus.model.menus.AbstractMenu;
import com.leus.model.service.scores.ScoreManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private Game game;
    private AbstractMenu[] menus;

    public GamePanel(Game game, AbstractMenu... menus) {
        this.game = game;
        this.menus = menus;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (game.isActive()) {
            System.out.println("Painting game...");
            game.paint(g);
            ScoreManager.drawScore(g);
            //repaint();
        } else {
            for (AbstractMenu menu : menus) {
                if (menu.isActive()) {
                    menu.paint(g);
                }
            }
        }
    }
}
