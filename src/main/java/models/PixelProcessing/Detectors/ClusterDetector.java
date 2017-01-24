package models.PixelProcessing.Detectors;

import models.FrameProcessing.FrameData;

import java.awt.image.BufferedImage;

/**
 * Created by Arthur on 1/17/2017.
 */
public abstract class ClusterDetector {

    private final PixelDetector detector;

    public ClusterDetector(PixelDetector detector){
        this.detector = detector;
    }

    public abstract FrameData processImage(BufferedImage image);
}
