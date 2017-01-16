package models.FrameProcessing;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Arthur on 1/14/2017.
 */
public class ProcessedFrameTest {

    @Test
    public void testClustersPointsTogether() throws Exception {
        ProcessedFrame frame = new ProcessedFrame();

        frame.addPoint(4, 8);
        frame.addPoint(3, 7);
        frame.addPoint(5, 6);
        frame.addPoint(3, 5);

        assertEquals(1, frame.getClusters().size());
    }
}