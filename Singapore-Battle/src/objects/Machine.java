package objects;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by oxana_bs on 10.4.2016 г..
 */
public abstract class Machine {

    protected int x, y;
    // coordinates
    protected int velocity;
    //speed

    public Machine(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public void setVelX(int velX) {

        this.velocity = velX;
    }

    public void setVelY(int velY) {

        this.velocity = velY;
    }

    public int getVelX() {
        return velocity;
    }

    public int getVelY() {
        return velocity;
    }

    public abstract void move();

    public abstract void render(Graphics g, BufferedImage img);

}

