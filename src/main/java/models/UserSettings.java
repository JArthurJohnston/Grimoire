package models;

/**
 * Created by arthur on 22/01/17.
 */
public class UserSettings {
    public static final int DEFAULT_SCANLINES = 3;
    public static final int DEFAULT_MOTION_DETECTION_RADIUS = 15;
    public static final float DEFAULT_LUMINESCENCE_THREASHOLD = 8.0f;
    public static final int DEFAULT_LUMEN_DISPLAY_VALUE = 800;
    public static final int DEFAULT_CLUSTER_DISTANCE = 19;
    private static final int CONTRAST = 122;
    private static final int BRIGHTNESS = -50;

    public static int SCANLINES = DEFAULT_SCANLINES;
    public static int MOTION_DETECTION_RADIUS = DEFAULT_MOTION_DETECTION_RADIUS;
    public static float LUMINESCENCE_THREASHOLD = DEFAULT_LUMINESCENCE_THREASHOLD;
    public static int WAND_POINT_SIZE = 25;
    public static int CLUSTER_CONTAINS_DIAMETER = DEFAULT_CLUSTER_DISTANCE;

}
