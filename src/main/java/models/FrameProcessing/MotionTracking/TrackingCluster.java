package models.FrameProcessing.MotionTracking;

import models.FrameProcessing.MotionTracking.Points.TrackablePoint;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by arthur on 21/01/17.
 */
public class TrackingCluster {

    private final List<List<TrackablePoint>> lines;
    private int size;

    public TrackingCluster(){
        lines = new ArrayList<List<TrackablePoint>>();
        size = 0;
    }

    public void add(TrackablePoint point){
        this.lines.get(point.yCoord()).add(point);
    }

    public List<List<TrackablePoint>> getLines(){
        return lines;
    }

    public int size() {
        return size();
    }
}
