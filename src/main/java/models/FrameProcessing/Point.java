package models.FrameProcessing;

public class Point {

    public final int xCoord;
    public final int yCoord;

    public Point(final int xCoord, final int yCoord){
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    public int distanceTo(Point anotherPoint){
        return (int)Math.sqrt(
                Math.pow(this.xCoord - anotherPoint.xCoord, 2) +
                        Math.pow(this.yCoord - anotherPoint.yCoord, 2));
    }
}
