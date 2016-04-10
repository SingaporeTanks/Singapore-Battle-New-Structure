package graphics;

import java.awt.image.BufferedImage;

/**
 * Created by oxana_bs on 9.4.2016 г..
 */
public class Assets {

    private static final int width = 68, height = 68;

    public static BufferedImage background;
    public static BufferedImage player;
    public static BufferedImage hardEnemy;
    public static BufferedImage bossEnemy;
    public static BufferedImage basicEnemy;

    //Loads every resource needed for the game
    public static void init() {
        background = ResourceLoader.loadResource("/texture/grass.jpg");

        SpriteSheet sheet = new SpriteSheet(ResourceLoader.loadResource("/texture/15_tank_set.png"));
        player = sheet.cut(0, 0, width, height);
        hardEnemy = sheet.cut(width, 0, width, height);
        bossEnemy = sheet.cut(width, height, width, height);
        basicEnemy = sheet.cut(2 * width, 2 * height, width, height);
    }
}
