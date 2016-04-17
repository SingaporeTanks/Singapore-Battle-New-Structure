package graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by oxana_bs on 9.4.2016 г..
 */
public class ResourceLoader {
    //Method that returns a resource
    public static BufferedImage loadResource(String path) {
        try {
            return ImageIO.read(ResourceLoader.class.getResource(path));
        } catch (IOException e) {
            //e.printStackTrace();
            //Exits the application
            System.exit(1);
        }

        //Ensures that no errors are passed
        return null;
    }
}