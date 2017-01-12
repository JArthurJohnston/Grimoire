package models.PixelProcessing.Filters;

import java.awt.*;

/**
 * Created by Arthur on 1/8/2017.
 */
public class ContrastFilter extends PixelFilter {

    public static final float DEFAULT_CONTRAST_LEVEL = 0.0f;
    private float contrastLevel;

    public ContrastFilter(){
        this.contrastLevel = calculateCorrectionFactor(DEFAULT_CONTRAST_LEVEL);
    }

    private float calculateCorrectionFactor(float contrastLevel){
        final float numerator = 259 * (contrastLevel + 255);
        final float denominator = 255 * (259 - contrastLevel);
        return numerator / denominator;
    }

    private int adjustContrast(final int rgbValue){
        final float adjustedColor = calculateCorrectionFactor(this.contrastLevel) * (rgbValue - 128) + 128;
        return truncate(adjustedColor);
    }

    public float getContrastLevel(){
        return this.contrastLevel;
    }

    public void update(float contrastLevel){
        this.contrastLevel = calculateCorrectionFactor(contrastLevel);
    }

    public int execute(int rgbValue) {
        final int contrastedRed =adjustContrast(red(rgbValue));
        final int contrastedGreen = adjustContrast(green(rgbValue));
        final int contrastedBlue = adjustContrast(blue(rgbValue));
        return new Color(contrastedRed, contrastedGreen, contrastedBlue).getRGB();
    }

    public void reset() {
        this.contrastLevel = DEFAULT_CONTRAST_LEVEL;
    }
}
