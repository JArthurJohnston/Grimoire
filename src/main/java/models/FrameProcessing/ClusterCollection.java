package models.FrameProcessing;

import org.bytedeco.javacv.Frame;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Arthur on 1/17/2017.
 */
public class ClusterCollection {

    private static final int FUDGE_FACTOR = 50;

    public final List<PointCluster> clusters;

    public ClusterCollection(){
        this.clusters = new LinkedList<PointCluster>();
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

    public void handle(PointCluster cluster){
        if(this.shouldAddCluster(cluster)){
            this.clusters.add(cluster);
        }
    }

    private boolean shouldAddCluster(PointCluster cluster){
        for (PointCluster eachCluster : this.clusters) {
            if(eachCluster.envelops(cluster))
                return false;
        }
        return true;
    }


}
