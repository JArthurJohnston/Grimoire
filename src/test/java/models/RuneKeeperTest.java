package models;

import models.FrameProcessing.Direction;
import org.junit.Test;

import static test.GrimoireCollectionAsserts.*;
import static org.junit.Assert.*;

/**
 * Created by Arthur on 1/13/2017.
 */
public class RuneKeeperTest {

    @Test
    public void testAddDirections() throws Exception {
        RuneKeeper keeper = new RuneKeeper();
        assertEmpty(keeper.getDirections());

        keeper.addDirection(Direction.VERTICAL);

        assertSame(Direction.VERTICAL, keeper.getDirections().get(0));
    }

    @Test
    public void testMovementsAggregateDirections() throws Exception {
        RuneKeeper keeper = new RuneKeeper();
        assertEmpty(keeper.getDirections());

        keeper.addDirection(Direction.VERTICAL);

    }

}