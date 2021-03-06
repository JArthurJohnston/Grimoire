package models.PixelProcessing.Detectors.Gestures;

/**
 * Created by arthur on 24/01/17.
 */
public enum  Direction {

    NONE("NONE"), UPWARDS("Up"), DOWNWARDS("Down"), LEFTWARDS("Left"), RIGHTWARDS("Right"),
    UPWARDS_RIGHT("Up Right"), UPWARDS_LEFT("Up Left"), DOWNWARDS_RIGHT("Down Right"), DOWNWARDS_LEFT("Down Left");

    public final String label;
    Direction(String label){
        this.label = label;
    }

}
