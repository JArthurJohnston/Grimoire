package models.PixelProcessing.Detectors;

import models.FrameProcessing.ClusterCollection;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static test.ImageTestHelper.loadTestImage;
import static test.ReflectiveHelper.getPrivateField;

/**
 * Created by Arthur on 1/17/2017.
 */
public class MotionDetectorTest {

    @Test
    public void testConstruction() throws Exception {
        WhitePixelDetector detector = mock(WhitePixelDetector.class);

        MotionDetector motionDetector = new MotionDetector(detector);

        assertSame(detector, getPrivateField(motionDetector, ""));
    }

    @Test
    public void testIgnoresObjectsThatArentMoving() throws Exception {
        MotionDetector motionDetector = new MotionDetector(new WhitePixelDetector());

        ClusterCollection frameCluster = motionDetector.processImage(loadTestImage("/ignored-frames/frame-1.png"));
        assertFalse(frameCluster.motionDetected);
        assertEquals(1, frameCluster.clusters.size());

        ClusterCollection frameCluster2 = motionDetector.processImage(loadTestImage("/ignored-frames/frame-2.png"));
        assertFalse(frameCluster2.motionDetected);
        assertEquals(1, frameCluster2.clusters.size());
        assertEquals(1, frameCluster.clusters);
    }

}