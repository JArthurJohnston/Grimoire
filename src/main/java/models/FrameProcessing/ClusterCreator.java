package models.FrameProcessing;

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

public class ClusterCreator {

    private final List<PointCluster> clusters;
    private BufferedImage image;

    public ClusterCreator(BufferedImage image){
        this.image = image;
        clusters = new LinkedList();
    }

    public void handle(int xCoord, int yCoord){
        for (PointCluster eachCluster :  this.clusters) {
            if (eachCluster.contains(xCoord, yCoord)){
                eachCluster.add(new Point(xCoord, yCoord));
                return;
            }
        }
        clusters.add(new PointCluster(this.image, new Point(xCoord, yCoord)));
    }

    public List<PointCluster> getClusters(){
        return this.clusters;
    }


}
