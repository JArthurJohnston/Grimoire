package models.PixelProcessing.Detectors;

import models.FrameProcessing.FrameData;
import models.PixelProcessing.Filters.PixelFilter;

import java.awt.image.BufferedImage;

public abstract class Detector {

    public boolean shouldChangeImage;
    protected final PixelFilter[] filters;

    public Detector(PixelFilter[] filters){
        this.filters = filters;
        shouldChangeImage = false;
    }

    public abstract FrameData processImage(BufferedImage image);

}
