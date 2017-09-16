package com.leus.model.menus;

import com.leus.model.menuItems.Button;
import com.leus.model.menuItems.Cursor;
import com.leus.model.menuItems.Logo;
import com.leus.view.displays.Display;

import java.awt.*;

public class MainMenu extends AbstractMenu {
    private static String version = "0.91 Alpha";
    private static String author = "Created by Leus";
    private Logo mainLogo;

    public MainMenu(Image backGround, Logo mainLogo, Cursor cursor, Button... buttons) {
        super(backGround, cursor, buttons);
        this.mainLogo = mainLogo;
        setActive(true);
        cursor.setActive(true);
    }

    public static void setVersion(String version) {
        MainMenu.version = version;
    }

    public static void setAuthor(String author) {
        MainMenu.author = author;
    }

    public void paint(Graphics g) {
        g.drawImage(getBackGround(), 0, 0, Display.getWidthWindow(), Display.getHeightWindow(), null);
        mainLogo.paint(g);
        getCursor().paint(g);
        for (Button elem : getButtons()) {
            elem.paint(g);
        }
        g.setColor(Color.BLACK);
        g.drawString(version, 15, Display.getHeightWindow() - 15);
        g.drawString(author, Display.getWidthWindow() - 100, Display.getHeightWindow() - 15);
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
