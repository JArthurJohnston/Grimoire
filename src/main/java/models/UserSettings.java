package models;

public class UserSettings {

    public static int SCANLINES = Default.SCANLINES;
    public static int MOTION_DETECTION_RADIUS = Default.MOTION_DETECTION_RADIUS;
    public static float LUMINESCENCE_THREASHOLD = Default.LUMINESCENCE_THREASHOLD;
    public static int WAND_POINT_SIZE = 25;
    public static int CLUSTER_CONTAINS_DIAMETER = Default.CLUSTER_DISTANCE;
    public static boolean SHOULD_GRAYSCALE_IMAGE = Default.GRAYSCALE;
    public static int BLUR_SIZE = Default.BLUR_SIZE;

    public class Default {
        public static final int SCANLINES = 3;
        public static final int MOTION_DETECTION_RADIUS = 15;
        public static final float LUMINESCENCE_THREASHOLD = 8.0f;
        public static final int CLUSTER_DISTANCE = 19;
        public static final boolean GRAYSCALE = false;
        public static final int LUMEN_DISPLAY_VALUE = 800;
        public static final int CONTRAST = 122;
        public static final int BRIGHTNESS = -50;
        public static final int BLUR_SIZE = 3;
    }
}
