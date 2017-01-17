package models.PixelProcessing.Detectors;

/**
 * Created by Arthur on 1/16/2017.
 */
public class LuminescenceDetector implements PixelDetector{
    private float luminescenceThreshold = 0.98f;


    public boolean isInteresting(int rgbValue) {
        int redValue = (rgbValue >>> 16) & 0xFF;
        int greenValue = (rgbValue >>> 8) & 0xFF;
        int blueValue = (rgbValue >>> 0) & 0xFF;
        return ((redValue * 0.2126f + greenValue * 0.7152f + blueValue * 0.0722f) / 255) > luminescenceThreshold;
    }

    public void updateThreshold(int value){
        this.luminescenceThreshold = value;
    }
}
