package com.leus.view.panels;

import com.leus.model.GameField;
import com.leus.model.GameMenu;
import com.leus.model.graphics.figures.AbstractFigure;
import com.leus.model.graphics.sprites.AbstractSprite;
import com.leus.model.service.scores.ScoreManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private static AbstractSprite[][] gameFieldMatrix = GameField.getGameFieldMatrix();
    private static boolean menuRender = true;

    private GameField gameField;
    private GameMenu menu;
    private AbstractFigure figure;

    public GamePanel(GameField gameField, GameMenu menu) {
        this.gameField = gameField;
        this.menu = menu;
    }

    public static boolean isMenuRender() {
        return menuRender;
    }

    public static void setMenuRender(boolean menuRender) {
        GamePanel.menuRender = menuRender;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (menuRender) {
            menu.paintMenu(g);
        } else {
            figure = gameField.getFigure();
            paintFigure(g);
            paintBallsOnField(g);
            ScoreManager.drawScore(g);
        }
    }

    private void paintFigure(Graphics g) {
        figure.paint(g);
    }

    private void paintBallsOnField(Graphics g) {
        for (AbstractSprite[] line : gameFieldMatrix) {
            for (AbstractSprite elem : line) {
                if (elem != null) {
                    elem.paint(g);
                }
            }
        }
    }
}
