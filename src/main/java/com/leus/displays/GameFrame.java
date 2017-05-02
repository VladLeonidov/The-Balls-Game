package com.leus.displays;

import com.leus.game.controllers.BallsController;
import com.leus.game.fields.GameField;
import com.leus.panels.GamePanel;

import javax.swing.*;
import java.awt.*;

import static com.leus.game.fields.GameField.TILE_HEIGHT;
import static com.leus.game.fields.GameField.TILE_WIDTH;

public final class GameFrame {

    public static final int FIELD_WIDTH_IN_TILE = 6;
    public static final int FIELD_HEIGHT_IN_TILE = 12;
    public static final int WIDTH_GAME_FRAME = TILE_WIDTH * FIELD_WIDTH_IN_TILE;
    public static final int HEIGHT_GAME_FRAME = TILE_HEIGHT * FIELD_HEIGHT_IN_TILE;
    //public static final int WIDTH_MENU_FRAME = 150;
    public static final int FIELD_DX = 15;
    public static final int FIELD_DY = 64;

    private static JFrame mFrame;
    private static GameField gameField = new GameField();
    private static GamePanel gamePanel;
    //private static MenuPanel menuPanel;
    private static String title = "Balls";

    public static GameField getGameField() {
        return gameField;
    }

    private GameFrame() {
    }

    public static void createFrame() {
        mFrame = new JFrame(title);
        mFrame.setSize(WIDTH_GAME_FRAME + FIELD_DX, HEIGHT_GAME_FRAME + FIELD_DY);
        mFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mFrame.addKeyListener(new BallsController());
        gamePanel = gameField.getGamePanel();
        gamePanel.setBackground(Color.white);
        //menuPanel = new MenuPanel();
        //menuPanel.setBackground(Color.white);
        //mFrame.getContentPane().add(BorderLayout.EAST, menuPanel);
        mFrame.getContentPane().add(gamePanel);
        //menuPanel.add(new StartButton());
        mFrame.setLocationRelativeTo(null);
        mFrame.setResizable(false);
        mFrame.setVisible(true);
        new Thread(gameField).start();
    }

    /*private static class StartButton extends JButton implements ActionListener {
        public StartButton() {
            setSize(150, 100);
        }

        public void actionPerformed(ActionEvent e) {
            start();
        }

        private void start() {
            if (gameField.isGameOver() != true) {

            }
        }
    }*/
}
