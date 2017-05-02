package com.leus.panels;

import com.leus.enums.PathsToResources;
import com.leus.utils.ImgLoader;

import javax.swing.*;
import java.awt.*;

import static com.leus.displays.GameFrame.FIELD_DX;
import static com.leus.displays.GameFrame.WIDTH_GAME_FRAME;

public class MenuPanel extends JPanel {
    private static final int POSITION_LOGO_X = WIDTH_GAME_FRAME + FIELD_DX + 50;
    private static final int POSITION_LOGO_Y = 70;
    private static Image logo = ImgLoader.getImage(PathsToResources.LOGO.getPath());

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        paintLogo(g);
    }

    private void paintLogo(Graphics g) {
        g.drawImage(logo, POSITION_LOGO_X, POSITION_LOGO_Y, null);
    }
}
