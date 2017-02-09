package models.Cameras.FrameBuffering;

import models.FrameProcessing.FrameData;

import java.awt.image.BufferedImage;

/**
 * Created by arthur on 01/02/17.
 */
public class FrameBuffer {
    private int width;
    private int height;

    protected FrameBuffer(BufferedImage image){

    }

    public static FrameBuffer initialize(BufferedImage image){
        return new FrameBuffer(image);
    }


    private class DetectionPoint{

    }
}
