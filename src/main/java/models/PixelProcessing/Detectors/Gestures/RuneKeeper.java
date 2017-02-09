package models.PixelProcessing.Detectors.Gestures;

import models.PixelProcessing.Detectors.MotionDetector;

import java.util.LinkedList;
import java.util.List;

public class RuneKeeper {

    private final LinkedList<Direction> swipedDirections;
    private MotionDetector motionDetector;

    public RuneKeeper(MotionDetector motionDetector){
        this.motionDetector = motionDetector;
        swipedDirections = new LinkedList();
    }

    public void swipe(Direction direction){
        if(swipedDirections.isEmpty() || swipedDirections.getLast() != direction){
            System.out.println(direction.label);
            swipedDirections.add(direction);
        }
        motionDetector.resetBuffer();
    }

    public List<Direction> swipedDirections() {
        return this.swipedDirections;
    }
}
