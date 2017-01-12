package models.FrameProcessing;

import java.util.LinkedList;

/**
 * Created by Arthur on 1/11/2017.
 */
public class ProcessedFrame {

    private static final int FUDGE_FACTOR = 26;
    private final LinkedList<Point> points;

    public ProcessedFrame(){
        points = new LinkedList<Point>();
    }

    public void addPoint(int x, int y){
        this.points.add(new Point(x, y));
    }

    public Direction getDirection(){
        if(heightDidntChange()){
            if(widthDidntChange()){
                return Direction.NONE;
            }
            return Direction.HORIZONTAL;
        } else if(widthDidntChange()) {
            return Direction.VERTICAL;
        } else if (slope() < 0) {
            return Direction.DIAGONAL_NEGATIVE;
        } else if (slope() > 0){
            return Direction.DIAGONAL_POSITIVE;
        }
        return Direction.NONE;
    }

    private Point firstPoint(){
        return points.get(0);
    }

    private Point lastPoint(){
        return points.get(points.size() - 1);
    }

    private float slope(){
        return yDifference() / xDifference();
    }

    private float yDifference(){
        return (lastPoint().getY() - firstPoint().getY()) * 1.0f;
    }

    private float xDifference(){
        return (lastPoint().getX() - firstPoint().getX()) * 1.0f;
    }

    private boolean widthDidntChange() {
        return Math.abs(firstPoint().getX() - lastPoint().getX()) <= FUDGE_FACTOR;
    }

    private boolean heightDidntChange() {
        return Math.abs(firstPoint().getY() - lastPoint().getY()) <= FUDGE_FACTOR;
    }

}
