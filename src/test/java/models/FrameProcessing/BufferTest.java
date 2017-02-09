package models.FrameProcessing;

import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class BufferTest {

    @Test
    public void testAddClusterCollection() throws Exception{
        Buffer buffer = new Buffer(5);
        FrameData expectedClusters1 = new FrameData();
        FrameData expectedClusters2 = new FrameData();

        buffer.add(expectedClusters1);

        assertSame(expectedClusters1, buffer.getFirst());
        assertSame(expectedClusters1, buffer.getLast());

        buffer.add(expectedClusters2);

        assertSame(expectedClusters1, buffer.getFirst());
        assertSame(expectedClusters2, buffer.getLast());
    }

    @Test
    public void testAddOverridesFirstClustersWhenCapacityIsReached() throws Exception{
        FrameData expectedCluster1 = new FrameData();
        FrameData expectedCluster2 = new FrameData();
        FrameData expectedCluster3 = new FrameData();
        FrameData expectedCluster4 = new FrameData();
        FrameData expectedCluster5 = new FrameData();
        FrameData expectedCluster6 = new FrameData();

        Buffer buffer = new Buffer(3);
        FrameData[] initialClusters = {
                expectedCluster1, expectedCluster2, expectedCluster3
        };
        addArrayToBuffer(buffer, initialClusters);

        assertSame(expectedCluster1, buffer.getFirst());
        assertSame(expectedCluster3, buffer.getLast());

        buffer.add(expectedCluster4);

        assertSame(expectedCluster2, buffer.getFirst());
        assertSame(expectedCluster4, buffer.getLast());

        buffer.add(expectedCluster5);

        assertSame(expectedCluster3, buffer.getFirst());
        assertSame(expectedCluster5, buffer.getLast());

        buffer.add(expectedCluster6);

        assertSame(expectedCluster4, buffer.getFirst());
        assertSame(expectedCluster6, buffer.getLast());
    }

    @Test
    public void testAccessElementByIndex() throws Exception{
        Buffer<String> buffer = new Buffer<String>(4);
        String value1 = "hello";
        String value2 = "world";
        String value3 = "foo";
        String value4 = "bar";
        addArrayToBuffer(buffer, new String[]{value1, value2, value3, value4});

        assertSame(value1, buffer.get(0));
        assertSame(value2, buffer.get(1));
        assertSame(value3, buffer.get(2));
        assertSame(value4, buffer.get(3));
    }

    @Test
    public void testAccessElementsByIndex_afterCapacityReached() throws Exception{
        Buffer<String> buffer = new Buffer<String>(3);
        String value1 = "hello";
        String value2 = "world";
        String value3 = "foo";
        String value4 = "bar";

        assertNull(buffer.get(0));

        addArrayToBuffer(buffer, new String[]{value1, value2, value3, value4});

        assertSame(value2, buffer.get(0));
        assertSame(value3, buffer.get(1));
        assertSame(value4, buffer.get(2));
    }

    @Test
    public void testIterator_afterCapacityReached() throws Exception{
        Buffer<String> buffer = new Buffer<String>(3);
        String value1 = "hello";
        String value2 = "world";
        String value3 = "foo";
        String value4 = "bar";
        addArrayToBuffer(buffer, new String[]{value1, value2, value3});
        buffer.add(value4);

        Iterator<String> iterator = buffer.iterator();

        assertTrue(iterator.hasNext());
        assertEquals(value2, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(value3, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(value4, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testIteratorBeforeCapacityReached() throws Exception{
        Buffer<String> buffer = new Buffer<String>(5);
        String value1 = "hello";
        String value2 = "world";
        String value3 = "foo";
        addArrayToBuffer(buffer, new String[]{value1, value2, value3});

        Iterator<String> iterator = buffer.iterator();

        assertTrue(iterator.hasNext());
        assertEquals(value1, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(value2, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(value3, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testIteratorWhenCapacityReached() throws Exception{
        Buffer<String> buffer = new Buffer<String>(3);
        String value1 = "hello";
        String value2 = "world";
        String value3 = "foo";
        addArrayToBuffer(buffer, new String[]{value1, value2, value3});

        Iterator<String> iterator = buffer.iterator();

        assertTrue(iterator.hasNext());
        assertEquals(value1, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(value2, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(value3, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testIteratorWhenEmpty() throws Exception{
        Buffer<String> buffer = new Buffer<String>(3);
        BufferIterator iterator = buffer.iterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testBufferThrowsExceptionWhenBuiltWithCapacityOfZero() throws Exception{
        try {
            new Buffer<String>(0);
            fail("should not reach here");
        } catch (RuntimeException e){
            assertEquals("Cannot instantiate Buffer with capacity of 0", e.getMessage());
        }
    }

    private void addArrayToBuffer(Buffer buffer, Object[] clusters){
        for (Object cluster : clusters) {
            buffer.add(cluster);
        }

    }


}