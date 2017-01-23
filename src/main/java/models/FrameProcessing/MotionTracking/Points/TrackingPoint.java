package models.FrameProcessing.MotionTracking.Points;

import models.FrameProcessing.Buffer;

public class TrackingPoint implements TrackablePoint {

    private final Buffer<Boolean> motionDetectedBuffer;
    private int xCoord;
    private int yCoord;
    private TrackablePoint topPoint;
    private TrackablePoint bottomPoint;
    private TrackablePoint rightPoint;
    private TrackablePoint leftPoint;

    public TrackingPoint(int x, int y, TrackablePoint top, TrackablePoint bottom, TrackablePoint right, TrackablePoint left){
        xCoord = x;
        yCoord = y;
        topPoint = top;
        bottomPoint = bottom;
        rightPoint = right;
        leftPoint = left;
        motionDetectedBuffer = new Buffer<Boolean>(90);
    }

    public boolean detectedAt(int frameIndex){
        return motionDetectedBuffer.get(frameIndex);
    }

    public TrackablePoint getTopPoint() {
        return topPoint;
    }

    public TrackablePoint getBottomPoint() {
        return bottomPoint;
    }

    public TrackablePoint getRightPoint() {
        return rightPoint;
    }

    public TrackablePoint getLeftPoint() {
        return leftPoint;
    }

    public int xCoord() {
        return xCoord;
    }

    public int yCoord() {
        return yCoord;
    }
}
