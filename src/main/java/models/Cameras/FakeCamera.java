package models.Cameras;

import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by arthur on 20/01/17.
 */
public class FakeCamera implements Camera {
    private final BufferedImage[] frames;
    private int frameIndex;
    private final Java2DFrameConverter java2DFrameConverter;
    private boolean isRunning;
    private final String directoryName;
    private long timestamp;
    private static final int SPEED = 333;

    public FakeCamera(String directoryName){
        this.directoryName = directoryName;
        java2DFrameConverter = new Java2DFrameConverter();
        List<BufferedImage> loadedImages = loadImageFiles();
        frames = loadedImages.toArray(new BufferedImage[loadedImages.size()]);
        frameIndex = 0;
    }

    private int getIndex(){
        long currentTimeMillis = System.currentTimeMillis();
        if(currentTimeMillis - timestamp > SPEED){
            timestamp = currentTimeMillis;
            frameIndex++;
            if(frameIndex == frames.length){
                frameIndex = 0;
            }
        }
        return frameIndex;
    }

    private List<BufferedImage> loadImageFiles() {
        LinkedList<BufferedImage> images = new LinkedList<BufferedImage>();
        try {
            File imageDirectory = new File(directoryName);
            for (File file : imageDirectory.listFiles()) {
                images.add(ImageIO.read(file));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return images;
    }


    public void start() {
        timestamp = System.currentTimeMillis();
        isRunning = true;
    }

    public void stop() {
        isRunning = false;
    }

    public Frame getFrame() {
        BufferedImage image = frames[getIndex()];
        return java2DFrameConverter.getFrame(image);
    }

    public boolean isRunning() {
        return isRunning;
    }
}
