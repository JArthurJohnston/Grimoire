package models.Cameras;

import models.FrameProcessing.ClusterCollection;
import models.FrameProcessing.PointCluster;
import models.ImageProcessing.ImageProcessor;
import models.PixelProcessing.Detectors.LuminescenceDetector;
import models.PixelProcessing.Detectors.MotionDetector;
import models.PixelProcessing.Filters.PixelFilter;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Arthur on 1/12/2017.
 */
public class FilteredCamera extends SimpleCamera{

    private final List<PixelFilter> filters;
    private final Java2DFrameConverter converter;
    private final LinkedList<ImageProcessor> imageProcessors;
    public final static int DEFAULT_SCANLINE_SETTING = 1;
    private int scanLineSetting = DEFAULT_SCANLINE_SETTING;
    private final MotionDetector motionDetector;


    public FilteredCamera(){
        converter = new Java2DFrameConverter();
        filters = new LinkedList<PixelFilter>();
        imageProcessors = new LinkedList<ImageProcessor>();
        motionDetector = new MotionDetector(new LuminescenceDetector());
    }

    public void addFilters(PixelFilter[] filters){
        this.filters.addAll(Arrays.asList(filters));
    }

    public void addProcessor(ImageProcessor processor){
        this.imageProcessors.add(processor);
    }

    public Frame getFrame(){
        return processFrame(super.getFrame());
    }

    private Frame processFrame(Frame frame){
        if(this.filters.isEmpty()){
            return frame;
        }
        BufferedImage image = converter.convert(frame);
        processImage(image);
        if(image != null){
            int height = image.getHeight();
            int width = image.getWidth();
            for(int y = 0; y < height; y+= this.scanLineSetting) {
                for(int x = 0; x < width; x++){
                    int filteredRgb = applyFilters(image.getRGB(x, y));
                    image.setRGB(x, y, filteredRgb);
                }
            }
            return converter.convert(image);
        }
        System.out.println("Null Image from frame");
        return frame;
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

    private void processImage(BufferedImage image){
        for (ImageProcessor processor : this.imageProcessors) {
            processor.process(image);
        }
    }

    private int applyFilters(int rgbValue){
        int newValue = rgbValue;
        for (PixelFilter filter : this.filters) {
            newValue = filter.execute(newValue);
        }
        return newValue;
    }

    public void updateScanlines(int scanlineValue){
        this.scanLineSetting = scanlineValue;
    }

}
