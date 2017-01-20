package models.FrameProcessing;

import org.junit.Test;
import java.util.LinkedList;
import static org.junit.Assert.*;

public class FrameBufferTest {

    @Test
    public void testAddClusterCollection() throws Exception{
        FrameBuffer frameBuffer = new FrameBuffer(5);
        ClusterCollection expectedClusters1 = new ClusterCollection(new LinkedList<PointCluster>());
        ClusterCollection expectedClusters2 = new ClusterCollection(new LinkedList<PointCluster>());

        frameBuffer.push(expectedClusters1);

        assertSame(expectedClusters1, frameBuffer.getFirst());
        assertSame(expectedClusters1, frameBuffer.getLast());

        frameBuffer.push(expectedClusters2);

        assertSame(expectedClusters1, frameBuffer.getFirst());
        assertSame(expectedClusters2, frameBuffer.getLast());
    }

    @Test
    public void testAddOverridesFirstClustersWhenCapacityIsReached() throws Exception{
        ClusterCollection expectedCluster1 = new ClusterCollection(new LinkedList<PointCluster>());
        ClusterCollection expectedCluster2 = new ClusterCollection(new LinkedList<PointCluster>());
        ClusterCollection expectedCluster3 = new ClusterCollection(new LinkedList<PointCluster>());
        ClusterCollection expectedCluster4 = new ClusterCollection(new LinkedList<PointCluster>());
        ClusterCollection expectedCluster5 = new ClusterCollection(new LinkedList<PointCluster>());
        ClusterCollection expectedCluster6 = new ClusterCollection(new LinkedList<PointCluster>());

        FrameBuffer frameBuffer = new FrameBuffer(3);
        ClusterCollection[] initialClusters = {
                expectedCluster1, expectedCluster2, expectedCluster3
        };
        addArraysToBuffer(frameBuffer, initialClusters);

        assertSame(expectedCluster1, frameBuffer.getFirst());
        assertSame(expectedCluster3, frameBuffer.getLast());

        frameBuffer.push(expectedCluster4);

        assertSame(expectedCluster2, frameBuffer.getFirst());
        assertSame(expectedCluster4, frameBuffer.getLast());

        frameBuffer.push(expectedCluster5);

        assertSame(expectedCluster3, frameBuffer.getFirst());
        assertSame(expectedCluster5, frameBuffer.getLast());

        frameBuffer.push(expectedCluster6);

        assertSame(expectedCluster4, frameBuffer.getFirst());
        assertSame(expectedCluster6, frameBuffer.getLast());
    }

    @Test
    public void testIterateOverBuffer() throws Exception{
        ClusterCollection expectedCluster1 = new ClusterCollection(new LinkedList<PointCluster>());

        FrameBuffer frameBuffer = new FrameBuffer(6);

        assertFalse(frameBuffer.hasMoreElements());

        frameBuffer.push(expectedCluster1);

        assertTrue(frameBuffer.hasMoreElements());

        ClusterCollection nextElement = frameBuffer.nextElement();

        assertSame(expectedCluster1, nextElement);

        assertFalse(frameBuffer.hasMoreElements());
    }

    @Test
    public void testIterateOverBufferAtCapacity() throws Exception{
        ClusterCollection expectedCluster1 = new ClusterCollection(new LinkedList<PointCluster>());
        ClusterCollection expectedCluster2 = new ClusterCollection(new LinkedList<PointCluster>());
        ClusterCollection expectedCluster3 = new ClusterCollection(new LinkedList<PointCluster>());

        FrameBuffer frameBuffer = new FrameBuffer(3);

        assertFalse(frameBuffer.hasMoreElements());

        frameBuffer.push(expectedCluster1);
        frameBuffer.push(expectedCluster2);
        frameBuffer.push(expectedCluster3);

        assertTrue(frameBuffer.hasMoreElements());
        assertSame(expectedCluster1, frameBuffer.nextElement());
        assertTrue(frameBuffer.hasMoreElements());
        assertSame(expectedCluster2, frameBuffer.nextElement());
        assertTrue(frameBuffer.hasMoreElements());
        assertSame(expectedCluster3, frameBuffer.nextElement());
        assertFalse(frameBuffer.hasMoreElements());
    }

    @Test
    public void testResetsIterableIndexWhenNewClusterCollectionIsAdded() throws Exception{
        ClusterCollection expectedCluster1 = new ClusterCollection(new LinkedList<PointCluster>());
        ClusterCollection expectedCluster2 = new ClusterCollection(new LinkedList<PointCluster>());
        ClusterCollection expectedCluster3 = new ClusterCollection(new LinkedList<PointCluster>());

        FrameBuffer frameBuffer = new FrameBuffer(3);
        addArraysToBuffer(frameBuffer, new ClusterCollection[]{expectedCluster1, expectedCluster2});

        assertSame(expectedCluster1, frameBuffer.nextElement());
        assertSame(expectedCluster2, frameBuffer.nextElement());

        assertFalse(frameBuffer.hasMoreElements());

        frameBuffer.push(expectedCluster3);

        assertTrue(frameBuffer.hasMoreElements());

        assertSame(expectedCluster1, frameBuffer.nextElement());
        assertSame(expectedCluster2, frameBuffer.nextElement());
        assertSame(expectedCluster3, frameBuffer.nextElement());
    }
    
    private void addArraysToBuffer(FrameBuffer buffer, ClusterCollection[] clusters){
        for (ClusterCollection cluster : clusters) {
            buffer.push(cluster);
        }

    }


}