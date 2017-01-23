package models.PixelProcessing.Detectors;

import models.FrameProcessing.Buffer;
import models.FrameProcessing.ClusterCreator;
import models.FrameProcessing.ClusterCollection;
import models.FrameProcessing.PointCluster;
import models.PixelProcessing.Filters.PixelFilter;
import java.awt.image.BufferedImage;

public class MotionDetector extends Detector{
    private static final int FRAMES_PER_SECOND = 30;
    private static final int FIVE_SECONDS_OF_FRAMES = FRAMES_PER_SECOND * 5;
    private final Buffer<ClusterCollection> buffer;
    private final PixelDetector detector;

    public MotionDetector(PixelDetector detector, PixelFilter[] filters){
        super(filters);
        this.detector = detector;
        buffer = new Buffer(FIVE_SECONDS_OF_FRAMES);
    }

    public ClusterCollection processImage(BufferedImage image){
        ClusterCreator clusterCreator = new ClusterCreator();
        for (int y = 0; y < image.getHeight(); y+= scanDistance) {
            for (int x = 0; x < image.getWidth(); x+= scanDistance) {
                int rgbValue = applyFilters(image.getRGB(x, y));
                if(detector.isInteresting(rgbValue)){
                    clusterCreator.handle(x, y);
                }
                if(shouldChangeImage){
                    image.setRGB(x, y, rgbValue);
                }
            }
        }
        ClusterCollection clusterCollection = new ClusterCollection();
        for (PointCluster pointCluster : clusterCreator.getClusters()) {
            clusterCollection.handle(pointCluster);
        }
        processClusters(clusterCollection);
        return clusterCollection;
    }

    private void processClusters(ClusterCollection clusters){
        ClusterCollection currentClusters = clusters;
        for (PointCluster cluster : currentClusters.clusters) {
            if(cluster.isPossibleWandPoint()){
                findLastClusterInMovement(cluster);
            }
        }
        buffer.add(clusters);
    }

    private void findLastClusterInMovement(PointCluster cluster) {
        buffer.onEachDo(eachClusterCollection -> {
            for (PointCluster lastFramesCluster : eachClusterCollection.clusters) {
                if(lastFramesCluster.centerPoint().distanceTo(cluster.centerPoint()) <= 40){
                    cluster.addPastCluster(lastFramesCluster);
                }
            }
        });
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
