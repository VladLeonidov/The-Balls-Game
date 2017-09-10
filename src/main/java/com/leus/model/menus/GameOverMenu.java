package com.leus.model.menus;

import com.leus.model.listeners.GameOverListener;
import com.leus.model.menuItems.Button;
import com.leus.model.menuItems.Cursor;
import com.leus.model.menuItems.Logo;
import com.leus.view.displays.Display;

import java.awt.*;

public class GameOverMenu extends AbstractMenu {

    private Logo gameOverLogo;

    public GameOverMenu(Image backGround, Logo gameOverLogo, Cursor cursor, Button... buttons) {
        super(backGround, cursor, buttons);
        this.gameOverLogo = gameOverLogo;
    }

    public void paint(Graphics g) {
        g.drawImage(getBackGround(), 0, 0, Display.getWidthWindow(), Display.getHeightWindow(), null);
        gameOverLogo.paint(g);

        for (Button currentButton : getButtons()) {
            currentButton.paint(g);
        }

        getCursor().paint(g);
    }

    @Override
    public void clear() {
        getBackGround().flush();
        gameOverLogo.clear();
        getCursor().clear();
        for (Button currentButton : getButtons()) {
            currentButton.clear();
        }
    }

    public class GameOverListenerImpl implements GameOverListener {
        @Override
        public void onEvent() {
            setActive(true);
            getCursor().setActive(true);
        }
    }
}
