package models.Cameras;

import org.bytedeco.javacv.Frame;

/**
 * Created by arthur on 20/01/17.
 */
public interface Camera {
    void start();

    void stop();

    Frame getFrame();

    boolean isRunning();
}
