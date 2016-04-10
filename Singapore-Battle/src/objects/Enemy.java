package objects;

/**
 * Created by oxana_bs on 10.4.2016 г..
 */
public abstract class Enemy extends Machine {

    public Enemy(int x, int y) {
        super(x, y);
        this.x = x;
        this.y = y;
    }
}
