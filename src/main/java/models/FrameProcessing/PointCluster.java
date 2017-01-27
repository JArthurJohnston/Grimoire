package models.FrameProcessing;

import models.PixelProcessing.Detectors.Gestures.GestureDetector;
import models.UserSettings;
import java.util.LinkedList;
import java.util.List;

public class PointCluster {

    public Point rightMostPoint;
    public Point leftMostPoint;
    public Point topMostPoint;
    public Point bottomMostPoint;
    private final List<PointCluster> pastClusters;
    private final GestureDetector gestureDetector;

    public PointCluster(Point point) {
        gestureDetector = new GestureDetector();
        pastClusters = new LinkedList<>();
        setFirstPoint(point);
    }

    public boolean contains(Point point) {
        return contains(point.xCoord, point.yCoord);
    }

    public boolean contains(int x, int y){
        int containsDiameter = UserSettings.CLUSTER_CONTAINS_DIAMETER;
        boolean withinHorizontalBounds = x <= rightMostPoint.xCoord + containsDiameter
                && x >= leftMostPoint.xCoord - containsDiameter;
        boolean withinVerticalBounds = y >= topMostPoint.yCoord - containsDiameter
                && y <= bottomMostPoint.yCoord + containsDiameter;
        return withinHorizontalBounds && withinVerticalBounds;
    }
    public boolean envelops(PointCluster cluster){
        return this.contains(cluster.rightMostPoint) &&
                this.contains(cluster.leftMostPoint) &&
                this.contains(cluster.topMostPoint) &&
                this.contains(cluster.bottomMostPoint);
    }

    public boolean isPossibleWandPoint(){
        int width = width();
        int height = height();
        boolean lessThanMaxWandSize = width < UserSettings.MAX_WAND_POINT_SIZE && height < UserSettings.MAX_WAND_POINT_SIZE;
        boolean greaterThanMinWandSize = width > UserSettings.MIN_WAND_POINT_SIZE && height > UserSettings.MIN_WAND_POINT_SIZE;
        return lessThanMaxWandSize && greaterThanMinWandSize;
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

    public boolean addPastCluster(PointCluster cluster){
        if(!this.pastClusters.contains(cluster)){
            this.pastClusters.add(cluster);
            return true;
        }
        return false;
    }

    public List<PointCluster> getPastClusters() {
        return this.pastClusters;
    }
}
