package models.PixelProcessing.Filters;

import java.awt.*;

/**
 * Created by Arthur on 1/8/2017.
 */
public class BrightnessFilter extends PixelFilter {

    public static final int DEFAULT_BRIGHTNESS = 0;
    private int brightnessValue = DEFAULT_BRIGHTNESS;

    public int execute(int rgbValue) {
        int brightRed = truncate(red(rgbValue) + this.brightnessValue);
        int brightGreen = truncate(green(rgbValue) + this.brightnessValue);
        int brightBlue = truncate(blue(rgbValue) + this.brightnessValue);
        return new Color(brightRed, brightGreen, brightBlue).getRGB();
    }

    public void reset() {
        this.brightnessValue = DEFAULT_BRIGHTNESS;
    }

    public void update(int brightnessValue){
        this.brightnessValue = brightnessValue;
    }

    public int getBrightnessValue(){
        return this.brightnessValue;
    }
}
