package models.FrameProcessing.MotionTracking.Points;

/**
 * Created by arthur on 21/01/17.
 */
public interface TrackablePoint {

    boolean detectedAt(int frameIndex);

    TrackablePoint getTopPoint();
    TrackablePoint getBottomPoint();
    TrackablePoint getRightPoint();
    TrackablePoint getLeftPoint();

    int xCoord();
    int yCoord();
}
