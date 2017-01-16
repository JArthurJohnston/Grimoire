package models.FrameProcessing;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Arthur on 1/14/2017.
 */
public class PointClusterTest {

    @Test
    public void testConstruction() throws Exception {
        Point point = new Point(4, 5);
        PointCluster cluster = new PointCluster(point);

        assertEquals(1, cluster.getPoints().size());
        assertSame(point, cluster.getPoints().get(0));
    }

    @Test
    public void testClusterContainsPoint() throws Exception {
        Point point = new Point(4, 5);
        PointCluster cluster = new PointCluster(point);

        assertTrue(cluster.contains(new Point(4, 9)));
        assertTrue(cluster.contains(new Point(8, 5)));

        assertFalse(cluster.contains(new Point(4, 11)));
        assertFalse(cluster.contains(new Point(10, 5)));
    }

    @Test
    public void testRightMostPoint() throws Exception {
        Point point = new Point(114, 50);
        PointCluster cluster = new PointCluster(point);

        assertSame(point, cluster.rightMostPoint);

        Point point2 = new Point(115, 50);
        cluster.add(point2);

        assertSame(point2, cluster.rightMostPoint);
    }

    @Test
    public void testLeftMostPoint() throws Exception {
        Point point = new Point(114, 50);
        PointCluster cluster = new PointCluster(point);

        assertSame(point, cluster.leftMostPoint);

        Point point2 = new Point(113, 50);
        cluster.add(point2);

        assertSame(point2, cluster.leftMostPoint);
    }

    @Test
    public void testTopMostPoint() throws Exception {
        Point point = new Point(114, 51);
        PointCluster cluster = new PointCluster(point);

        assertSame(point, cluster.topMostPoint);

        Point point2 = new Point(113, 50);
        cluster.add(point2);

        assertSame(point2, cluster.topMostPoint);
    }

    @Test
    public void testBottomMostPoint() throws Exception {
        Point point = new Point(114, 49);
        PointCluster cluster = new PointCluster(point);

        assertSame(point, cluster.bottomMostPoint);

        Point point2 = new Point(113, 50);
        cluster.add(point2);

        assertSame(point2, cluster.bottomMostPoint);
    }

    @Test
    public void testCenterPoint() throws Exception {
        Point rightPoint = new Point(0, 50);
        PointCluster cluster = new PointCluster(rightPoint);
        Point topPoint = new Point(50, 0);
        cluster.add(topPoint);
        Point leftPoint = new Point(100, 50);
        cluster.add(leftPoint);
        Point bottomPoint = new Point(50, 100);
        cluster.add(bottomPoint);

        Point centerPoint = cluster.centerPoint();

        assertEquals(50, centerPoint.xCoord);
        assertEquals(-50, centerPoint.yCoord);
    }
}