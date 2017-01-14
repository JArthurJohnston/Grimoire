package models.FrameProcessing;

import models.RuneKeeper;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static test.GrimoireCollectionAsserts.*;
import static test.ReflectiveHelper.*;

/**
 * Created by Arthur on 1/11/2017.
 */
public class FrameProcessorTest {

    @Test
    public void testConstruction() throws Exception{
        RuneKeeper keeper = mock(RuneKeeper.class);
        FrameProcessor processor = new FrameProcessor(keeper);

        assertEmpty(processor.getFrames());
        assertSame(keeper, processor.getRuneKeeper());
    }

    @Test
    public void testProcessFrameAddsFrameToFrames() throws Exception {
        FrameProcessor processor = new FrameProcessor(new RuneKeeper());
        BufferedImage image = loadTestImage("frame-1.png");
        processor.processFrame(image);

        assertEquals(1, processor.getProcessedFrames().size());

        List points = getPrivateField(processor.getProcessedFrames().get(0), "points", List.class);
        denyEmpty(points);
    }

    @Test
    public void testProcessedFrameDetectsDirection() throws Exception {
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

        assertSame(Direction.HORIZONTAL, frame1.getDirection());
        assertSame(Direction.DIAGONAL_NEGATIVE, frame2.getDirection());
        assertSame(Direction.VERTICAL, frame3.getDirection());
        assertSame(Direction.VERTICAL, frame4.getDirection());
        assertSame(Direction.NONE, frame5.getDirection());
        assertSame(Direction.DIAGONAL_POSITIVE, frame6.getDirection());
    }

    @Test
    public void testsProcessorDetectsMotion() throws Exception {
        FrameProcessor processor = new FrameProcessor(new RuneKeeper());

        assertFalse(processor.motionDetected());

        processor.processFrame(loadTestImage("frame-1.png"));

        assertFalse(processor.motionDetected());

        processor.processFrame(loadTestImage("frame-2.png"));

        assertTrue(processor.motionDetected());
        //motion detected is fale with only one frame

        //motion detected is false if the bright spots on frame B are close to the bright spots on frame A

        //motion detected is true if there are any new bright spots on frame B

        //motion detected is false if the new bright spots are too large
    }

    private BufferedImage loadTestImage(String imageName){
        File imageFile = new File("./src/test/resources/test-images/" + imageName);
        try {
            return ImageIO.read(imageFile);
        } catch (IOException e) {
            System.out.println("Could not load test image named: " + imageName);
//            e.printStackTrace();
        }
        return null;
    }
}