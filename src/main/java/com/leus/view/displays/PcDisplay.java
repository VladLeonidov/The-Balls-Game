package com.leus.view.displays;

import com.leus.model.GameField;
import com.leus.model.service.scores.ScoreManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;

public final class PcDisplay {

    private static PcDisplay display;

    private static int widthWindowInTile = 8;
    private static int heightWindowInTile = 16;
    private static int widthWindow = GameField.TILE_WIDTH * widthWindowInTile;
    private static int heightGameSpaceWindow = GameField.TILE_HEIGHT * heightWindowInTile;
    private static int heightWindow = heightGameSpaceWindow + GameField.TILE_HEIGHT + ScoreManager.SIZE_FRAME_FOR_SCORE;

    private JPanel canvas;
    private JFrame window;

    private boolean isCreated;

    private PcDisplay() {
    }

    public static PcDisplay getDisplay() {
        if (display == null) {
            display = new PcDisplay();
        }

        return display;
    }

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
        return canvas;
    }

    public void addKeyController(KeyAdapter keyController) {
        window.addKeyListener(keyController);
    }

    public void addMouseController(MouseAdapter mouseController) {
        window.addMouseListener(mouseController);
    }

    public void create(JPanel canvas, int widthInTile, int heightInTile, String title) {

        if (isCreated) {
            throw new IllegalStateException("Window was created!");
        }

        widthWindowInTile = widthInTile;
        heightWindowInTile = heightInTile;

        this.window = new JFrame(title);
        this.canvas = canvas;

        canvas.setPreferredSize(new Dimension(widthWindow, heightWindow));
        canvas.setBackground(Color.white);

        window.getContentPane().add(canvas);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        isCreated = true;
    }

    public void destroy() {
        if (isCreated) {
            window.dispose();
        } else {
            throw new IllegalStateException("Window has not been created!");
        }
    }
}
