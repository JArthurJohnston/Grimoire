package models.PixelProcessing.Detectors;

import java.awt.image.BufferedImage;

/**
 * Created by Arthur on 1/16/2017.
 */
public interface PixelDetector {

    boolean isInteresting(int rgbValue);
}
