package models.FrameProcessing;
import java.util.LinkedList;
import java.util.List;

public class PointCluster {

    private static final int FUDGE_FACTOR  = 20;
    private List<Point> points;
    public Point rightMostPoint;
    public Point leftMostPoint;
    public Point topMostPoint;
    public Point bottomMostPoint;

    public PointCluster(Point point) {
        this.points = new LinkedList<Point>();
        setFirstPoint(point);
        this.points.add(point);
    }

    public List<Point> getPoints() {
        return points;
    }

    public boolean contains(Point point) {
        final int fudgeFactor = 7;
        boolean withinHorizontalBounds = point.xCoord <= rightMostPoint.xCoord + fudgeFactor
                && point.xCoord >= leftMostPoint.xCoord - fudgeFactor;
        boolean withinVerticalBounds = point.yCoord >= topMostPoint.yCoord - fudgeFactor
                && point.yCoord <= bottomMostPoint.yCoord + fudgeFactor;
        return withinHorizontalBounds && withinVerticalBounds;
    }

    public void add(Point point){
        isBorderPoint(point);
        this.points.add(point);
    }

    private void setFirstPoint(Point point){
        rightMostPoint = point;
        leftMostPoint = point;
        topMostPoint = point;
        bottomMostPoint = point;
    }

    private void isBorderPoint(Point point){
        if(point.xCoord > this.rightMostPoint.xCoord)
            this.rightMostPoint = point;
        if(point.xCoord < this.leftMostPoint.xCoord)
            this.leftMostPoint = point;
        if(point.yCoord < this.topMostPoint.yCoord)
            this.topMostPoint = point;
        if(point.yCoord > this.bottomMostPoint.yCoord)
            this.bottomMostPoint = point;
    }

    public Point centerPoint(){
        int xWidth = rightMostPoint.distanceTo(leftMostPoint) / 2;
        int yWidth = topMostPoint.distanceTo(bottomMostPoint) / 2;
        return new Point(rightMostPoint.xCoord - xWidth, topMostPoint.yCoord - yWidth);
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

    private float yDifference(){
        return (lastPoint().yCoord - firstPoint().yCoord);
    }

    private float xDifference(){
        return (lastPoint().xCoord- firstPoint().xCoord);
    }

    private float slope(){
        return yDifference() / xDifference();
    }

    private boolean widthDidntChange() {
        return Math.abs(firstPoint().xCoord - lastPoint().xCoord) <= FUDGE_FACTOR;
    }

    private boolean heightDidntChange() {
        return Math.abs(firstPoint().yCoord - lastPoint().yCoord) <= FUDGE_FACTOR;
    }
}
