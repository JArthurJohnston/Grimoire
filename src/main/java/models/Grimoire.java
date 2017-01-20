package models;

import models.Cameras.Camera;
import models.Cameras.FakeCamera;
import models.Cameras.MotionDetectingRecorder;
import models.Cameras.SimpleCamera;
import models.ImageProcessing.ImageFileCapture.ImageWriter;
import models.PixelProcessing.Detectors.MotionDetector;
import org.bytedeco.javacv.*;
import views.ImageSettings;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Created by Arthur on 1/1/2017.
 */
public class Grimoire {

    private static CanvasFrame canvas;
    private static ImageSettings imageSettings;
    private static MotionDetectingRecorder recorder;
    public static ImageWriter imageWriter;
    private static Camera camera;

    public static void main(String[] args){
        camera = new FakeCamera("./res/raw-images/low-speed");
//        camera = new SimpleCamera();
        canvas = new CanvasFrame("Webcam");
        canvas.addWindowListener(setupCloseListener());
        canvas.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        canvas.setSize(new Dimension(800, 600));

        imageSettings = new ImageSettings();

        MotionDetector motionDetector =
                new MotionDetector(imageSettings.luminescenceDetector, imageSettings.getFilters());
        motionDetector.shouldChangeImage = true;
        recorder = new MotionDetectingRecorder(motionDetector, camera);

        imageWriter = new ImageWriter();
        recorder.writer = imageWriter;

        imageSettings.setDetector(motionDetector);
        imageSettings.setVisible(true);

        startOpenCVFrameGrabber();
    }

    private static void startOpenCVFrameGrabber() {
//        recorder.addProcessor(imageWriter);
        camera.start();
        while (camera.isRunning()){
            canvas.showImage(recorder.getFrame());
        }
    }

    private static WindowListener setupCloseListener(){
        return new WindowListener() {
            public void windowOpened(WindowEvent e) {

            }

            public void windowClosing(WindowEvent e) {
                imageWriter.stop();
                camera.stop();
            }

            public void windowClosed(WindowEvent e) {

            }

            public void windowIconified(WindowEvent e) {

            }

            public void windowDeiconified(WindowEvent e) {

            }

            public void windowActivated(WindowEvent e) {

            }

            public void windowDeactivated(WindowEvent e) {

            }
        };
    }
}
