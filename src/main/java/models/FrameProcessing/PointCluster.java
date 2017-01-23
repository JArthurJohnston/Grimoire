package models.FrameProcessing;

import java.util.LinkedList;
import java.util.List;

public class PointCluster {

    private static final int FUDGE_FACTOR  = 7;
    public Point rightMostPoint;
    public Point leftMostPoint;
    public Point topMostPoint;
    public Point bottomMostPoint;
    private final List<PointCluster> pastClusters;

    public PointCluster(Point point) {
        pastClusters = new LinkedList<PointCluster>();
        setFirstPoint(point);
    }

    public boolean contains(Point point) {
        return contains(point.xCoord, point.yCoord);
    }

    public boolean contains(int x, int y){
        boolean withinHorizontalBounds = x <= rightMostPoint.xCoord + FUDGE_FACTOR
                && x >= leftMostPoint.xCoord - FUDGE_FACTOR;
        boolean withinVerticalBounds = y >= topMostPoint.yCoord - FUDGE_FACTOR
                && y <= bottomMostPoint.yCoord + FUDGE_FACTOR;
        return withinHorizontalBounds && withinVerticalBounds;
    }
    public boolean envelops(PointCluster cluster){
        return this.contains(cluster.rightMostPoint) &&
                this.contains(cluster.leftMostPoint) &&
                this.contains(cluster.topMostPoint) &&
                this.contains(cluster.bottomMostPoint);
    }

    public boolean isPossibleWandPoint(){
        final int UPPER_FUDGE_FACTOR = 25;
        final int LOWER_FUDGE_FACTOR = 0;
        int width = width();
        int height = height();
        return width < UPPER_FUDGE_FACTOR && height < UPPER_FUDGE_FACTOR;
//        return width < UPPER_FUDGE_FACTOR || width > LOWER_FUDGE_FACTOR &&
//                height < UPPER_FUDGE_FACTOR || height > LOWER_FUDGE_FACTOR;
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

    public void addPastCluster(PointCluster cluster){
        this.pastClusters.add(cluster);
    }

    public List<PointCluster> getPastClusters(){
        return this.pastClusters;
//        List<PointCluster> pointClusters;
//        if(pastClusters == null){
//            pointClusters = new LinkedList<PointCluster>();
//            pointClusters.add(this);
//            return pointClusters;
//        }
//        pointClusters = pastClusters.getPastClusters();
//        pointClusters.add(this);
//        return null;
    }
}
