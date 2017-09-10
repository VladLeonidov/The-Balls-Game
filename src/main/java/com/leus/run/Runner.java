package com.leus.run;

import com.leus.controllers.KeyController;
import com.leus.model.Game;
import com.leus.model.factories.figureFactories.HorizontalThreeBallFigureFactory;
import com.leus.model.factories.figureFactories.TwoBallFigureFactory;
import com.leus.model.factories.figureFactories.VerticalThreeBallFigureFactory;
import com.leus.model.factories.spriteFactories.RandBallFactory;
import com.leus.model.menuItems.Button;
import com.leus.model.menuItems.Cursor;
import com.leus.model.menuItems.Logo;
import com.leus.model.menus.AbstractMenu;
import com.leus.model.menus.GameOverMenu;
import com.leus.model.menus.MainMenu;
import com.leus.model.menus.ScoreMenu;
import com.leus.model.service.FieldManager;
import com.leus.paths.PathsToResources;
import com.leus.utils.RecordsUtil;
import com.leus.utils.ResourceLoader;
import com.leus.view.displays.Display;
import com.leus.view.panels.GamePanel;

public class Runner {
    public static void main(String[] args) {
        int figureStartPositionX = Display.getWidthWindow() / 2 - Game.TILE_WIDTH;
        int figureStartPositionY = 0;

        Display display = new Display();

        Game game = new Game(new FieldManager(),
                new TwoBallFigureFactory(figureStartPositionX, figureStartPositionY, new RandBallFactory()),
                new VerticalThreeBallFigureFactory(figureStartPositionX, figureStartPositionY, new RandBallFactory()),
                new HorizontalThreeBallFigureFactory(figureStartPositionX, figureStartPositionY, new RandBallFactory()));

        Logo mainLogo = new Logo(ResourceLoader.loadImage(PathsToResources.LOGO.getPath()), -60, 100, 400, 300);
        Logo gameOverLogo = new Logo(ResourceLoader.loadImage(PathsToResources.GAME_OVER.getPath()), -60, 75, 400, 350);

        Button startButton = new Button(ResourceLoader.loadImage(PathsToResources.START_BUTTON.getPath()), 60, 300, 150, 75);
        Button exitButtonMainMenu = new Button(ResourceLoader.loadImage(PathsToResources.EXIT_BUTTON.getPath()), 60, 450, 150, 75) {
            @Override
            public void push() {
                display.destroy();
                System.exit(0);
            }
        };
        Button exitButtonGameOverMenu = new Button(ResourceLoader.loadImage(PathsToResources.EXIT_BUTTON.getPath()), 60, 375, 150, 75) {
            @Override
            public void push() {
                display.destroy();
                System.exit(0);
            }
        };
        Button scoreButton = new Button(ResourceLoader.loadImage(PathsToResources.SCORE_BUTTON.getPath()), 60, 375, 150, 75);
        Button restartButton = new Button(ResourceLoader.loadImage(PathsToResources.RESTART_BUTTON.getPath()), 60, 300, 150, 75);
        Button returnButton = new Button(ResourceLoader.loadImage(PathsToResources.RETURN_BUTTON.getPath()), Display.getWidthWindow() - 125, Display.getHeightWindow() - 50, 125, 60);

        Cursor mainCursor = new Cursor(ResourceLoader.loadImage(PathsToResources.MENU_CURSOR.getPath()), 30, 325, 32, 32, 75);
        mainCursor.addListener(startButton.new CursorListenerImpl());
        mainCursor.addListener(scoreButton.new CursorListenerImpl());
        mainCursor.addListener(exitButtonMainMenu.new CursorListenerImpl());
        Cursor scoreCursor = new Cursor(ResourceLoader.loadImage(PathsToResources.MENU_CURSOR.getPath()), Display.getWidthWindow() - 160, Display.getHeightWindow() - 35, 32, 32, 0);
        scoreCursor.addListener(returnButton.new CursorListenerImpl());
        Cursor gameOverCursor = new Cursor(ResourceLoader.loadImage(PathsToResources.MENU_CURSOR.getPath()), 20, 325, 32, 32, 75);
        gameOverCursor.addListener(restartButton.new CursorListenerImpl());
        gameOverCursor.addListener(exitButtonGameOverMenu.new CursorListenerImpl());

        KeyController keyController = new KeyController(display);
        keyController.addListener(mainCursor.new CursorKeyListener());
        keyController.addListener(scoreCursor.new CursorKeyListener());
        keyController.addListener(gameOverCursor.new CursorKeyListener());
        keyController.addListener(game.new GameKeyListener());

        MainMenu mainMenu = new MainMenu(ResourceLoader.loadImage(PathsToResources.BACK_GROUND_MENU.getPath()), mainLogo, mainCursor, startButton, scoreButton, exitButtonMainMenu);
        ScoreMenu scoreMenu = new ScoreMenu(ResourceLoader.loadImage(PathsToResources.BACK_GROUND_MENU.getPath()), RecordsUtil.getRecords(), scoreCursor, 15, 15, 45, returnButton);
        GameOverMenu gameOver = new GameOverMenu(ResourceLoader.loadImage(PathsToResources.BACK_GROUND_MENU.getPath()), gameOverLogo, gameOverCursor, restartButton, exitButtonGameOverMenu);

        startButton.addListener(game.new ButtonListenerImpl());
        scoreButton.addListener(scoreMenu.new ButtonListenerImpl());
        returnButton.addListener(mainMenu.new ButtonListenerImpl());
        restartButton.addListener(game.new ButtonListenerImpl());

        mainMenu.addListener(scoreMenu);
        mainMenu.addListener(game.new ActiveListenerImpl());
        mainMenu.addListener(gameOver);
        scoreMenu.addListener(mainMenu);
        scoreMenu.addListener(game.new ActiveListenerImpl());
        scoreMenu.addListener(gameOver);
        game.addListener(mainMenu);
        game.addListener(scoreMenu);
        game.addListener(gameOver);
        game.addListener(gameOver.new GameOverListenerImpl());
        gameOver.addListener(game.new ActiveListenerImpl());
        gameOver.addListener(mainMenu);
        gameOver.addListener(scoreMenu);

        initializePcDisplay(display, game, keyController, mainMenu, scoreMenu, gameOver);
        game.setCanvas(display.getCanvas());
        //game.start();
    }

    private static void initializePcDisplay(Display display, Game game, KeyController keyController, AbstractMenu... menus) {
        display.create(new GamePanel(game, menus),8, 16, "Balls");
        display.addKeyController(keyController);
    }
}
