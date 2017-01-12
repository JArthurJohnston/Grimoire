package models.ImageProcessing;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Arthur on 1/6/2017.
 */
public class BrightnessAdjustment extends Adjustment {

    private int brightnessValue = 0;
    public final int defaultBrightness = 0;

    public void execute(BufferedImage image) {
        for(int x = 0; x < image.getWidth(); x++){
            for(int y = 0; y < image.getHeight(); y++){
                int brightRed = truncateValue(redValue(image, x, y) + this.brightnessValue);
                int brightGreen = truncateValue(greenValue(image, x, y) + this.brightnessValue);
                int brightBlue = truncateValue(blueValue(image, x, y) + this.brightnessValue);
                image.setRGB(x, y, new Color(brightRed, brightGreen, brightBlue).getRGB());
            }
        }
    }
    
    public int getBrightness(){
        return this.brightnessValue;
    }
    
    public void update(int brightness){
        this.brightnessValue = brightness; 
    }

    @Override
    public void reset() {
        this.brightnessValue = this.defaultBrightness;
    }
    
}
