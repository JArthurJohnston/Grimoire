package models.ImageProcessing;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Arthur on 1/6/2017.
 */
public class ContrastAdjustment extends Adjustment {

    private float contrastLevel;
    public final float defaultContrastLevel = 0.0f;
    private final float correctionFactor;

    public ContrastAdjustment(){
        this.contrastLevel = this.defaultContrastLevel;
        this.correctionFactor = calculateCorrectionFactor();
    }

    public void execute(final BufferedImage image) {
        for(int x = 0; x < image.getWidth(); x++){
            for(int y = 0; y < image.getHeight(); y++){
                final int contrastedRed =adjustContrast(redValue(image, x, y));
                final int contrastedGreen = adjustContrast(greenValue(image, x, y));
                final int contrastedBlue = adjustContrast(blueValue(image, x, y));
                final Color contrastedColor = new Color(contrastedRed, contrastedGreen, contrastedBlue);
                image.setRGB(x, y, contrastedColor.getRGB());
            }
        }
    }
    
    public float getContrastLevel(){
        return this.contrastLevel;
    }

    private int adjustContrast(final float colorValue){
        final float adjustedColor = calculateCorrectionFactor() * (colorValue - 128) + 128;
        return truncateValue(adjustedColor);
    }

    private float calculateCorrectionFactor(){
        final float numerator = 259 * (this.contrastLevel + 255);
        final float denominator = 255 * (259 - this.contrastLevel);
        return numerator / denominator;
    }
    
    public void update(float contrast){
        this.contrastLevel = contrast;
    }

    @Override
    public void reset() {
        this.contrastLevel = this.defaultContrastLevel;
    }
}
