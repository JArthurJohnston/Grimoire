package models.PixelProcessing.Detectors;

import models.UserSettings;

public class LuminescenceDetector implements PixelDetector{

    private static final float RED_SENSITIVITY = 0.2126F;
    private static final float GREEN_SENSITIVITY = 0.7152F;
    private static final float BLUE_SENSITIVITY = 0.0722F;

    public boolean isBrightEnough(int rgbValue) {
        return luminescenceFor(rgbValue) >= UserSettings.LUMINESCENCE_THRESHOLD;
    }

    public static float luminescenceFor(int rgbValue){
        int redValue = (rgbValue >>> 16) & 0xFF;
        int greenValue = (rgbValue >>> 8) & 0xFF;
        int blueValue = (rgbValue >>> 0) & 0xFF;
        return ((redValue * RED_SENSITIVITY +
                greenValue * GREEN_SENSITIVITY +
                blueValue * BLUE_SENSITIVITY) / 255);
    }

    public void updateThreshold(float value){
        UserSettings.LUMINESCENCE_THRESHOLD = value;
    }
}
