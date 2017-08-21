package com.leus.model;

import com.leus.model.graphics.sprites.menuItem.Button;
import com.leus.model.graphics.sprites.menuItem.Logo;
import com.leus.model.graphics.sprites.menuItem.MenuCursor;
import com.leus.model.service.scores.ScoreManager;
import com.leus.view.displays.PcDisplay;

import java.awt.*;

public class GameMenu {
    private Image backGround;
    private Logo logo;
    private MenuCursor cursor;
    private Button[] menuButtons;

    public GameMenu(Image backGround, Logo logo, MenuCursor cursor, Button... menuButtons) {
        this.backGround = backGround;
        this.logo = logo;
        this.cursor = cursor;
        this.menuButtons = menuButtons;
    }

    public void paintMenu(Graphics g) {
        g.drawImage(backGround, 0, 0, PcDisplay.getWidthWindow(), PcDisplay.getHeightWindow() + GameField.TILE_HEIGHT + ScoreManager.SIZE_FRAME_FOR_SCORE, null);
        logo.paint(g);
        cursor.paint(g);
        for (Button elem : menuButtons) {
            elem.paint(g);
        }
    }

    public void clear() {
        backGround.flush();
        logo.clear();
        cursor.clear();
        for (Button elem : menuButtons) {
            elem.clear();
        }
    }
}
