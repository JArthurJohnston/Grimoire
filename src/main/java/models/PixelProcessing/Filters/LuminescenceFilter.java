package models.PixelProcessing.Filters;


import models.UserSettings;

/**
 * Created by Arthur on 1/12/2017.
 */
public class LuminescenceFilter extends PixelFilter {
    private static final int BLACK = 0;

    public int execute(int rgbValue) {
        if(findLuminescence(rgbValue) < UserSettings.LUMINESCENCE_THREASHOLD){
            return BLACK;
        }
        return rgbValue;
    }

    public void reset() {
        UserSettings.LUMINESCENCE_THREASHOLD = UserSettings.DEFAULT_LUMINESCENCE_THREASHOLD;
    }

    private static float findLuminescence( int rgbValiue) {
        return (red(rgbValiue) * 0.2126f + green(rgbValiue) * 0.7152f + blue(rgbValiue) * 0.0722f) / 255;
    }


    public void update(float threashold){
        UserSettings.LUMINESCENCE_THREASHOLD = threashold;
    }
}
