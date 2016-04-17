package graphics;

import java.awt.image.BufferedImage;


public class Assets {

    private static final int width = 68, height = 68;

    public static BufferedImage background;
    public static BufferedImage player;
    public static BufferedImage hardEnemy;
    public static BufferedImage basicEnemy;

    //Loads every resource needed for the game
    public static void init() {
        background = ResourceLoader.loadResource("/texture/grass_logo2.jpg");

        SpriteSheet sheet = new SpriteSheet(ResourceLoader
                .loadResource("/texture/15_tank_set.png"));
        player = sheet.cut(0, 0, width, height);
        hardEnemy = sheet.cut(width, 0, width, height);
        basicEnemy = sheet.cut(2 * width, 2 * height, width, height);
    }
}
