package models.Cameras.FrameBuffering;

import models.Cameras.Camera;
import models.FrameProcessing.Buffer;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import java.awt.image.BufferedImage;

/**
 * Created by arthur on 26/01/17.
 */
public class BufferingRecorder {

    private static final int FPS = 30;
    private static final int BUFFER_LENGTH_IN_SECONDS = 2;
    private final Java2DFrameConverter converter;
    private final Camera camera;
    private final Buffer<BufferedFrame> frameBuffer;
    private int width = 0;
    private int height = 0;

    public BufferingRecorder(Camera camera){
        this.converter = new Java2DFrameConverter();
        this.camera = camera;
        frameBuffer = new Buffer<>(FPS * BUFFER_LENGTH_IN_SECONDS);
    }

    public Frame getFrame(){
        Frame frame = camera.getFrame();
        BufferedImage image = converter.convert(frame);
        return frame;
    }
}
