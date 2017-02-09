package models.Cameras;

import models.FrameProcessing.*;
import models.FrameProcessing.Point;
import models.ImageProcessing.ImageFileCapture.ImageWriter;
import models.PixelProcessing.Detectors.Gestures.Direction;
import models.PixelProcessing.Detectors.Gestures.RuneKeeper;
import models.PixelProcessing.Detectors.MotionDetector;
import models.UserSettings;
import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;
import java.awt.*;
import java.awt.image.BufferedImage;

import static org.bytedeco.javacpp.opencv_imgproc.*;

public class MotionDetectingRecorder {

    private final MotionDetector motionDetector;
    private final Java2DFrameConverter java2DFrameConverter;
    private final RuneKeeper runekeeper;
    public ImageWriter writer;
    private final Camera camera;
    private OpenCVFrameConverter.ToIplImage toIplImage;

    public MotionDetectingRecorder(MotionDetector detector, Camera camera){
        this.camera = camera;
        motionDetector = detector;
        java2DFrameConverter = new Java2DFrameConverter();
        toIplImage = new OpenCVFrameConverter.ToIplImage();
        runekeeper = new RuneKeeper(motionDetector);
    }

    public Frame getFrame(){
        Frame frame = camera.getFrame();
        if(frame != null){
            BufferedImage bufferedImage = java2DFrameConverter.getBufferedImage(blurImage(frame));
            FrameData clusterCollection = motionDetector.processImage(bufferedImage);
            drawBrightspotIds(bufferedImage, clusterCollection);
            if(writer != null){
                writer.process(bufferedImage);
            }
            return java2DFrameConverter.convert(bufferedImage);
        }
        return new Frame();
    }

    private static final int GAUSSIAN_STANDARD_DEV = 1;

    private Frame blurImage(Frame frame) {
        opencv_core.IplImage iplImage = toIplImage.convertToIplImage(frame);
        int blurSize = UserSettings.BLUR_SIZE;
        cvSmooth(iplImage, iplImage, CV_BLUR, blurSize, blurSize,
                GAUSSIAN_STANDARD_DEV, GAUSSIAN_STANDARD_DEV);
        return toIplImage.convert(iplImage);
    }

    private void drawBrightspotIds(BufferedImage bufferedImage, FrameData clusters) {
        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.setColor(Color.RED);
        for (PointCluster cluster : clusters.clusters) {
            if(cluster.isPossibleWandPoint()){
                drawWandPoint(graphics, cluster);
                graphics.setColor(Color.RED);
            } else {
                drawRectangleForCluster(graphics, cluster);
            }
        }
        graphics.dispose();
    }

    private void drawWandPoint(Graphics2D graphics, PointCluster cluster) {
        graphics.setColor(Color.CYAN);
        drawRectangleForCluster(graphics, cluster);
        models.FrameProcessing.Point point = cluster.centerPoint();
        graphics.drawString("WAND?", point.xCoord, point.yCoord);
        drawMotionTail(graphics, cluster);
    }

    private void drawMotionTail(Graphics2D graphics, PointCluster cluster){
        PointCluster currentCluster = cluster;
        for (PointCluster pointCluster : cluster.getPastClusters()) {
            graphics.drawLine(currentCluster.centerPoint().xCoord, currentCluster.centerPoint().yCoord,
                    pointCluster.centerPoint().xCoord, pointCluster.centerPoint().yCoord);
            currentCluster = pointCluster;
        }
        if(!cluster.getPastClusters().isEmpty()){
            graphics.setColor(Color.MAGENTA);
            drawGestureLine(graphics, cluster);
        }
    }

    private void drawGestureLine(Graphics2D graphics, PointCluster cluster) {
        Point currentPoint = cluster.centerPoint();
        Point firstPoint = cluster.getPastClusters().getLast().centerPoint();
        graphics.drawLine(currentPoint.xCoord, currentPoint.yCoord, firstPoint.xCoord, firstPoint.yCoord);
        Direction gestureDirection = cluster.getGestureDirection();
        if(gestureDirection != Direction.NONE){
            graphics.setColor(Color.GREEN);
            graphics.drawString(gestureDirection.label, 10, 15);
            runekeeper.swipe(gestureDirection);
        }
    }

    private void drawRectangleForCluster(Graphics2D graphics, PointCluster cluster) {
        graphics.drawRect(cluster.leftMostPoint.xCoord, cluster.topMostPoint.yCoord, cluster.width(), cluster.height());
    }
}
