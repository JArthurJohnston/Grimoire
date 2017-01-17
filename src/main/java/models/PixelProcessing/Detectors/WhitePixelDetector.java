package models.PixelProcessing.Detectors;

import java.awt.*;

/**
 * Created by Arthur on 1/16/2017.
 */
public class WhitePixelDetector implements PixelDetector{

    private static final int WHITE_VALUE = Color.white.getRGB();

    public boolean isInteresting(int rgbValue) {
        return rgbValue == WHITE_VALUE;
    }
}
