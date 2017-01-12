package models;

import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Arthur on 1/4/2017.
 */
public class ImageProcessor {

    private static CanvasFrame canvas;

    public static void main(String[] args){
        canvas = new CanvasFrame("Show Single Frame");
        canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        canvas.setSize(new Dimension(800, 600));
        controlFrame().setVisible(true);
//        JMenu menu = new JMenu("File");
//        final JMenuItem loadImageItem = new JMenuItem("Load Image");
//        loadImageItem.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                loadImage();
//            }
//        });
//        menu.add(loadImageItem);
//        JMenuBar jMenuBar = new JMenuBar();
//        jMenuBar.add(menu);
//        canvas.setJMenuBar(jMenuBar);
    }

    public static void loadImage(){
        JFileChooser chooser = new JFileChooser();
        int choice = chooser.showOpenDialog(canvas);
        if(choice == JFileChooser.APPROVE_OPTION){
            File selectedFile = chooser.getSelectedFile();
            try {
                BufferedImage image = ImageIO.read(selectedFile);
                final Java2DFrameConverter converter = new Java2DFrameConverter();
                Frame convertedFrame = converter.convert(image);
                canvas.showImage(convertedFrame);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static JFrame controlFrame(){
        JFrame jFrame = new JFrame("Load Image Controls");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(new Dimension(500, 100));

        JButton loadButton = new JButton("Load Image");
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadImage();
            }
        });
        jFrame.add(loadButton);

        return jFrame;
    }
}
