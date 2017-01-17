package models.FrameProcessing;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Arthur on 1/17/2017.
 */
public class ClusterCreator {

    private final List<PointCluster> clusters;

    public ClusterCreator(){
        clusters = new LinkedList<PointCluster>();
    }

    public void handle(int xCoord, int yCoord){
        for (PointCluster eachCluster :  this.clusters) {
            if (eachCluster.canHandle(xCoord, yCoord)){
                eachCluster.add(new Point(xCoord, yCoord));
                return;
            }
        }
        clusters.add(new PointCluster(new Point(xCoord, yCoord)));
    }

    public List<PointCluster> getClusters(){
        return this.clusters;
    }


}
