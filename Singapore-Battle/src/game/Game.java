package game;

import display.Display;
import graphics.ResourceLoader;
import graphics.SpriteSheet;
import objects.*;
import objects.BasicEnemy;
import objects.HardEnemy;
import objects.Player;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game implements Runnable {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    private String name;
    private int width, height;
    private Display display;

    private Thread thread;
    private Boolean running = false;
    private InputHandler inputHandler;
    private BufferStrategy bs;
    private Graphics g;

    private BufferedImage img;
    private SpriteSheet shObstacles;
    private Machine.Heading headingPlayer;
    private Clip sfire, stank, sboom, scrash, sbron;
    public static Player player;
    public static BasicEnemy basicEnemy1;
    public static BasicEnemy basicEnemy2;
    public static HardEnemy hardEnemy;
    public Rectangle[] obstacles = {
            new Rectangle(0, 300, 51, 51),
            new Rectangle(51, 300, 51, 51),
            new Rectangle(102, 300, 51, 51),
            new Rectangle(749, 300, 51, 51),
            new Rectangle(698, 300, 51, 51),
            new Rectangle(647, 300, 51, 51),
            new Rectangle(360, 249, 51, 51),
            new Rectangle(360, 300, 51, 51)
    };

    public Game(String name) {
        this.name = name;
        this.width = WIDTH;
        this.height = HEIGHT;
    }

    public void init() {
        this.display = new Display(this.name, this.width, this.height);
        this.img = ResourceLoader.loadResource("/texture/grass_logo2.jpg");
        this.shObstacles = new SpriteSheet(ResourceLoader.loadResource("/texture/obstacles_1.png"));

        player = new Player(360, 400);
        basicEnemy1 = new BasicEnemy(200, 250, BasicEnemy.Heading.DOWN);
        basicEnemy2 = new BasicEnemy(520, 250, BasicEnemy.Heading.DOWN);
        hardEnemy = new HardEnemy(360, 50);
        this.inputHandler = new InputHandler(this.display);
    }

    //The method that will update all the variables
    private void tick() {
        basicEnemy1.move(obstacles);
        basicEnemy2.move(obstacles);
        //hardEnemy.move(obstacles);
        player.move(obstacles);
        player.keepInBounds();
        basicEnemy1.keepInBounds();
        basicEnemy2.keepInBounds();
    }

    public void render() {
        this.bs = this.display.getCanvas().getBufferStrategy();

        if (bs == null) {
            this.display.getCanvas().createBufferStrategy(2);
            this.bs = this.display.getCanvas().getBufferStrategy();
        }

        this.g = this.bs.getDrawGraphics();
        this.g.drawImage(ResourceLoader.loadResource("/texture/grass_logo2.jpg"), 0, 0, 800, 600, null);
        BufferedImage imgPlayer = null;
        if (headingPlayer == Machine.Heading.UP) {
            imgPlayer = ResourceLoader.loadResource("/texture/tankUp.png");
        } else if (headingPlayer == Machine.Heading.DOWN) {
            imgPlayer = ResourceLoader.loadResource("/texture/tankDown.png");
        } else if (headingPlayer == Machine.Heading.LEFT) {
            imgPlayer = ResourceLoader.loadResource("/texture/tankLeft.png");
        } else if (headingPlayer == Machine.Heading.RIGHT) {
            imgPlayer = ResourceLoader.loadResource("/texture/tankRight.png");
        }
        if (player.goingDown) {
           imgPlayer = ResourceLoader.loadResource("/texture/tankDown.png");
            headingPlayer = Machine.Heading.DOWN;
        } else if (player.goingLeft) {
            imgPlayer = ResourceLoader.loadResource("/texture/tankLeft.png");
            headingPlayer = Machine.Heading.LEFT;
        } else if (player.goingRight) {
            imgPlayer = ResourceLoader.loadResource("/texture/tankRight.png");
            headingPlayer = Machine.Heading.RIGHT;
        } else if (player.goingUp) {
            imgPlayer = ResourceLoader.loadResource("/texture/tankUp.png");
            headingPlayer = Machine.Heading.UP;
        }

        BufferedImage imgBasicEnemy1 = null;
        BufferedImage imgBasicEnemy2 = null;
        BufferedImage imgHardEnemy = ResourceLoader.loadResource("/texture/HardEnemyUp.png");
        if (basicEnemy1.heading == BasicEnemy.Heading.UP) {
            imgBasicEnemy1 = ResourceLoader.loadResource("/texture/BasicEnemyUp.png");
        } else if (basicEnemy1.heading == BasicEnemy.Heading.DOWN) {
            imgBasicEnemy1 = ResourceLoader.loadResource("/texture/BasicEnemyDown.png");
        } else if (basicEnemy1.heading == BasicEnemy.Heading.LEFT) {
            imgBasicEnemy1 = ResourceLoader.loadResource("/texture/BasicEnemyLeft.png");
        } else if (basicEnemy1.heading == BasicEnemy.Heading.RIGHT) {
            imgBasicEnemy1 = ResourceLoader.loadResource("/texture/BasicEnemyRight.png");
        }

        if (basicEnemy2.heading == BasicEnemy.Heading.UP) {
            imgBasicEnemy2 = ResourceLoader.loadResource("/texture/BasicEnemyUp.png");
        } else if (basicEnemy2.heading == BasicEnemy.Heading.DOWN) {
            imgBasicEnemy2 = ResourceLoader.loadResource("/texture/BasicEnemyDown.png");
        } else if (basicEnemy2.heading == BasicEnemy.Heading.LEFT) {
            imgBasicEnemy2 = ResourceLoader.loadResource("/texture/BasicEnemyLeft.png");
        } else if (basicEnemy2.heading == BasicEnemy.Heading.RIGHT) {
            imgBasicEnemy2 = ResourceLoader.loadResource("/texture/BasicEnemyRight.png");
        }
        player.render(g, imgPlayer);
        basicEnemy1.render(g, imgBasicEnemy1);
        basicEnemy2.render(g, imgBasicEnemy2);
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
        headingPlayer = Machine.Heading.UP;
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