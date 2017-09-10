package com.leus.model.menus;

import com.leus.model.menuItems.Button;
import com.leus.model.menuItems.Cursor;
import com.leus.model.menuItems.Logo;
import com.leus.view.displays.Display;

import java.awt.*;

public class MainMenu extends AbstractMenu {
    private static final String VERSION = "1.35 Pre-Alpha";
    private static final String AUTHOR = "Created by Leus";
    private Logo mainLogo;

    public MainMenu(Image backGround, Logo mainLogo, Cursor cursor, Button... buttons) {
        super(backGround, cursor, buttons);
        this.mainLogo = mainLogo;
        setActive(true);
        cursor.setActive(true);
    }

    public void paint(Graphics g) {
        g.drawImage(getBackGround(), 0, 0, Display.getWidthWindow(), Display.getHeightWindow(), null);
        mainLogo.paint(g);
        getCursor().paint(g);
        for (Button elem : getButtons()) {
            elem.paint(g);
        }
        g.setColor(Color.BLACK);
        g.drawString(VERSION, 15, Display.getHeightWindow() - 15);
        g.drawString(AUTHOR, Display.getWidthWindow() - 100, Display.getHeightWindow() - 15);
    }

    public void clear() {
        getBackGround().flush();
        mainLogo.clear();
        getCursor().clear();
        for (Button elem : getButtons()) {
            elem.clear();
        }
    }
}
