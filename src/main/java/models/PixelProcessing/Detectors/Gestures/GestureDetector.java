package models.PixelProcessing.Detectors.Gestures;

import models.FrameProcessing.Point;
import models.FrameProcessing.PointCluster;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by arthur on 24/01/17.
 */
public class GestureDetector {
    private final List<PointPair> pairs;

    public GestureDetector(){
        pairs = new LinkedList<>();
    }

    public List<Gesture> getGesturesFrom(List<PointCluster> clusters){
        return new LinkedList<>();
    }

    private class PointPair{
        Point firstPoint;
        Point secondPoint;
        PointPair(Point first, Point second){
            firstPoint = first;
            secondPoint = second;
        }

        int length(){
            return firstPoint.distanceTo(secondPoint);
        }
    }
}
