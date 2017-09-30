package com.leus.display.panels;

import com.leus.display.Display;
import com.leus.game.Game;
import com.leus.UI.menus.AbstractMenu;

import javax.swing.*;
import java.awt.*;

/**
 * Class-painter extends {@code JPanel}, painting game elements in a window.
 * @see JPanel
 * @see Display
 */
public class GamePanel extends JPanel {

    private Game game;
    private AbstractMenu[] menus;

    /**
     * Creates a new {@code GamePanel} whit the specified game class and menus.
     * @param game a main game class of the game. It can't be null
     * @param menus menus of a user interface game
     * @throws NullPointerException if the game class is null.
     * @see Game
     * @see AbstractMenu
     */
    public GamePanel(Game game, AbstractMenu... menus) {
        if (game == null) {
            throw new NullPointerException("The game class can't be null");
        }
        this.game = game;
        this.menus = menus;
    }

    /**
     * Painting game class and menus, if they are, depending what is active now.
     */
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
                repaint();
            }
        }
    }
}
