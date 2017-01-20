package models.ImageProcessing.ImageFileCapture;

import models.ImageProcessing.ImageProcessor;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;


/**
 * Created by Arthur on 1/12/2017.
 */
public class ImageWriter implements Runnable, ImageProcessor{

    private volatile boolean isRunning;
    //change this value if run in windows
    private final String parentDirectoryName = "./res";
    private int numberOfFilesWritten = 0;
    BlockingQueue<BufferedImage> imageQueue = new LinkedBlockingDeque<BufferedImage>();

    public void process(BufferedImage image){
        if(isRunning){
            try {
                imageQueue.put(image);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void writeQueuedImages(){
        while(!imageQueue.isEmpty()){
            try {
                BufferedImage image = imageQueue.take();
                if(image != null){
                    saveImageToFile(image);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveImageToFile(BufferedImage image){
        File outputFile = new File(fileName());
        try {
            outputFile.createNewFile();
            ImageIO.write(image, "png", outputFile);
            numberOfFilesWritten++;
        } catch (IOException e) {
            System.out.println("Failed to write image" + fileName());
            e.printStackTrace();
        }
    }

    private String fileName(){
        return String.format("%s/%d-%d.png",
                parentDirectoryName,
                numberOfFilesWritten,
                System.currentTimeMillis());
    }

    public void run() {
        isRunning = true;
        while(isRunning){
            writeQueuedImages();
        }
    }

    public void stop(){
        isRunning = false;
    }

    public void start(){
        isRunning = true;
        new Thread(this).start();
    }
}
