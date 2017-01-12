package models.FrameProcessing;

/**
 * Created by Arthur on 1/11/2017.
 */
public class Point {

    private int xCoord;
    private int yCoord;

    public Point(int xCoord, int yCoord){
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    public int getX(){
        return this.xCoord;
    }

    public int getY(){
        return this.yCoord * -1;
    }
}
