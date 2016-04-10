package game;

import display.Display;
import graphics.Assets;
import graphics.ResourceLoader;
import graphics.SpriteSheet;
import objects.BasicEnemy;
import objects.BossEnemy;
import objects.HardEnemy;
import objects.Player;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import static graphics.Assets.player;

/**
 * Created by oxana_bs on 9.4.2016 г..
 */
public class Game implements Runnable {
    private String name;
    private int width, height;
    private Display display;

    private Thread thread;
    private Boolean running = false;
    private InputHandler inputHandler;
    private BufferStrategy bs;
    private Graphics g;

    private BufferedImage img;
    private SpriteSheet sh;
    private SpriteSheet shObstacles;
    private SpriteSheet ss1;
    private int x = 0;

    public static Player player;
    public static BasicEnemy basicEnemy1;
    public static BasicEnemy basicEnemy2;
    public static HardEnemy hardEnemy;
    //private static BossEnemy bossEnemy;

    public Game(String name, int width, int height) {
        this.name = name;
        this.width = width;
        this.height = height;
    }

    public void init() {
        // Assets.init();
        this.display = new Display(this.name, this.width, this.height);
        this.img = ResourceLoader.loadResource("/texture/grass.jpg");
        this.sh = new SpriteSheet(ResourceLoader.loadResource("/texture/15_tank_set.png"));
        this.shObstacles = new SpriteSheet(ResourceLoader.loadResource("/texture/obstacles_1.png"));
        this.ss1 = new SpriteSheet(ResourceLoader.loadResource("/texture/1.png"));
        player = new Player(350, 400);
        basicEnemy1 = new BasicEnemy(200, 250);
        basicEnemy2 = new BasicEnemy(500, 250);
        hardEnemy = new HardEnemy(350, 50);
        //bossEnemy = new BossEnemy();
        this.inputHandler = new InputHandler(this.display);
    }

    //The method that will update all the variables
    private void tick() {
        //Checks if a State exists and tick()
//        if (StateManager.getState() != null) {
//            StateManager.getState().tick();
//        }
        player.move();
//        if(player.Intersects(enemy)) {
//            System.out.print("You died");
//            stop();
//        }

    }

    public void render() {
        this.bs = this.display.getCanvas().getBufferStrategy();

        if (bs == null) {
            this.display.getCanvas().createBufferStrategy(2);
            this.bs = this.display.getCanvas().getBufferStrategy();
        }

        this.g = this.bs.getDrawGraphics();
        this.g.drawImage(ResourceLoader.loadResource("/texture/grass.jpg"), 0, 0, 800, 600, null);
        BufferedImage imgPlayer = ResourceLoader.loadResource("/texture/1.png");
        if (player.goingDown) {
           imgPlayer = ResourceLoader.loadResource("/texture/3.png");
        } else if (player.goingLeft) {
            imgPlayer = ResourceLoader.loadResource("/texture/4.png");
        } else if (player.goingRight) {
            imgPlayer = ResourceLoader.loadResource("/texture/2.png");
        } else if (player.goingUp) {
            imgPlayer = ResourceLoader.loadResource("/texture/1.png");
        }
        //BufferedImage imgPlayer = sh.cut(0, 0, 68, 68);
        BufferedImage imgBasicEnemy = sh.cut(68, 0, 68, 68);
        BufferedImage imgHardEnemy = sh.cut(136, 68, 68, 68);

        player.render(g, imgPlayer);
        basicEnemy1.render(g, imgBasicEnemy);
        basicEnemy2.render(g, imgBasicEnemy);
        hardEnemy.render(g, imgHardEnemy);

        this.g.drawImage(this.shObstacles.cut(0, 0, 51, 51), 0, 300, null);
        this.g.drawImage(this.shObstacles.cut(0, 0, 51, 51), 51, 300, null);
        this.g.drawImage(this.shObstacles.cut(0, 0, 51, 51), 102, 300, null);

        this.g.drawImage(this.shObstacles.cut(0, 0, 51, 51), 749, 300, null);
        this.g.drawImage(this.shObstacles.cut(0, 0, 51, 51), 698, 300, null);
        this.g.drawImage(this.shObstacles.cut(0, 0, 51, 51), 647, 300, null);

        this.g.drawImage(this.shObstacles.cut(0, 0, 51, 51), 360, 249, null);
        this.g.drawImage(this.shObstacles.cut(0, 0, 51, 51), 360, 300, null);

        this.g.dispose();
        this.bs.show();
    }

    @Override
    public void run() {
        this.init();
        while (running) {
            tick();
            render();
        }
        this.stop();
    }

    //Creating a start method for the Thread to start our game
    //Synchronized is used because our method is working with threads
    //so we ensure ourselves that nothing will go bad
    public synchronized void start() {
        //If the game is running exit the method
        //This is done in order to prevent the game to initialize
        //more than enough threads
        if (running) {
            return;
        }
        //Setting the while-game-loop to run
        running = true;
        //Initialize the thread that will work with "this" class (game.Game)
        thread = new Thread(this);
        //The start method will call start the new thread and it will call
        //the run method in our class
        thread.start();
    }

    //Creating a stop method for the Thread to stop our game
    public synchronized void stop() {
        //If the game is not running exit the method
        //This is done to prevent the game from stopping a
        //non-existing thread and cause errors
        if (!running) {
            return;
        }
        running = false;
        //The join method stops the current method from executing and it
        //must be surrounded in try-catch in order to work
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
