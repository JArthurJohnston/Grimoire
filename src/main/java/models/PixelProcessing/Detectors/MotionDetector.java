package models.PixelProcessing.Detectors;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

/**
 * Created by Arthur on 1/12/2017.
 */
public class MotionDetector {

    private BufferedImage previousImage;
    private final int motionColor = Color.red.getRGB();

    public BufferedImage processImage(BufferedImage image){
        if(previousImage == null){
            previousImage = image;
        } else {
            int height = image.getHeight();
            int width = image.getWidth();
            for(int y = 0; y < height; y++) {
                for(int x = 0; x < width; x++){
                    if(image.getRGB(x, y) != previousImage.getRGB(x, y)){
                        image.setRGB(x, y, motionColor);
                    }
                }
            }
            previousImage = image;
        }
        return image;
    }
}
