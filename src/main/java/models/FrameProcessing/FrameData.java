package models.FrameProcessing;

import models.UserSettings;
import org.bytedeco.javacv.Frame;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class FrameData {


    public final List<PointCluster> clusters;

    public FrameData(){
        this.clusters = new LinkedList<>();
    }

    public PointCluster nearestClusterTo(PointCluster cluster){
        LinkedList<PointCluster> clusters = new LinkedList<PointCluster>();
        for (PointCluster eachCluster : this.clusters) {
            if(eachCluster.centerPoint().distanceTo(cluster.centerPoint()) < UserSettings.MOTION_DETECTION_RADIUS){
                clusters.add(eachCluster);
            }
        }
        if(clusters.isEmpty()){
            return null;
        }
        clusters.sort((o1, o2) -> o1.centerPoint().distanceTo(o2.centerPoint()));
        return clusters.get(0);
    }

    public boolean handle(PointCluster cluster){
        if(this.shouldAddCluster(cluster)){
            this.clusters.add(cluster);
            return true;
        }
        return false;
    }

    private boolean shouldAddCluster(PointCluster cluster){
        if(cluster.isPossibleWandPoint())
            return true;
        for (PointCluster eachCluster : this.clusters) {
            if(eachCluster.envelops(cluster))
                return false;
        }
        return true;
    }

    public PointCluster get(int index){
        return this.clusters.get(index);
    }

}
