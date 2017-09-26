package com.leus.UI.menus;

import com.leus.UI.menuItems.Cursor;
import com.leus.view.displays.Display;

import java.awt.*;

public class MainMenu extends AbstractMenu {
    private static String version = "0.97 Alpha";
    private static String author = "Created by Leus";

    public MainMenu(Image backGround) {
        super(backGround);
        setActive(true);
    }

    public MainMenu(Image backGround, Cursor cursor) {
        super(backGround, cursor);
        setActive(true);
        getCursor().setActive(true);
    }

    public static void setVersion(String version) {
        MainMenu.version = version;
    }

    public static void setAuthor(String author) {
        MainMenu.author = author;
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.drawString(version, 15, Display.getHeightWindow() - 15);
        g.drawString(author, Display.getWidthWindow() - 100, Display.getHeightWindow() - 15);
    }
}
