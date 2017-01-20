package models.PixelProcessing.Detectors;

import models.FrameProcessing.ClusterCollection;
import models.PixelProcessing.Filters.PixelFilter;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;

public abstract class Detector {

    public boolean shouldChangeImage;
    public static final int DEFAULT_SCAN_DISTANCE = 1;
    protected int scanDistance = DEFAULT_SCAN_DISTANCE;
    protected final PixelFilter[] filters;

    public Detector(PixelFilter[] filters){
        this.filters = filters;
        shouldChangeImage = false;
    }

    public abstract ClusterCollection processImage(BufferedImage image);

    public void updateScanDistance(int newDistance){
        this.scanDistance = newDistance;
    }

}
