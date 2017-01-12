package models;

import models.Cameras.FilteredCamera;
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
    private static FilteredCamera camera;

    public static void main(String[] args){
        canvas = new CanvasFrame("Webcam");
        canvas.addWindowListener(setupCloseListener());
        canvas.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        canvas.setSize(new Dimension(800, 600));

        camera = new FilteredCamera();

        imageSettings = new ImageSettings();
        imageSettings.setVisible(true);

        startOpenCVFrameGrabber();
    }

    private static void startOpenCVFrameGrabber() {
        camera.addFilters(imageSettings.getFilters());
        camera.start();
        while (camera.isRunning()){
            canvas.showImage(camera.getFrame());
        }
    }

    private static WindowListener setupCloseListener(){
        return new WindowListener() {
            public void windowOpened(WindowEvent e) {

            }

            public void windowClosing(WindowEvent e) {
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
