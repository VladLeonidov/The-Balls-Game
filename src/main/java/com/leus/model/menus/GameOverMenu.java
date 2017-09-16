package com.leus.model.menus;

import com.leus.model.listeners.GameOverListener;
import com.leus.model.menuItems.Button;
import com.leus.model.menuItems.Cursor;
import com.leus.model.menuItems.Logo;
import com.leus.model.service.scores.ScoreManager;
import com.leus.view.displays.Display;

import java.awt.*;

public class GameOverMenu extends AbstractMenu {

    private Logo gameOverLogo;
    private int scorePositionX = ScoreManager.DEFAULT_POSITION_SCORE_X;
    private int scorePositionY = Display.getHeightWindow() / 2;
    private Font fontForScore = new Font("Impact", Font.BOLD, 26);

    public GameOverMenu(Image backGround, Logo gameOverLogo, Cursor cursor, Button... buttons) {
        super(backGround, cursor, buttons);
        this.gameOverLogo = gameOverLogo;
    }

    public void setScorePositionX(int scorePositionX) {
        this.scorePositionX = scorePositionX;
    }

    public void setScorePositionY(int scorePositionY) {
        this.scorePositionY = scorePositionY;
    }

    public void setFontForScore(Font fontForScore) {
        this.fontForScore = fontForScore;
    }

    public void paint(Graphics g) {
        g.drawImage(getBackGround(), 0, 0, Display.getWidthWindow(), Display.getHeightWindow(), null);
        gameOverLogo.paint(g);
        for (Button currentButton : getButtons()) {
            currentButton.paint(g);
        }
        getCursor().paint(g);
        paintingScore(g);
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

    private void paintingScore(Graphics g) {
        g.setColor(Color.BLACK);
        ScoreManager.drawScore(g, scorePositionX, scorePositionY, fontForScore, null);
    }

    public class GameOverListenerImpl implements GameOverListener {
        @Override
        public void onEvent() {
            setActive(true);
            getCursor().setActive(true);
        }
    }
}
