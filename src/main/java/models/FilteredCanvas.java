package models;

import org.bytedeco.javacv.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Arthur on 1/4/2017.
 */
public class FilteredCanvas extends CanvasFrame {

    private final Java2DFrameConverter converter;
    public float luminescenceFilter = 0.0f;
    private OpenCVFrameGrabber grabber;
    private BufferedImage image;

    public FilteredCanvas(final String title) {
        super(title);
        converter = new Java2DFrameConverter();
    }

    public void startVideoCapture(){
        grabber = new OpenCVFrameGrabber("");
        try {
            grabber.start();
            this.setCanvasSize(grabber.getImageWidth(), grabber.getImageHeight());
            while(true){
                final Frame frame = grabber.grab();
                this.showImage(processFrame(frame));
            }
        } catch (FrameGrabber.Exception e) {
            e.printStackTrace();
        }
    }

    public void loadImage(){
        JFileChooser chooser = new JFileChooser();
        int choice = chooser.showOpenDialog(canvas);
        if(choice == JFileChooser.APPROVE_OPTION){
            File selectedFile = chooser.getSelectedFile();
            try {
                this.image = ImageIO.read(selectedFile);
                renderImage(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void refreshImage(){
        renderImage(this.image);
    }

    private void renderImage(BufferedImage image) {
        Frame convertedFrame = converter.convert(applyFilters(image));
        this.showImage(convertedFrame);
    }

    private Frame processFrame(final Frame frame){
        BufferedImage bufferedImage = converter.getBufferedImage(frame);
        return converter.convert(applyFilters(bufferedImage));
    }

    private BufferedImage applyFilters(BufferedImage image) {
        int height = image.getHeight();
        int width = image.getWidth();
        final int black = 0;
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++) {
                float luminance = findPixelLuminescence(image, x, y);

                if (luminance >= luminescenceFilter) {
                    // bright color
                } else {
                    // dark color
                    image.setRGB(x, y, black);
                }
            }
        }
        return image;
    }

    private float findPixelLuminescence(final BufferedImage bufferedImage, int x, int y) {
        final int color = bufferedImage.getRGB(x, y);

        final int red   = (color >>> 16) & 0xFF;
        final int green = (color >>>  8) & 0xFF;
        final int blue  = (color >>>  0) & 0xFF;

        return (red * 0.2126f + green * 0.7152f + blue * 0.0722f) / 255;
    }
}
