package models.FrameProcessing.MotionTracking.Points;

public class NullTrackablePoint implements TrackablePoint {

    public boolean detectedAt(int frameIndex) {
        return false;
    }

    public TrackablePoint getTopPoint() {
        return this;
    }

    public TrackablePoint getBottomPoint() {
        return this;
    }

    public TrackablePoint getRightPoint() {
        return this;
    }

    public TrackablePoint getLeftPoint() {
        return this;
    }

    public int xCoord() {
        return -1;
    }

    public int yCoord() {
        return -1;
    }
}
