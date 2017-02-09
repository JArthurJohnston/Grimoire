package models;

public class UserSettings {

    public static int SCANLINES = Default.SCANLINES;
    public static int MOTION_DETECTION_RADIUS = Default.MOTION_DETECTION_RADIUS;
    public static float LUMINESCENCE_THRESHOLD = Default.LUMINESCENCE_THRESHOLD;
    public static int MAX_WAND_POINT_SIZE = 30;
    public static int MIN_WAND_POINT_SIZE = 0;
    public static int CLUSTER_CONTAINS_DISTANCE = Default.CLUSTER_CONTAINS_DISTANCE;
    public static boolean SHOULD_GRAYSCALE_IMAGE = Default.GRAYSCALE;
    public static int BLUR_SIZE = Default.BLUR_SIZE;
    public static float SURROUNDING_BRIGHTNESS = Default.SURROUNDING_BRIGHTNESS / 1000.0f;
    public static int GESTURE_DETECTION_DISTANCE = Default.GESTURE_DETECTION_DISTANCE;

    public class Default {
        public static final int SCANLINES = 3;
        public static final int MOTION_DETECTION_RADIUS = 25;
        public static final float LUMINESCENCE_THRESHOLD = 7.0f;
        public static final int CLUSTER_CONTAINS_DISTANCE = 19;
        public static final boolean GRAYSCALE = false;
        public static final int LUMEN_DISPLAY_VALUE = 800;
        public static final int BRIGHTNESS = -10;
        public static final int BLUR_SIZE = 3;
        public static final int SURROUNDING_BRIGHTNESS = 600;
        public static final int GESTURE_DETECTION_DISTANCE = 80;
    }

    public static void reset(){
        SCANLINES = Default.SCANLINES;
        MOTION_DETECTION_RADIUS = Default.MOTION_DETECTION_RADIUS;
        LUMINESCENCE_THRESHOLD = Default.LUMINESCENCE_THRESHOLD;
        SHOULD_GRAYSCALE_IMAGE = Default.GRAYSCALE;
        BLUR_SIZE = Default.BLUR_SIZE;
        SURROUNDING_BRIGHTNESS = Default.SURROUNDING_BRIGHTNESS / 1000.0f;
        CLUSTER_CONTAINS_DISTANCE = Default.CLUSTER_CONTAINS_DISTANCE;
        GESTURE_DETECTION_DISTANCE = Default.GESTURE_DETECTION_DISTANCE;
    }
}
