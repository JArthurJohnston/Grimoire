package models.ImageProcessing;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Arthur on 1/6/2017.
 */
public class GammaAdjustment extends Adjustment {

    private float gammaAdjustment;
    public final float defaultGammaAdjustment = 0.0f;

    public GammaAdjustment(){
        this.gammaAdjustment = this.defaultGammaAdjustment;
    }

    public void execute(BufferedImage image) {
        for(int x = 0; x < image.getWidth(); x++){
            for(int y = 0; y < image.getHeight(); y++){
                int newRed = truncateValue(255 * Math.pow(redValue(image, x, y) / 255, gamma()));
                int newGreen = truncateValue(255 * Math.pow(greenValue(image, x, y) / 255, gamma()));
                int newblue = truncateValue(255 * Math.pow(blueValue(image, x, y) / 255, gamma()));
                image.setRGB(x, y, new Color(newRed, newGreen, newblue).getRGB());
            }
        }
    }
    
    public float getGammaAdjustment(){
        return this.gammaAdjustment;
    }
    
    public void update(float gamma){
        this.gammaAdjustment = gamma;
    }
    
    private float gamma(){
        return 1.0f / this.gammaAdjustment;
    }
    
    public void reset(){
        this.gammaAdjustment = this.defaultGammaAdjustment;
    }
}
