package models.FrameProcessing;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Arthur on 1/14/2017.
 */
public class PointTest {

    @Test
    public void testDistanceBetweenPoints() throws Exception {
        Point pointA = new Point(3, 4);
        Point pointB = new Point(34, 12);

        int distance = pointA.distanceTo(pointB);

        assertEquals(32, distance);
    }

}