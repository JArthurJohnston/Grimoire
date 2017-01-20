package models.PixelProcessing.Detectors;

import models.FrameProcessing.FrameBuffer;
import models.PixelProcessing.Filters.PixelFilter;
import org.junit.Test;

import java.awt.image.BufferedImage;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static test.ImageTestHelper.loadTestImage;
import static test.ReflectiveHelper.*;

/**
 * Created by Arthur on 1/17/2017.
 */
public class MotionDetectorTest {

    @Test
    public void testConstruction() throws Exception {
        WhitePixelDetector pixelDetector = mock(WhitePixelDetector.class);

        MotionDetector motionDetector = new MotionDetector(pixelDetector, new PixelFilter[0]);

        assertSame(pixelDetector, getPrivateField(motionDetector, "detector"));
        assertNotNull(getPrivateField(motionDetector, "frameBuffer", FrameBuffer.class));
    }

    @Test
    public void testProcessImageAddesClustersToBuffer() throws Exception{
        WhitePixelDetector pixelDetector = mock(WhitePixelDetector.class);
        MotionDetector motionDetector = new MotionDetector(pixelDetector,new PixelFilter[0]);
        FrameBuffer frameBuffer = getPrivateField(motionDetector, "frameBuffer", FrameBuffer.class);

        assertFalse(frameBuffer.hasMoreElements());

        motionDetector.processImage(loadTestImage("frame-1.png"));

        assertTrue(frameBuffer.hasMoreElements());
    }




}