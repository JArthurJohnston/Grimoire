package models.Cameras;

import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameGrabber;

/**
 * Created by Arthur on 1/12/2017.
 */
public class SimpleCamera {
    OpenCVFrameGrabber grabber;
    private boolean isRunning;

    public boolean isRunning(){
        return isRunning;
    }

    public void start(){
        grabber = new OpenCVFrameGrabber("");
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
//            e.printStackTrace();
            System.out.println("Frame Dropped");
            return null;
        }
    }
}
