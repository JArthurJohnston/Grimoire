package models.PixelProcessing.Filters;

import models.UserSettings;

/**
 * Created by Arthur on 1/12/2017.
 */
public class WeightedGrayscaleFilter extends PixelFilter{

    public int execute(int rgbValue) {
        if(UserSettings.SHOULD_GRAYSCALE_IMAGE){
            int value = (int)(0.299 * red(rgbValue) +
                    0.587 * green(rgbValue) +
                    0.114 * blue(rgbValue));
            value = (alpha(rgbValue) << 24) | (value << 16) | (value << 8) | value;
            return truncate(value);
        }
        return rgbValue;
    }

    @Override
    public void reset() {
        UserSettings.SHOULD_GRAYSCALE_IMAGE = UserSettings.Default.GRAYSCALE;
    }
}
