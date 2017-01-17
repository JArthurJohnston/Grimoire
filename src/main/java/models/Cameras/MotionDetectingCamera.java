package models.Cameras;

import models.FrameProcessing.ClusterCollection;
import models.FrameProcessing.PointCluster;
import models.PixelProcessing.Detectors.LuminescenceDetector;
import models.PixelProcessing.Detectors.MotionDetector;
import models.PixelProcessing.Detectors.WhitePixelDetector;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Arthur on 1/17/2017.
 */
public class MotionDetectingCamera extends SimpleCamera {

    private final MotionDetector motionDetector;
    private final Java2DFrameConverter java2DFrameConverter;

    public MotionDetectingCamera(){
        motionDetector = new MotionDetector(new LuminescenceDetector());
        java2DFrameConverter = new Java2DFrameConverter();
    }

    public Frame getFrame(){
        Frame frame = super.getFrame();
        if(frame != null){
            BufferedImage bufferedImage = java2DFrameConverter.getBufferedImage(frame);
            drawRectanglesAroundBrightSpots(bufferedImage);
            return java2DFrameConverter.convert(bufferedImage);
        }
        return new Frame();
    }

    private void drawRectanglesAroundBrightSpots(BufferedImage bufferedImage) {
        ClusterCollection clusterCollection = motionDetector.processImage(bufferedImage);
        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.setColor(Color.RED);
        for (PointCluster cluster : clusterCollection.clusters) {
            graphics.drawRect(cluster.leftMostPoint.xCoord, cluster.topMostPoint.yCoord, cluster.width(), cluster.height());
        }
        graphics.dispose();
    }
}
