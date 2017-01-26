package models.PixelProcessing.Filters;

import models.UserSettings;

public class AverageGrayscaleFilter extends PixelFilter {

    @Override
    public int execute(int rgbValue) {
        if(UserSettings.SHOULD_GRAYSCALE_IMAGE) {
            int value = (int)((red(rgbValue) +
                            green(rgbValue) +
                            blue(rgbValue)) / 3.0);
            value =  (value << 16) | (value << 8) | value;
            return value;
        }
        return rgbValue;
    }

    @Override
    public void reset() {
        UserSettings.SHOULD_GRAYSCALE_IMAGE = UserSettings.Default.GRAYSCALE;
    }
}
