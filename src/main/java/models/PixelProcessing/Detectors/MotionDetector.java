package models.PixelProcessing.Detectors;

import models.FrameProcessing.Buffer;
import models.FrameProcessing.ClusterCreator;
import models.FrameProcessing.FrameData;
import models.FrameProcessing.PointCluster;
import models.PixelProcessing.Filters.PixelFilter;
import models.UserSettings;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.List;

public class MotionDetector extends Detector{
    private static final int FRAMES_PER_SECOND = 30;
    private static final int BUFFER_SIZE = (int)(FRAMES_PER_SECOND * 1.5f);
    private final Buffer<FrameData> buffer;
    private final PixelDetector detector;

    public MotionDetector(PixelDetector detector, PixelFilter[] filters){
        super(filters);
        this.detector = detector;
        buffer = new Buffer(BUFFER_SIZE);
    }

    public FrameData processImage(BufferedImage image){
        ClusterCreator clusterCreator = new ClusterCreator();
        for (int y = 0; y < image.getHeight(); y+= UserSettings.SCANLINES) {
            for (int x = 0; x < image.getWidth(); x+= UserSettings.SCANLINES) {
                int rgbValue = applyFilters(image.getRGB(x, y));
                if(detector.isInteresting(rgbValue)){
                    clusterCreator.handle(x, y);
                }
                if(shouldChangeImage){
                    image.setRGB(x, y, rgbValue);
                }
            }
        }
        FrameData frameData = processFrameData(clusterCreator.getClusters());
        buffer.add(frameData);
        return frameData;
    }

    private FrameData processFrameData(List<PointCluster> clusters){
        FrameData currentFrame = new FrameData();
        for (PointCluster pointCluster : clusters) {
            if(currentFrame.handle(pointCluster)){
                if(pointCluster.isPossibleWandPoint()){
                    findPastWandClusters(pointCluster);
                }
            }
        }
        return currentFrame;
    }

    private void findPastWandClusters(PointCluster cluster) {
        PointCluster currentCluster = cluster;
        Iterator<FrameData> iterator = buffer.iterator();
        while (iterator.hasNext()){
            FrameData next = iterator.next();
            PointCluster lastNearbyCluster = next.nearestClusterTo(currentCluster);
            if(lastNearbyCluster != null){
                cluster.addPastCluster(lastNearbyCluster);
                currentCluster = lastNearbyCluster;
            }
        }
    }

    private int applyFilters(int rgbValue){
        int newValue = rgbValue;
        for (PixelFilter filter : this.filters) {
            newValue = filter.execute(newValue);
        }
        return newValue;
    }
}
