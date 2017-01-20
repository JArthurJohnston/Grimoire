package models.FrameProcessing;

import org.junit.Test;

import static org.junit.Assert.*;

public class BufferTest {

    @Test
    public void testAddClusterCollection() throws Exception{
        Buffer buffer = new Buffer(5);
        ClusterCollection expectedClusters1 = new ClusterCollection();
        ClusterCollection expectedClusters2 = new ClusterCollection();

        buffer.push(expectedClusters1);

        assertSame(expectedClusters1, buffer.getFirst());
        assertSame(expectedClusters1, buffer.getLast());

        buffer.push(expectedClusters2);

        assertSame(expectedClusters1, buffer.getFirst());
        assertSame(expectedClusters2, buffer.getLast());
    }

    @Test
    public void testAddOverridesFirstClustersWhenCapacityIsReached() throws Exception{
        ClusterCollection expectedCluster1 = new ClusterCollection();
        ClusterCollection expectedCluster2 = new ClusterCollection();
        ClusterCollection expectedCluster3 = new ClusterCollection();
        ClusterCollection expectedCluster4 = new ClusterCollection();
        ClusterCollection expectedCluster5 = new ClusterCollection();
        ClusterCollection expectedCluster6 = new ClusterCollection();

        Buffer buffer = new Buffer(3);
        ClusterCollection[] initialClusters = {
                expectedCluster1, expectedCluster2, expectedCluster3
        };
        addArraysToBuffer(buffer, initialClusters);

        assertSame(expectedCluster1, buffer.getFirst());
        assertSame(expectedCluster3, buffer.getLast());

        buffer.push(expectedCluster4);

        assertSame(expectedCluster2, buffer.getFirst());
        assertSame(expectedCluster4, buffer.getLast());

        buffer.push(expectedCluster5);

        assertSame(expectedCluster3, buffer.getFirst());
        assertSame(expectedCluster5, buffer.getLast());

        buffer.push(expectedCluster6);

        assertSame(expectedCluster4, buffer.getFirst());
        assertSame(expectedCluster6, buffer.getLast());
    }

    @Test
    public void testIterateOverBuffer() throws Exception{
        ClusterCollection expectedCluster1 = new ClusterCollection();

        Buffer buffer = new Buffer<ClusterCollection>(6);

        assertFalse(buffer.hasMoreElements());

        buffer.push(expectedCluster1);

        assertTrue(buffer.hasMoreElements());

        Object nextElement = buffer.nextElement();

        assertSame(expectedCluster1, nextElement);

        assertFalse(buffer.hasMoreElements());
    }

    @Test
    public void testIterateOverBufferAtCapacity() throws Exception{
        ClusterCollection expectedCluster1 = new ClusterCollection();
        ClusterCollection expectedCluster2 = new ClusterCollection();
        ClusterCollection expectedCluster3 = new ClusterCollection();

        Buffer buffer = new Buffer(3);

        assertFalse(buffer.hasMoreElements());

        buffer.push(expectedCluster1);
        buffer.push(expectedCluster2);
        buffer.push(expectedCluster3);

        assertTrue(buffer.hasMoreElements());
        assertSame(expectedCluster1, buffer.nextElement());
        assertTrue(buffer.hasMoreElements());
        assertSame(expectedCluster2, buffer.nextElement());
        assertTrue(buffer.hasMoreElements());
        assertSame(expectedCluster3, buffer.nextElement());
        assertFalse(buffer.hasMoreElements());
    }

    @Test
    public void testResetsIterableIndexWhenNewClusterCollectionIsAdded() throws Exception{
        ClusterCollection expectedCluster1 = new ClusterCollection();
        ClusterCollection expectedCluster2 = new ClusterCollection();
        ClusterCollection expectedCluster3 = new ClusterCollection();

        Buffer buffer = new Buffer(3);
        addArraysToBuffer(buffer, new ClusterCollection[]{expectedCluster1, expectedCluster2});

        assertSame(expectedCluster1, buffer.nextElement());
        assertSame(expectedCluster2, buffer.nextElement());

        assertFalse(buffer.hasMoreElements());

        buffer.push(expectedCluster3);

        assertTrue(buffer.hasMoreElements());

        assertSame(expectedCluster1, buffer.nextElement());
        assertSame(expectedCluster2, buffer.nextElement());
        assertSame(expectedCluster3, buffer.nextElement());
    }
    
    private void addArraysToBuffer(Buffer buffer, ClusterCollection[] clusters){
        for (ClusterCollection cluster : clusters) {
            buffer.push(cluster);
        }

    }


}