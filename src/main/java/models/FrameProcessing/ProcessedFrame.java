package models.FrameProcessing;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Arthur on 1/11/2017.
 */
public class ProcessedFrame {

    private List<PointCluster> clusters;

    public ProcessedFrame(){
        this.clusters = new LinkedList<PointCluster>();
    }

    public void addPoint(int x, int y){
        Point point = new Point(x, y);
        if(clusters.isEmpty()) {
            clusters.add(new PointCluster(point));
        }
        else {
            for (PointCluster cluster : this.clusters) {
                if (cluster.contains(point)) {
                    cluster.add(point);
                    return;
                }
            }
            clusters.add(new PointCluster(point));
        }
    }

    public void clearUnnecessaryClusters(){
        LinkedList<PointCluster> validClusters = new LinkedList<PointCluster>();
        for (PointCluster cluster : this.clusters) {
            if (cluster.getPoints().size() > 10) {
                validClusters.add(cluster);
            }
        }
        this.clusters = validClusters;
    }

    public List<PointCluster> getClusters(){
        return this.clusters;
    }

}
