package models.FrameProcessing;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;
import static test.GrimoireCollectionAsserts.*;
import static test.ReflectiveHelper.*;

/**
 * Created by Arthur on 1/11/2017.
 */
public class FrameProcessorTest {

    @Test
    public void testConstruction() throws Exception{
        FrameProcessor processor = new FrameProcessor();
        assertEmpty(processor.getFrames());
    }

    @Test
    public void testProcessFrameAddsFrameToFrames() throws Exception {
        FrameProcessor processor = new FrameProcessor();
        BufferedImage image = loadTestImage("frame-1.png");
        processor.processFrame(image);

        assertEquals(1, processor.getProcessedFrames().size());

        List points = getPrivateField(processor.getProcessedFrames().get(0), "points", List.class);
        denyEmpty(points);
    }

    @Test
    public void testProcessedFrameDetectsDirection() throws Exception {
        FrameProcessor processor = new FrameProcessor();
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