package com.leus.display;

import com.leus.game.Game;
import com.leus.game.service.scores.ScoreManager;
import com.leus.utils.SettingsInitializer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;

/**
 * This is the class-container, it is used to create the game {@literal window.
 * } This class delegate its work to JFrame and {@literal JPanel.} Warning! Methods must be called after call
 * method {@link #create(JPanel, String)}.
 * @see JFrame
 * @see JPanel
 */
public final class Display {

    /**
     * Width window in tiles, used value from {@code Settings.properties} file.
     * @see SettingsInitializer
     * @see Game#TILE_WIDTH
     * @see Game#TILE_HEIGHT
     */
    public static final int WIDTH_WINDOW_IN_TILES = Integer.valueOf(SettingsInitializer.getPropertyValue("WidthWindowInTile"));

    /**
     * Height window in tiles, used value from {@code Settings.properties} file.
     * @see SettingsInitializer
     * @see Game#TILE_WIDTH
     * @see Game#TILE_HEIGHT
     */
    public static final int HEIGHT_WINDOW_IN_TILES = Integer.valueOf(SettingsInitializer.getPropertyValue("HeightWindowInTile"));

    /**
     * The width of the entire window.
     */
    public static final int WIDTH_WINDOW = Game.TILE_WIDTH * WIDTH_WINDOW_IN_TILES;

    /**
     * The height the window of the game space.
     */
    public static final int HEIGHT_GAME_SPACE_WINDOW = Game.TILE_HEIGHT * HEIGHT_WINDOW_IN_TILES;

    /**
     * The height of the entire window.
     */
    public static final int HEIGHT_WINDOW = HEIGHT_GAME_SPACE_WINDOW + Game.TILE_HEIGHT + ScoreManager.SIZE_FRAME_FOR_SCORE;

    private JPanel canvas;
    private JFrame window;
    private boolean isCreated;

    /**
     * This method is add a keyboard controller to JFrame.
     * @see KeyAdapter
     * @see java.awt.event.KeyListener
     * @param keyController KeyAdapter implements KeyListener
     */
    public void addKeyController(KeyAdapter keyController) {
        window.addKeyListener(keyController);
    }

    /**
     * This method is add a mouse controller to JFrame.
     * @see MouseAdapter
     * @see java.awt.event.MouseListener
     * @param mouseController MouseAdapter implements MouseListener
     */
    public void addMouseController(MouseAdapter mouseController) {
        window.addMouseListener(mouseController);
    }

    /**
     * The Method repaint window components, delegate its work to {@link JPanel#repaint()}.
     * @throws IllegalStateException if window has not been created.
     */
    public void repaint() {
        if (!isCreated) {
            throw new IllegalStateException("Window has not been created!");
        }

        canvas.repaint();
    }

    /**
     * This method is create the game window, size the window depending from {@code Settings.properties} file,
     * window not resizable.
     * @see SettingsInitializer
     * @param canvas the class is responsible for drawing the game elements
     * @param title the title of the game
     * @throws IllegalStateException if the window was created.
     */
    public void create(JPanel canvas, String title) {

        if (isCreated) {
            throw new IllegalStateException("Window was created!");
        }

        this.window = new JFrame(title);
        this.canvas = canvas;

        canvas.setPreferredSize(new Dimension(WIDTH_WINDOW, HEIGHT_WINDOW));
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

    /**
     * This method clean the game window, delegate its work to {@link JFrame#dispose()}.
     * @throws IllegalStateException if the window has not been created.
     */
    public void destroy() {
        if (isCreated) {
            isCreated = false;
            window.dispose();
        } else {
            throw new IllegalStateException("Window has not been created!");
        }
    }
}
