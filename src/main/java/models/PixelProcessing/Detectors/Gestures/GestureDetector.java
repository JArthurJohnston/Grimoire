package models.PixelProcessing.Detectors.Gestures;

import models.FrameProcessing.Point;
import models.PixelProcessing.Aggregator;

import java.util.*;

public class GestureDetector {
    private Point currentPoint;
    private final Aggregator<Direction> directionAggregator;

    public GestureDetector(){
        directionAggregator = new Aggregator<>(Direction.NONE, Direction.values());
    }

    public void addPoint(Point point){
        if(currentPoint == null){
            currentPoint = point;
        } else {
            directionAggregator.add(getGestureFor(currentPoint, point));
        }
    }

    private Direction getGestureFor(Point a, Point b){
        final int FUDGE_FACTOR = 10;
        if(Math.abs(a.xCoord - b.xCoord) < FUDGE_FACTOR){
            return  (a.yCoord > b.yCoord)? Direction.UPWARDS : Direction.DOWNWARDS;
        } else if(Math.abs(a.yCoord - b.yCoord) < FUDGE_FACTOR){
            return  (a.xCoord > b.xCoord)? Direction.LEFTWARDS : Direction.RIGHTWARDS;
        }
        return Direction.NONE;
    }

    public Direction getGesture(){
        return directionAggregator.aggregate();
    }

}
