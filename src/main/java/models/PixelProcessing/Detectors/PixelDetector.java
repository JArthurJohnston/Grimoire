package models.PixelProcessing.Detectors;

/**
 * Created by Arthur on 1/16/2017.
 */
public interface PixelDetector {

    boolean isInteresting(int rgbValue);
}
