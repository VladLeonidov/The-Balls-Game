package com.leus.model;

import com.leus.model.graphics.sprites.menuItems.buttons.AbstractButton;
import com.leus.model.graphics.sprites.menuItems.Logo;
import com.leus.model.graphics.sprites.menuItems.MenuCursor;
import com.leus.view.displays.PcDisplay;

import java.awt.*;

public class GameMenu {
    private static final String VERSION = "1.01 Pre-Alpha";
    private static final String AUTHOR = "Created by Leus";
    private static boolean focusMenu = true;
    private Image backGround;
    private Logo logo;
    private MenuCursor cursor;
    private AbstractButton[] menuButtons;

    public GameMenu(Image backGround, Logo logo, MenuCursor cursor, AbstractButton... menuButtons) {
        this.backGround = backGround;
        this.logo = logo;
        this.cursor = cursor;
        this.menuButtons = menuButtons;
        cursor.setFocus(menuButtons[0]);
    }

    public MenuCursor getCursor() {
        return cursor;
    }

    public AbstractButton[] getMenuButtons() {
        return menuButtons;
    }

    public static boolean isFocusMenu() {
        return focusMenu;
    }

    public static void setFocusMenu(boolean focusMenu) {
        GameMenu.focusMenu = focusMenu;
    }

    public void paintMenu(Graphics g) {
        g.drawImage(backGround, 0, 0, PcDisplay.getWidthWindow(), PcDisplay.getHeightWindow(), null);
        logo.paint(g);
        cursor.paint(g);
        for (AbstractButton elem : menuButtons) {
            elem.paint(g);
        }
        g.setColor(Color.BLACK);
        g.drawString(VERSION, 15, PcDisplay.getHeightWindow() - 15);
        g.drawString(AUTHOR, PcDisplay.getWidthWindow() - 100, PcDisplay.getHeightWindow() - 15);
    }

    public void clear() {
        backGround.flush();
        logo.clear();
        cursor.clear();
        for (AbstractButton elem : menuButtons) {
            elem.clear();
        }
    }
}
