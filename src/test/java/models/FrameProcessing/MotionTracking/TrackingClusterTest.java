package models.FrameProcessing.MotionTracking;

import models.FrameProcessing.MotionTracking.Points.TrackablePoint;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static test.GrimoireCollectionAsserts.assertEmpty;

/**
 * Created by arthur on 21/01/17.
 */
public class TrackingClusterTest {

    @Test
    public void testConstruction() throws Exception{
        TrackingCluster trackingCluster = new TrackingCluster();

        assertEmpty(trackingCluster.getLines());
        assertEquals(0, trackingCluster.size());
    }


}