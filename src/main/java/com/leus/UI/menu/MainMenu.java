package com.leus.UI.menu;

import com.leus.UI.menuItem.Cursor;
import com.leus.view.Display;

import java.awt.*;

public class MainMenu extends AbstractMenu {
    private static String gameVersion = "1.01 release";
    private static String authorOfGame = "Created by Leus";

    public MainMenu(Image backGround) {
        super(backGround);
        setActive(true);
    }

    public MainMenu(Image backGround, Cursor cursor) {
        super(backGround, cursor);
        setActive(true);
        getCursor().setActive(true);
    }

    public static void setGameVersion(String gameVersion) {
        MainMenu.gameVersion = gameVersion;
    }

    public static void setAuthorOfGame(String authorOfGame) {
        MainMenu.authorOfGame = authorOfGame;
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.drawString(gameVersion, 15, Display.HEIGHT_WINDOW - 15);
        g.drawString(authorOfGame, Display.WIDTH_WINDOW - 100, Display.HEIGHT_WINDOW - 15);
    }
}
