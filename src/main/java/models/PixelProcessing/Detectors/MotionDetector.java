package models.PixelProcessing.Detectors;

import models.FrameProcessing.ClusterCreator;
import models.FrameProcessing.ClusterCollection;
import models.FrameProcessing.FrameBuffer;
import models.FrameProcessing.PointCluster;
import models.PixelProcessing.Filters.PixelFilter;
import java.awt.image.BufferedImage;

public class MotionDetector extends Detector{
    private static final int FRAMES_PER_SECOND = 30;
    private static final int FIVE_SECONDS_OF_FRAMES = FRAMES_PER_SECOND * 5;
    private final FrameBuffer frameBuffer;
    private final PixelDetector detector;

    public MotionDetector(PixelDetector detector, PixelFilter[] filters){
        super(filters);
        this.detector = detector;
        frameBuffer = new FrameBuffer(FIVE_SECONDS_OF_FRAMES);
    }

    public ClusterCollection processImage(BufferedImage image){
        ClusterCreator clusterCreator = new ClusterCreator();
        for (int y = 0; y < image.getHeight(); y+= scanDistance) {
            for (int x = 0; x < image.getWidth(); x++) {
                int rgbValue = applyFilters(image.getRGB(x, y));
                if(detector.isInteresting(rgbValue)){
                    clusterCreator.handle(x, y);
                }
                if(shouldChangeImage){
                    image.setRGB(x, y, rgbValue);
                }
            }
        }
        ClusterCollection clusterCollection = new ClusterCollection(null);
        for (PointCluster pointCluster : clusterCreator.getClusters()) {
            clusterCollection.handle(pointCluster);
        }
        return clusterCollection;
    }

    private int applyFilters(int rgbValue){
        int newValue = rgbValue;
        for (PixelFilter filter : this.filters) {
            newValue = filter.execute(newValue);
        }
        return newValue;
    }

    public void updateScanDistance(int newDistance){
        this.scanDistance = newDistance;
    }

}
