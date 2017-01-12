package models;

import models.ImageProcessing.Adjustment;
import models.PixelProcessing.PixelAdjustment;
import org.bytedeco.javacv.*;
import org.bytedeco.javacv.Frame;
import views.ImageSettings;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;

/**
 * Created by Arthur on 1/1/2017.
 */
public class Grimoire {

    public static float luminescenceFilter = 0.0f;
    private static CanvasFrame canvas;
    private static FrameGrabber grabber;
    private static ImageSettings imageSettings;
    private static PixelAdjustment[] adjustments;

    public static void main(String[] args){
        canvas = new CanvasFrame("Webcam");
        setupCloseListener();
        LuminosityFrame luminosityFrame = new LuminosityFrame();
        luminosityFrame.setSize(new Dimension(500, 100));
        luminosityFrame.setVisible(true);
        imageSettings = new ImageSettings();
        adjustments = imageSettings.getAdjustments();
        imageSettings.setVisible(true);

        canvas.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        canvas.setSize(new Dimension(800, 600));
        startOpenCVFrameGrabber(canvas);
    }

    private static void startOpenCVFrameGrabber(CanvasFrame canvas) {
        grabber = new OpenCVFrameGrabber("");

        try {
            grabber.start();
            
            canvas.setCanvasSize(grabber.getImageWidth(), grabber.getImageHeight());
            while(true){
                final Frame frame = grabber.grab();
//                canvas.showImage(processFrame(frame));
                canvas.showImage(frame);
            }
        } catch (FrameGrabber.Exception e) {
            e.printStackTrace();
        }

    }

    public static void applyImageAdjustments(final BufferedImage image){

//        for (Adjustment eachAdjustment : adjustments) {
//            eachAdjustment.execute(image);
//        }
    }

    public static Frame processFrame(final Frame frame){
        final Java2DFrameConverter converter = new Java2DFrameConverter();
        final BufferedImage image = converter.getBufferedImage(frame);
//        if(luminescenceFilter > 0.0f){
            applyImageAdjustments(image);
            filterOutDarkColors(image);
            return converter.convert(image);
//        } else {
//            return frame;
//        }
    }

    private static int processPixel(int rgbValue){
        int colorValue = rgbValue;
        for (PixelAdjustment eachAdjustment: adjustments) {
            colorValue = eachAdjustment.execute(colorValue);
        }
        return colorValue;
    }

    private static BufferedImage filterOutDarkColors(final BufferedImage bufferedImage) {
        int height = bufferedImage.getHeight();
        int width = bufferedImage.getWidth();
        final int gridDensity = 1;
        for(int x = 0; x < width; x+= gridDensity){
            for(int y = 0; y < height; y+= gridDensity) {
                int processedValue = processPixel(bufferedImage.getRGB(x, y));
                bufferedImage.setRGB(x, y, processedValue);

//                float luminance = findPixelLuminescence(bufferedImage, x, y);
//
//                if (luminance >= luminescenceFilter) {
//                    drawTargetAt(x, y, width, height, bufferedImage,  Color.red);
////                    bufferedImage.setRGB(x, y, Color.red.getRGB());
//                    // bright color
//                } else {
//                    // dark color
//                    bufferedImage.setRGB(x, y, Color.black.getRGB());
//                }
            }
        }
        return bufferedImage;
    }

    private static void drawTargetAt(int x, int y, int width, int height, BufferedImage image, Color color){
        image.setRGB(x, y, color.getRGB());
        if(!((x - 1) < width))
            image.setRGB(x-1, y, color.getRGB());
        if(!((x + 1) > width))
            image.setRGB(x + 1, y, color.getRGB());
        if(!((y + 1) > height))
            image.setRGB(x, y + 1, color.getRGB());
        if(!((y - 1) < height))
            image.setRGB(x, y - 1, color.getRGB());
    }

    private static float findPixelLuminescence(final BufferedImage bufferedImage, int x, int y) {
        final int color = bufferedImage.getRGB(x, y);

        final int red   = (color >>> 16) & 0xFF;
        final int green = (color >>>  8) & 0xFF;
        final int blue  = (color >>>  0) & 0xFF;

        return (red * 0.2126f + green * 0.7152f + blue * 0.0722f) / 255;
    }

    private static void setupCloseListener(){
        canvas.addWindowListener(new WindowListener() {
            public void windowOpened(WindowEvent e) {

            }

            public void windowClosing(WindowEvent e) {
                try {
                    grabber.stop();
                } catch (FrameGrabber.Exception e1) {
                    e1.printStackTrace();
                }
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
        });
    }
}
