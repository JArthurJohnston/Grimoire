package models.FrameProcessing;

import org.junit.Test;
import models.RuneKeeper;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static test.GrimoireCollectionAsserts.*;
import static test.ImageTestHelper.loadTestImage;

public class FrameProcessorTest {

    @Test
    public void testConstruction() throws Exception{
        RuneKeeper keeper = mock(RuneKeeper.class);
        FrameProcessor processor = new FrameProcessor(keeper);

        assertEmpty(processor.getFrames());
        assertSame(keeper, processor.getRuneKeeper());
    }

    @Test
    public void functionalTestProcessFrameClustersPoints() throws Exception {
        FrameProcessor processor = new FrameProcessor(new RuneKeeper());
        processor.processFrame(loadTestImage("frame-1.png"));
        processor.processFrame(loadTestImage("frame-2.png"));
        processor.processFrame(loadTestImage("frame-3.png"));
        processor.processFrame(loadTestImage("frame-4.png"));
        processor.processFrame(loadTestImage("frame-5.png"));
        processor.processFrame(loadTestImage("frame-6.png"));

        ProcessedFrame frame1 = processor.getFrames().get(0);
        ProcessedFrame frame2 = processor.getFrames().get(1);
        ProcessedFrame frame3 = processor.getFrames().get(2);
        ProcessedFrame frame4 = processor.getFrames().get(3);
        ProcessedFrame frame5 = processor.getFrames().get(4);
        ProcessedFrame frame6 = processor.getFrames().get(5);

        assertEquals(1, frame1.getClusters().size());
        assertEquals(1, frame2.getClusters().size());
        assertEquals(1, frame3.getClusters().size());
        assertEquals(1, frame4.getClusters().size());
        assertEquals(1, frame5.getClusters().size());
        assertEquals(1, frame6.getClusters().size());
    }

}