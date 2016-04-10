package objects;

import graphics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by oxana_bs on 10.4.2016 г..
 */
public class Player extends Machine {

    public static boolean goingUp;
    public static boolean goingDown;
    public static boolean goingLeft;
    public static boolean goingRight;
    private static int velocity;

    public Player(int x, int y) {
        super(x, y);
        velocity = 2;

        goingUp = false;
        goingDown = false;
        goingLeft = false;
        goingRight = false;
    }

    @Override
    public void move() {
        //Update the movement of the player

        //Update the bounding box's position
        //this.boundingBox.setBounds(this.x, this.y, this.width, this.height);

        if (goingUp) {
            this.y -= this.velocity;
        }
        if (goingDown) {
            this.y += this.velocity;
        }
        if (goingLeft) {
            this.x -= this.velocity;
        }
        if (goingRight) {
            this.x += this.velocity;
        }
    }

    public void render(Graphics g, BufferedImage img) {
        g.drawImage(img, this.x, this.y, null);
    }
}
