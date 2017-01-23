package models.FrameProcessing.MotionTracking.Points;

import org.junit.Test;
import org.mockito.internal.matchers.Null;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 * Created by arthur on 21/01/17.
 */
public class TrackingPointTest {

    @Test
    public void testKnowsItsNeighbors() throws Exception{
        TrackablePoint topPoint = mock(TrackablePoint.class);
        TrackablePoint bottomPoint = mock(TrackablePoint.class);
        TrackablePoint rightPoint = mock(TrackablePoint.class);
        TrackablePoint leftPoint = mock(TrackablePoint.class);

        TrackingPoint trackingPoint = new TrackingPoint(0, 0, topPoint, bottomPoint, rightPoint, leftPoint);

        assertSame(topPoint, trackingPoint.getTopPoint());
        assertSame(bottomPoint, trackingPoint.getBottomPoint());
        assertSame(rightPoint, trackingPoint.getRightPoint());
        assertSame(leftPoint, trackingPoint.getLeftPoint());
    }

    @Test
    public void testClustersNeighbors() throws Exception{
        TrackablePoint topPoint = mock(TrackablePoint.class);
        TrackablePoint bottomPoint = mock(TrackablePoint.class);
        TrackablePoint rightPoint = mock(TrackablePoint.class);
        TrackablePoint leftPoint = mock(TrackablePoint.class);



    }



}