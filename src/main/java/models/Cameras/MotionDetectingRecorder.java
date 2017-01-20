package models.Cameras;

import models.FrameProcessing.*;
import models.ImageProcessing.ImageFileCapture.ImageWriter;
import models.PixelProcessing.Detectors.MotionDetector;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Arthur on 1/17/2017.
 */
public class MotionDetectingRecorder {

    private final MotionDetector motionDetector;
    private final Java2DFrameConverter java2DFrameConverter;
    public ImageWriter writer;
    private final Camera camera;

    public MotionDetectingRecorder(MotionDetector detector, Camera camera){
        this.camera = camera;
        motionDetector = detector;
        java2DFrameConverter = new Java2DFrameConverter();
    }

    public Frame getFrame(){
        Frame frame = camera.getFrame();
        if(frame != null){
            BufferedImage bufferedImage = java2DFrameConverter.getBufferedImage(frame);
            ClusterCollection clusterCollection = motionDetector.processImage(bufferedImage);
            drawBrightspotIds(bufferedImage, clusterCollection);
            if(writer != null){
                writer.process(bufferedImage);
            }
            return java2DFrameConverter.convert(bufferedImage);
        }
        return new Frame();
    }

    private void drawBrightspotIds(BufferedImage bufferedImage, ClusterCollection clusters) {
        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.setColor(Color.RED);
        for (PointCluster cluster : clusters.clusters) {
            if(cluster.isPossibleWandPoint()){
                graphics.setColor(Color.CYAN);
                drawRectangleForCluster(graphics, cluster);
                models.FrameProcessing.Point point = cluster.centerPoint();
                graphics.drawString("WAND?", point.xCoord, point.yCoord);
                graphics.setColor(Color.RED);
            } else {
                drawRectangleForCluster(graphics, cluster);
            }
        }
        graphics.dispose();
    }

    private void drawRectangleForCluster(Graphics2D graphics, PointCluster cluster) {
        graphics.drawRect(cluster.leftMostPoint.xCoord, cluster.topMostPoint.yCoord, cluster.width(), cluster.height());
    }
}
