package models.FrameProcessing;

import models.PixelProcessing.Detectors.Gestures.ClusterGestureDetector;
import models.PixelProcessing.Detectors.Gestures.Direction;
import models.UserSettings;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class PointCluster {

    public Point rightMostPoint;
    public Point leftMostPoint;
    public Point topMostPoint;
    public Point bottomMostPoint;
    private final LinkedList<PointCluster> pastClusters;
    private BufferedImage image;
    private final ClusterGestureDetector detector;

    public PointCluster(BufferedImage image, Point point) {
        this.image = image;
        pastClusters = new LinkedList<>();
        setFirstPoint(point);
        detector = new ClusterGestureDetector(this);
    }

    public Direction getGestureDirection(){
        return detector.getGestureDirection();
    }

    public boolean contains(Point point) {
        return contains(point.xCoord, point.yCoord);
    }

    public boolean contains(int x, int y){
        int containsDistance = UserSettings.CLUSTER_CONTAINS_DISTANCE;
        boolean withinHorizontalBounds = x <= rightMostPoint.xCoord + containsDistance
                && x >= leftMostPoint.xCoord - containsDistance;
        boolean withinVerticalBounds = y >= topMostPoint.yCoord - containsDistance
                && y <= bottomMostPoint.yCoord + containsDistance;
        return withinHorizontalBounds && withinVerticalBounds;
    }

    public boolean envelops(PointCluster cluster){
        return this.contains(cluster.rightMostPoint) &&
                this.contains(cluster.leftMostPoint) &&
                this.contains(cluster.topMostPoint) &&
                this.contains(cluster.bottomMostPoint);
    }

    public boolean isPossibleWandPoint(){
        return isTheCorrectSize();
//                && averageSurroundingLuminosity() > UserSettings.SURROUNDING_BRIGHTNESS;
    }

    private boolean isTheCorrectSize(){
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

    public LinkedList<PointCluster> getPastClusters() {
        return this.pastClusters;
    }

    public void clearPastClusters(){
        this.pastClusters.clear();
    }
}
