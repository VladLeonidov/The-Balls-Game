package com.leus.view.displays;

import com.leus.model.Game;
import com.leus.model.service.scores.ScoreManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;

public final class Display {

    private static int widthWindowInTile = 8;
    private static int heightWindowInTile = 16;
    private static int widthWindow = Game.TILE_WIDTH * widthWindowInTile;
    private static int heightGameSpaceWindow = Game.TILE_HEIGHT * heightWindowInTile;
    private static int heightWindow = heightGameSpaceWindow + Game.TILE_HEIGHT + ScoreManager.SIZE_FRAME_FOR_SCORE;

    private JPanel canvas;
    private JFrame window;
    private boolean isCreated;

    public static int getHeightGameSpaceWindow() {
        return heightGameSpaceWindow;
    }

    public static int getWidthWindowInTile() {
        return widthWindowInTile;
    }

    public static int getHeightWindowInTile() {
        return heightWindowInTile;
    }

    public static int getWidthWindow() {
        return widthWindow;
    }

    public static int getHeightWindow() {
        return heightWindow;
    }

    public JFrame getWindow() {
        return window;
    }

    public JPanel getCanvas() {
        if (!isCreated) {
            throw new IllegalStateException("Window has not been created!");
        }
        return canvas;
    }

    public void addKeyController(KeyAdapter keyController) {
        window.addKeyListener(keyController);
    }

    public void addMouseController(MouseAdapter mouseController) {
        window.addMouseListener(mouseController);
    }

    public void create(JPanel canvas, String title) {

        if (isCreated) {
            throw new IllegalStateException("Window was created!");
        }

        this.window = new JFrame(title);
        this.canvas = canvas;

        canvas.setPreferredSize(new Dimension(widthWindow, heightWindow));
        canvas.setLayout(null);
        canvas.setBackground(Color.white);

        window.getContentPane().add(canvas);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.pack();
        window.setSize(window.getPreferredSize());
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        isCreated = true;
    }

    public void destroy() {
        if (isCreated) {
            isCreated = false;
            window.dispose();
        } else {
            throw new IllegalStateException("Window has not been created!");
        }
    }
}
