package models.FrameProcessing;

public class PointMath {


    public static float slope(final Point one, final Point two){
        return (two.yCoord - one.yCoord) /
                (float)(two.xCoord - one.xCoord);
    }

    public static boolean isDiagonal(float slope){
        float absSlope = Math.abs(slope);
        return absSlope >= 0.66 && absSlope <= 1.66;
    }
}
