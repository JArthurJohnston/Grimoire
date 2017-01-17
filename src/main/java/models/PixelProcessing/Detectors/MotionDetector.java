package models.PixelProcessing.Detectors;


import models.FrameProcessing.ClusterCreator;
import models.FrameProcessing.ClusterCollection;
import models.FrameProcessing.PointCluster;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Created by Arthur on 1/12/2017.
 */
public class MotionDetector {
    private int scanDistance = 1;
    private final PixelDetector detector;
    private ClusterCollection lastFrame;

    public MotionDetector(PixelDetector detector){
        this.detector = detector;
    }

    public ClusterCollection processImage(BufferedImage image){
        ClusterCreator clusterCreator = new ClusterCreator();
        for (int y = 0; y < image.getHeight(); y+= scanDistance) {
            for (int x = 0; x < image.getWidth(); x++) {
                int rgbValue = image.getRGB(x, y);
                if(detector.isInteresting(rgbValue)){
                    clusterCreator.handle(x, y);
                }
            }
        }
        ClusterCollection frameCluster = new ClusterCollection(clusterCreator.getClusters());
        if(lastFrame != null){
             detectMovements(frameCluster);
        }
        lastFrame = frameCluster;
        return frameCluster;
    }

    private void detectMovements(ClusterCollection cluster){
        for (PointCluster eachClusterFromLastFrame : lastFrame.clusters) {
            List<PointCluster> nearbyClusters = cluster.nearbyClustersTo(eachClusterFromLastFrame);
            if(!nearbyClusters.isEmpty()){
                cluster.motionDetected = true;
            }
        }
    }

    public void setScanLines(int newDistance){
        this.scanDistance = newDistance;
    }
}
