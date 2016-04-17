package objects;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by oxana_bs on 10.4.2016 г..
 */
public class BasicEnemy extends Enemy{

    public static int velocity;

    public BasicEnemy(int x, int y) {
        super(x, y);
        velocity = 2;
    }

    @Override
    public void move(Rectangle[] obstacles) {

    }

    public void render(Graphics g, BufferedImage img) {
        g.drawImage(img, this.x, this.y, null);
    }

    @Override
    public void keepInBounds () {

    }
}