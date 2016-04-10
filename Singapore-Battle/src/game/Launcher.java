package game;

import display.Display;

/**
 * Created by oxana_bs on 9.4.2016 г..
 */
public class Launcher {
    public static void main(String[] args) {
        //Display display = new Display("Singapore Battle", 800, 600);
        Game game = new Game("Singapore Battle", 800, 600);
        game.start();
    }
}
