package models.FrameProcessing.MotionTracking;

import models.FrameProcessing.Buffer;
import models.FrameProcessing.MotionTracking.Points.TrackingPoint;
import models.PixelProcessing.Detectors.Detector;
import models.PixelProcessing.Detectors.PixelDetector;

import java.awt.image.BufferedImage;

/**
 * Created by arthur on 20/01/17.
 */
public class TrackingGrid {

    private final int gridIntensity;
    private final TrackingPoint[][] trackingPoints;
    private final PixelDetector detector;
    private final int width;
    private final int height;

    public TrackingGrid(PixelDetector detector, int width, int height){
        this.detector = detector;
        this.width = width;
        this.height = height;
        trackingPoints = new TrackingPoint[width][height];
        gridIntensity = 3;
        initializeTrackingPoints();

    }

    private void initializeTrackingPoints(){
        for (int y = 0; y < height; y+=gridIntensity) {
            for (int x = 0; x < width; x+=gridIntensity) {
//                trackingPoints[x][y] = new TrackingPoint();
            }
        }
    }

    public BufferedImage process(BufferedImage image){
        for (int y = 0; y < height; y+=gridIntensity) {
            for (int x = 0; x < width; x+=gridIntensity) {
                if(detector.isInteresting(image.getRGB(x, y))){

                }
            }
        }
        return image;
    }


}
