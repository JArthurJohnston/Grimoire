package models.PixelProcessing.Filters;


/**
 * Created by Arthur on 1/12/2017.
 */
public class LuminescenceFilter extends PixelFilter {

    public static final int DEFAULT_LUMINESCENCE_THREASHOLD = 0;
    private float luminescenceThreashold = DEFAULT_LUMINESCENCE_THREASHOLD;
    private static final int BLACK = 0;

    public int execute(int rgbValue) {
        if(findLuminescence(rgbValue) < this.luminescenceThreashold){
            return BLACK;
        }
        return rgbValue;
    }

    public void reset() {
        this.luminescenceThreashold = DEFAULT_LUMINESCENCE_THREASHOLD;
    }

    private static float findLuminescence( int rgbValiue) {
        return (red(rgbValiue) * 0.2126f + green(rgbValiue) * 0.7152f + blue(rgbValiue) * 0.0722f) / 255;
    }

    public float getLuminescenceThreashold(){
        return this.luminescenceThreashold;
    }

    public void update(float threashold){
        this.luminescenceThreashold = threashold;
    }
}
