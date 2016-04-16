package graphics;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import java.awt.image.BufferedImage;
import java.io.File;
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
    public static Clip loadSound(String filename){
        File file = null;
        Clip clip = null;
        AudioFileFormat fmt = null;
        try {
            file = new File(filename);
            fmt  = AudioSystem.getAudioFileFormat(file);
            clip = (Clip)AudioSystem.getLine(new DataLine.Info(Clip.class, fmt.getFormat()));
            clip.open(AudioSystem.getAudioInputStream(file));
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            fmt  = null;
            file = null;
        }
        return clip;
    }

}
