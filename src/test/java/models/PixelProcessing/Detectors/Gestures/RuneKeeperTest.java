package models.PixelProcessing.Detectors.Gestures;

import org.junit.Test;

import static org.junit.Assert.*;

public class RuneKeeperTest {

    @Test
    public void testSwipeLogsDirection() throws Exception{
        RuneKeeper runekeeper = new RuneKeeper(null);

        Direction expectedDirection = Direction.DOWNWARDS;
        runekeeper.swipe(expectedDirection);

        assertEquals(1, runekeeper.swipedDirections().size());
        assertSame(expectedDirection, runekeeper.swipedDirections().get(0));
    }

    @Test
    public void testSwipeOnlyLogsNewDirections() throws Exception{
        RuneKeeper runekeeper = new RuneKeeper(null);

        Direction expectedDirection = Direction.DOWNWARDS;
        runekeeper.swipe(expectedDirection);

        assertEquals(1, runekeeper.swipedDirections().size());
        assertSame(expectedDirection, runekeeper.swipedDirections().get(0));

        runekeeper.swipe(Direction.DOWNWARDS);

        assertEquals(1, runekeeper.swipedDirections().size());

        runekeeper.swipe(Direction.RIGHTWARDS);

        assertEquals(2, runekeeper.swipedDirections().size());
        assertSame(Direction.DOWNWARDS, runekeeper.swipedDirections().get(0));
        assertSame(Direction.RIGHTWARDS, runekeeper.swipedDirections().get(1));
    }



}