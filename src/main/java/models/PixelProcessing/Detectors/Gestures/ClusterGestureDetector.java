package models.PixelProcessing.Detectors.Gestures;

import models.FrameProcessing.Point;
import models.FrameProcessing.PointCluster;
import models.UserSettings;

import static models.FrameProcessing.PointMath.*;

public class ClusterGestureDetector {

    private final PointCluster cluster;

    public ClusterGestureDetector(final PointCluster cluster){
        this.cluster = cluster;
    }

    public Direction getGestureDirection(){
        Point currentPoint = this.cluster.centerPoint();
        Point firstPoint = this.cluster.getPastClusters().getLast().centerPoint();

        if(currentPoint.distanceTo(firstPoint) >= UserSettings.GESTURE_DETECTION_DISTANCE){
            final float slope = slope(currentPoint, firstPoint);
            if(isDiagonal(slope)){
                boolean isRightwardsMovement = currentPoint.xCoord > firstPoint.xCoord;
                if(slope > 0){
                    return isRightwardsMovement? Direction.DOWNWARDS_LEFT : Direction.UPWARDS_RIGHT;
                }else {
                    return isRightwardsMovement? Direction.UPWARDS_LEFT : Direction.DOWNWARDS_RIGHT;
                }
            }
            int differenceInX = Math.abs(currentPoint.xCoord - firstPoint.xCoord);
            int differenceInY = Math.abs(currentPoint.yCoord - firstPoint.yCoord);
            if(differenceInX > differenceInY){
                return currentPoint.xCoord > firstPoint.xCoord? Direction.LEFTWARDS : Direction.RIGHTWARDS;
            }else {
                return currentPoint.yCoord > firstPoint.yCoord? Direction.DOWNWARDS : Direction.UPWARDS;
            }
        }
        return Direction.NONE;
    }
}
