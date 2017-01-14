package models.FrameProcessing;

import models.RuneKeeper;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Arthur on 1/11/2017.
 */
public class FrameProcessor {

    private static final int FUDGE_FACTOR  = 5;


    private LinkedList<ProcessedFrame> processedFrames;
    private RuneKeeper runeKeeper;

    public FrameProcessor(RuneKeeper keeper){
        this.runeKeeper = keeper;
        this.processedFrames = new LinkedList<ProcessedFrame>();
    }

    public LinkedList<ProcessedFrame> getProcessedFrames() {
        return processedFrames;
    }

    public List<ProcessedFrame> getFrames() {
        return processedFrames;
    }

    public void processFrame(BufferedImage image) {
        ProcessedFrame frame = new ProcessedFrame();
        this.processedFrames.add(frame);
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                if(pixelIsSpecial(image.getRGB(x, y))){
                    frame.addPoint(x, y);
                    x += FUDGE_FACTOR;
                }
            }
        }
    }

    public boolean motionDetected(){
        return false;
    }

    public RuneKeeper getRuneKeeper(){
        return this.runeKeeper;
    }
    private boolean pixelIsSpecial(int rgbValue){
        return rgbValue == Color.white.getRGB();
    }

}
