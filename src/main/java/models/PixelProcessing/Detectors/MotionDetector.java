package models.PixelProcessing.Detectors;

import models.FrameProcessing.Buffer;
import models.FrameProcessing.ClusterCreator;
import models.FrameProcessing.FrameData;
import models.FrameProcessing.PointCluster;
import models.PixelProcessing.Aggregator;
import models.PixelProcessing.Detectors.Gestures.Direction;
import models.PixelProcessing.Detectors.Gestures.GestureDetector;
import models.PixelProcessing.Filters.PixelFilter;
import models.UserSettings;
import java.awt.image.BufferedImage;
import java.util.*;

public class MotionDetector extends Detector{
    private static final int FRAMES_PER_SECOND = 30;
    private static final int BUFFER_SIZE = (int)(FRAMES_PER_SECOND * 1.5f);
    private final Buffer<FrameData> buffer;
    private final PixelDetector detector;
    private final Aggregator<Direction> directionAggregator;

    public MotionDetector(PixelDetector detector, PixelFilter[] filters){
        super(filters);
        this.detector = detector;
        buffer = new Buffer(BUFFER_SIZE);
        directionAggregator = new Aggregator<Direction>(Direction.NONE, Direction.values());
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

    private List<PointCluster> findPastWandClusters(PointCluster cluster) {
        GestureDetector gestureDetector = new GestureDetector();
        PointCluster currentCluster = cluster;
        gestureDetector.addPoint(cluster.centerPoint());
        Iterator<FrameData> iterator = buffer.iterator();
        while (iterator.hasNext()){
            FrameData next = iterator.next();
            PointCluster lastNearbyCluster = next.nearestClusterTo(currentCluster);
            if(lastNearbyCluster != null){
                if(cluster.addPastCluster(lastNearbyCluster)){
                    gestureDetector.addPoint(lastNearbyCluster.centerPoint());
                }
                currentCluster = lastNearbyCluster;
            }
        }
        directionAggregator.add(gestureDetector.getGesture());
        return cluster.getPastClusters();
    }

    public Direction currentMovement(){
        Direction currentDirection = directionAggregator.aggregate();
        directionAggregator.reset();
        return currentDirection;
    }

    private int applyFilters(int rgbValue){
        int newValue = rgbValue;
        for (PixelFilter filter : this.filters) {
            newValue = filter.execute(newValue);
        }
        return newValue;
    }
}
