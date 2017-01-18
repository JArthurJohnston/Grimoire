package models.FrameProcessing;
import java.util.LinkedList;
import java.util.List;

public class PointCluster {

    private static final int FUDGE_FACTOR  = 20;
    public Point rightMostPoint;
    public Point leftMostPoint;
    public Point topMostPoint;
    public Point bottomMostPoint;

    public PointCluster(Point point) {
        setFirstPoint(point);
    }

    public boolean contains(Point point) {
        return contains(point.xCoord, point.yCoord);
    }

    public boolean contains(int x, int y){
        final int fudgeFactor = 7;
        boolean withinHorizontalBounds = x <= rightMostPoint.xCoord + fudgeFactor
                && x >= leftMostPoint.xCoord - fudgeFactor;
        boolean withinVerticalBounds = y >= topMostPoint.yCoord - fudgeFactor
                && y <= bottomMostPoint.yCoord + fudgeFactor;
        return withinHorizontalBounds && withinVerticalBounds;
    }

    public boolean contains(PointCluster otherCluster){
        return false;
    }

    public void add(Point point){
        isBorderPoint(point);
    }

    public int width(){
        return rightMostPoint.xCoord - leftMostPoint.xCoord;
    }

    public int height(){
        return bottomMostPoint.yCoord - topMostPoint.yCoord;
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
}
