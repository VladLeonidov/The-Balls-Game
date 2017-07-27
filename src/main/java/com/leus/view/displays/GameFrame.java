package com.leus.view.displays;

import com.leus.controller.BallsKeyController;
import com.leus.model.fields.GameField;

import javax.swing.*;
import java.awt.*;

import static com.leus.model.fields.GameField.TILE_HEIGHT;
import static com.leus.model.fields.GameField.TILE_WIDTH;

public final class GameFrame {

    public static final int FIELD_WIDTH_IN_TILE = 6;
    public static final int FIELD_HEIGHT_IN_TILE = 12;
    public static final int WIDTH_GAME_FRAME = TILE_WIDTH * FIELD_WIDTH_IN_TILE;
    public static final int HEIGHT_GAME_FRAME = TILE_HEIGHT * FIELD_HEIGHT_IN_TILE;
    public static final int FIELD_ADD_TO_WIDTH = 15;
    public static final int FIELD_ADD_TO_HEIGHT = 64;

    private JFrame mainFrame;
    private String title;
    private GameField gameField = GameField.getGameFieldInstance();
    private JPanel gamePanel = gameField.getGamePanel();

    public GameFrame(JFrame mainFrame, String title) {
        this.mainFrame = mainFrame;
        this.title = title;
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }

    public void setMainFrame(JFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void createFrame() {
        mainFrame.setTitle(title);
        mainFrame.setSize(WIDTH_GAME_FRAME + FIELD_ADD_TO_WIDTH, HEIGHT_GAME_FRAME + FIELD_ADD_TO_HEIGHT);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.addKeyListener(new BallsKeyController());
        gamePanel.setBackground(Color.white);
        mainFrame.getContentPane().add(gamePanel);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);
        gameField.startGameProcess();
    }
}
