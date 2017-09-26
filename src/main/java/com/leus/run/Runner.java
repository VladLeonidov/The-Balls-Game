package com.leus.run;

import com.leus.controllers.KeyController;
import com.leus.model.Game;
import com.leus.model.factories.figureFactories.CruciateFigureFactory;
import com.leus.model.factories.figureFactories.ThreeBallFigureFactory;
import com.leus.model.factories.figureFactories.TwoBallFigureFactory;
import com.leus.model.factories.spriteFactories.RandBallFactory;
import com.leus.UI.menuItems.Button;
import com.leus.UI.menuItems.Cursor;
import com.leus.UI.menuItems.Logo;
import com.leus.UI.menus.AbstractMenu;
import com.leus.UI.menus.GameOverMenu;
import com.leus.UI.menus.MainMenu;
import com.leus.UI.menus.ScoreMenu;
import com.leus.model.service.FieldManager;
import com.leus.model.service.scores.RecordTable;
import com.leus.paths.PathsToResources;
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
                new ThreeBallFigureFactory(figureStartPositionX, figureStartPositionY, new RandBallFactory()),
                new CruciateFigureFactory(figureStartPositionX, figureStartPositionY, new RandBallFactory()));

        Logo mainLogo = new Logo(ResourceLoader.loadImage(PathsToResources.LOGO.getPath()), -60, 100, 400, 300);
        Logo gameOverLogo = new Logo(ResourceLoader.loadImage(PathsToResources.GAME_OVER.getPath()), -60, 60, 400, 350);

        Button startButton = new Button(ResourceLoader.loadImage(PathsToResources.START_BUTTON.getPath()), 60, 300, 150, 75);
        Button exitButtonMainMenu = new Button(ResourceLoader.loadImage(PathsToResources.EXIT_BUTTON.getPath()), 60, 450, 150, 75) {
            @Override
            public void push() {
                display.destroy();
                System.exit(0);
            }
        };
        Button exitButtonGameOverMenu = new Button(ResourceLoader.loadImage(PathsToResources.EXIT_BUTTON.getPath()), 60, 450, 150, 75) {
            @Override
            public void push() {
                display.destroy();
                System.exit(0);
            }
        };
        Button scoreButton = new Button(ResourceLoader.loadImage(PathsToResources.SCORE_BUTTON.getPath()), 60, 375, 150, 75);
        Button restartButton = new Button(ResourceLoader.loadImage(PathsToResources.RESTART_BUTTON.getPath()), 60, 300, 150, 75);
        Button returnButton = new Button(ResourceLoader.loadImage(PathsToResources.RETURN_BUTTON.getPath()), Display.getWidthWindow() - 125, Display.getHeightWindow() - 50, 125, 60);

        startButton.setName("Start");
        exitButtonMainMenu.setName("Exit");
        exitButtonGameOverMenu.setName("Exit");
        scoreButton.setName("Score");
        restartButton.setName("Restart");
        returnButton.setName("Return");

        Cursor mainCursor = new Cursor(ResourceLoader.loadImage(PathsToResources.MENU_CURSOR.getPath()), 30, 325, 32, 32, 75);
        mainCursor.addListener(startButton.new CursorListenerImpl());
        mainCursor.addListener(scoreButton.new CursorListenerImpl());
        mainCursor.addListener(exitButtonMainMenu.new CursorListenerImpl());

        Cursor scoreCursor = new Cursor(ResourceLoader.loadImage(PathsToResources.MENU_CURSOR.getPath()), Display.getWidthWindow() - 160, Display.getHeightWindow() - 35, 32, 32, 0);
        scoreCursor.addListener(returnButton.new CursorListenerImpl());

        Cursor gameOverCursor = new Cursor(ResourceLoader.loadImage(PathsToResources.MENU_CURSOR.getPath()), 20, 325, 32, 32, 75);
        gameOverCursor.addListener(restartButton.new CursorListenerImpl());
        gameOverCursor.addListener(scoreButton.new CursorListenerImpl());
        gameOverCursor.addListener(exitButtonGameOverMenu.new CursorListenerImpl());

        KeyController keyController = new KeyController(display);
        keyController.addListener(mainCursor.new KeyControllerListenerImpl());
        keyController.addListener(scoreCursor.new KeyControllerListenerImpl());
        keyController.addListener(gameOverCursor.new KeyControllerListenerImpl());
        keyController.addListener(game.new GameKeyListener());

        MainMenu mainMenu = new MainMenu(ResourceLoader.loadImage(PathsToResources.BACK_GROUND_MENU.getPath()), mainCursor);
        ScoreMenu scoreMenu = new ScoreMenu(ResourceLoader.loadImage(PathsToResources.BACK_GROUND_MENU.getPath()), scoreCursor,
                RecordTable.getInstance());
        GameOverMenu gameOverMenu = new GameOverMenu(ResourceLoader.loadImage(PathsToResources.BACK_GROUND_MENU.getPath()), gameOverCursor);

        mainMenu.addMenuItem(mainLogo);
        mainMenu.addMenuItem(startButton);
        mainMenu.addMenuItem(scoreButton);
        mainMenu.addMenuItem(exitButtonMainMenu);

        scoreMenu.addMenuItem(returnButton);

        gameOverMenu.addMenuItem(gameOverLogo);
        gameOverMenu.addMenuItem(restartButton);
        gameOverMenu.addMenuItem(scoreButton);
        gameOverMenu.addMenuItem(exitButtonGameOverMenu);

        startButton.addListener(game.new ButtonListenerImpl());
        scoreButton.addListener(scoreMenu.new ButtonListenerImpl());
        returnButton.addListener(mainMenu.new ButtonListenerImpl());
        restartButton.addListener(game.new ButtonListenerImpl());

        mainMenu.addListener(scoreMenu);
        mainMenu.addListener(game.new DeactivateListenerImpl());
        mainMenu.addListener(gameOverMenu);

        scoreMenu.addListener(mainMenu);
        scoreMenu.addListener(game.new DeactivateListenerImpl());
        scoreMenu.addListener(gameOverMenu);

        game.addListener(mainMenu);
        game.addListener(scoreMenu);
        game.addListener(gameOverMenu);
        game.addListener(gameOverMenu.new GameOverListenerImpl());

        gameOverMenu.addListener(game.new DeactivateListenerImpl());
        gameOverMenu.addListener(mainMenu);
        gameOverMenu.addListener(scoreMenu);

        initializeDisplay(display, game, keyController, mainMenu, scoreMenu, gameOverMenu);
    }

    private static void initializeDisplay(Display display, Game game, KeyController keyController, AbstractMenu... menus) {
        display.create(new GamePanel(game, menus), "Balls");
        display.addKeyController(keyController);
    }
}
