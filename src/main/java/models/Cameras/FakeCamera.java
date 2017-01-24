package models.Cameras;

import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
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
    private static final int SPEED = 200;

    public FakeCamera(String directoryName){
        this.directoryName = directoryName;
        java2DFrameConverter = new Java2DFrameConverter();
        List<BufferedImage> loadedImages = loadImageFiles();
        frames = loadedImages.toArray(new BufferedImage[loadedImages.size()]);
        frameIndex = 0;
    }

    private int getIndex(){
            frameIndex++;
            if(frameIndex >= frames.length){
                frameIndex = 0;
            }
        return frameIndex;
    }

    private List<BufferedImage> loadImageFiles() {
        LinkedList<BufferedImage> images = new LinkedList<BufferedImage>();
        try {
            File imageDirectory = new File(directoryName);
            File[] files = imageDirectory.listFiles();
            Arrays.sort(files);
            for (File file : files) {
                images.add(ImageIO.read(file));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return images;
    }


    public void start() {
        isRunning = true;
    }

    public void stop() {
        isRunning = false;
    }

    public Frame getFrame() {
        try {
            Thread.sleep(SPEED);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int index = getIndex();
        BufferedImage image = frames[index];
        return java2DFrameConverter.getFrame(image);
    }

    public boolean isRunning() {
        return isRunning;
    }
}
