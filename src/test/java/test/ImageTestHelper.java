package test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Arthur on 1/14/2017.
 */
public class ImageTestHelper {

    public static BufferedImage loadTestImage(String imageName){
        File imageFile = new File("./src/test/resources/test-images/" + imageName);
        try {
            return ImageIO.read(imageFile);
        } catch (IOException e) {
            System.out.println("Could not load test image named: " + imageName);
        }
        return null;
    }
}
