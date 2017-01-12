package models.Cameras;

import models.PixelProcessing.Detectors.MotionDetector;
import models.PixelProcessing.Filters.PixelFilter;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

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
    private static MotionDetector detector = new MotionDetector();

    public FilteredCamera(){
        converter = new Java2DFrameConverter();
        filters = new LinkedList<PixelFilter>();
    }

    public void addFilters(PixelFilter[] filters){
        this.filters.addAll(Arrays.asList(filters));
    }

    public Frame getFrame(){
        return processFrame(super.getFrame());
    }

    private Frame processFrame(Frame frame){
        if(this.filters.isEmpty()){
            return frame;
        }
        BufferedImage image = converter.convert(frame);

        if(image != null){
            int height = image.getHeight();
            int width = image.getWidth();
            for(int y = 0; y < height; y++) {
                for(int x = 0; x < width; x++){
                    image.setRGB(x, y, applyFilters(image.getRGB(x, y)));
                }
            }
//            detector.processImage(image);
            return converter.convert(image);
        }
        System.out.println("Null Image from frame");
        return frame;
    }

    private int applyFilters(int rgbValue){
        int newValue = rgbValue;
        for (PixelFilter filter : this.filters) {
            newValue = filter.execute(newValue);
        }
        return newValue;
    }

}
