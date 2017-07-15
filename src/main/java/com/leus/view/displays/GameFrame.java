package com.leus.view.displays;

import com.leus.controller.BallsKeyController;
import com.leus.model.fields.GameField;
import com.leus.view.panels.GamePanel;

import javax.swing.*;
import java.awt.*;

import static com.leus.model.fields.GameField.TILE_HEIGHT;
import static com.leus.model.fields.GameField.TILE_WIDTH;

public final class GameFrame {

    public static final int FIELD_WIDTH_IN_TILE = 6;
    public static final int FIELD_HEIGHT_IN_TILE = 12;
    public static final int WIDTH_GAME_FRAME = TILE_WIDTH * FIELD_WIDTH_IN_TILE;
    public static final int HEIGHT_GAME_FRAME = TILE_HEIGHT * FIELD_HEIGHT_IN_TILE;
    public static final int FIELD_DX = 15;
    public static final int FIELD_DY = 64;

    private static JFrame mFrame;
    private static GameField gameField = new GameField();
    private static GamePanel gamePanel;
    private static String title = "Balls";

    public static GameField getGameField() {
        return gameField;
    }

    public static void setGameField(GameField gameField) {
        gameField = gameField;
    }

    public static void createFrame() {
        mFrame = new JFrame(title);
        mFrame.setSize(WIDTH_GAME_FRAME + FIELD_DX, HEIGHT_GAME_FRAME + FIELD_DY);
        mFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mFrame.addKeyListener(new BallsKeyController());
        gamePanel = gameField.getGamePanel();
        gamePanel.setBackground(Color.white);
        mFrame.getContentPane().add(gamePanel);
        mFrame.setLocationRelativeTo(null);
        mFrame.setResizable(false);
        mFrame.setVisible(true);
        new Thread(gameField).start();
    }
}
