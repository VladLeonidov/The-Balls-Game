package com.leus.panels;

import com.leus.game.fields.GameField;
import com.leus.game.graphics.Ball;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class GamePanel extends JPanel {

    /*private static final int POSITION_LOGO_X = 25;
    private static final int POSITION_LOGO_Y = 70;
    private static Image logo = ImgLoader.getImage(PathsToResources.LOGO.getPath());
    */
    private Map<Integer, Ball> ballsOnField = GameField.getBallsOnField();

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //paintLogo(g);
        paintGame(g);
    }

    /*private void paintLogo(Graphics g) {
        g.drawImage(logo, POSITION_LOGO_X, POSITION_LOGO_Y, null);
    }*/

    private void paintGame(Graphics g) {

        for (Map.Entry<Integer, Ball> entry : ballsOnField.entrySet()) {
            entry.getValue().paint(g);
        }
    }
}
