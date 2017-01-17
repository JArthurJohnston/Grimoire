package models.FrameProcessing;

import org.bytedeco.javacv.Frame;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Arthur on 1/17/2017.
 */
public class ClusterCollection {

    private static final int FUDGE_FACTOR = 20;

    public final List<PointCluster> clusters;
    public boolean motionDetected;

    public ClusterCollection(List<PointCluster> clusters){
        this.clusters = clusters;
    }

    public List<PointCluster> nearbyClustersTo(PointCluster cluster){
        LinkedList<PointCluster> clusters = new LinkedList<PointCluster>();
        for (PointCluster eachCluster : this.clusters) {
            if(eachCluster.centerPoint().distanceTo(cluster.centerPoint()) < FUDGE_FACTOR){
                clusters.add(eachCluster);
            }
        }
        return clusters;
    }
}
