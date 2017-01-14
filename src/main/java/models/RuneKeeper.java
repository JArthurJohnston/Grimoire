package models;

import models.FrameProcessing.Direction;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Arthur on 1/13/2017.
 */
public class RuneKeeper {

    private final LinkedList<Direction> directions;

    public RuneKeeper(){
        directions = new LinkedList<Direction>();
    }

    public void addDirection(Direction direction){

    }

    public List<Direction> getDirections(){
        return this.directions;
    }
}
