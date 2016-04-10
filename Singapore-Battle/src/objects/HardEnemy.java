package objects;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by oxana_bs on 10.4.2016 г..
 */
public class HardEnemy extends Enemy {
    public HardEnemy(int x, int y) {
        super(x, y);
    }

    @Override
    public void move() {

    }

    public void render(Graphics g, BufferedImage img) {
        g.drawImage(img, this.x, this.y, null);
    }
}
