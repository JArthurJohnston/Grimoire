package models.Cameras;

import models.ImageProcessing.ImageFileCapture.ImageWriter;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameGrabber;

/**
 * Created by Arthur on 1/12/2017.
 */
public class SimpleCamera {
    OpenCVFrameGrabber grabber;
    private boolean isRunning;
    //these camera values may change after the computer is rebooted
    private static final int WEBCAM = 0;
    private static final int USB = 1;

    public boolean isRunning(){
        return isRunning;
    }

    public void start(){
        //be sure to rebuild the project after changing the camera index
        grabber = new OpenCVFrameGrabber(USB);
        try {
            grabber.start();
            isRunning = true;
        } catch (FrameGrabber.Exception e) {
            e.printStackTrace();
        }
    }

    public void stop(){
        if(grabber != null){
            try {
                grabber.stop();
                isRunning = false;
            } catch (FrameGrabber.Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Frame getFrame(){
        try {
            return grabber.grab();
        } catch (FrameGrabber.Exception e) {
            System.out.println("Frame Dropped");
            return null;
        }
    }
}
