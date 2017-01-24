package models.Cameras;

import models.FrameProcessing.*;
import models.ImageProcessing.ImageFileCapture.ImageWriter;
import models.PixelProcessing.Detectors.MotionDetector;
import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.bytedeco.javacpp.opencv_imgproc.CV_MEDIAN;
import static org.bytedeco.javacpp.opencv_imgproc.cvSmooth;

/**
 * Created by Arthur on 1/17/2017.
 */
public class MotionDetectingRecorder {

    private final MotionDetector motionDetector;
    private final Java2DFrameConverter java2DFrameConverter;
    public ImageWriter writer;
    private final Camera camera;
    private OpenCVFrameConverter.ToIplImage toIplImage;

    public MotionDetectingRecorder(MotionDetector detector, Camera camera){
        this.camera = camera;
        motionDetector = detector;
        java2DFrameConverter = new Java2DFrameConverter();
        toIplImage = new OpenCVFrameConverter.ToIplImage();
    }

    public Frame getFrame(){
        Frame frame = camera.getFrame();
        opencv_core.IplImage iplImage = toIplImage.convertToIplImage(frame);
        cvSmooth(iplImage, iplImage, CV_MEDIAN, 3, 0, 0, 0);

        if(frame != null){
            BufferedImage bufferedImage = java2DFrameConverter.getBufferedImage(toIplImage.convert(iplImage));
            FrameData clusterCollection = motionDetector.processImage(bufferedImage);
            drawBrightspotIds(bufferedImage, clusterCollection);
            if(writer != null){
                writer.process(bufferedImage);
            }
            return java2DFrameConverter.convert(bufferedImage);
        }
        return new Frame();
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

    }

    private void drawRectangleForCluster(Graphics2D graphics, PointCluster cluster) {
        graphics.drawRect(cluster.leftMostPoint.xCoord, cluster.topMostPoint.yCoord, cluster.width(), cluster.height());
    }
}
