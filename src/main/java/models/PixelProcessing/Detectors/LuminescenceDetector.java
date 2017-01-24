package models.PixelProcessing.Detectors;

import models.UserSettings;

/**
 * Created by Arthur on 1/16/2017.
 */
public class LuminescenceDetector implements PixelDetector{

    public boolean isInteresting(int rgbValue) {
        int redValue = (rgbValue >>> 16) & 0xFF;
        int greenValue = (rgbValue >>> 8) & 0xFF;
        int blueValue = (rgbValue >>> 0) & 0xFF;
        return ((redValue * 0.2126f + greenValue * 0.7152f + blueValue * 0.0722f) / 255) > UserSettings.LUMINESCENCE_THREASHOLD;
    }

    public void updateThreshold(float value){
        UserSettings.LUMINESCENCE_THREASHOLD = value;
    }
}
