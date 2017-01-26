package models.PixelProcessing.Filters;

/**
 * Created by Arthur on 1/8/2017.
 */
public abstract class PixelFilter {

    public abstract int execute(int rgbValue);

    public abstract void reset();

    protected static int alpha(int rgbValue){
        return (rgbValue >> 24) & 0xFF;
    }

    protected static int red(int rgbValue){
        return (rgbValue >> 16) & 0xFF;
    }

    protected static int green(int rgbValue){
        return (rgbValue >> 8) & 0xFF;
    }

    protected static int blue(int rgbValue){
        return rgbValue  & 0xFF;
    }

    protected static int truncate(int value){
        if(value > 255){
            return 255;
        } else if(value < 0){
            return 0;
        }
        return value;
    }

    protected static int truncate(float value){
        return truncate((int) value);
    }

    protected static int truncate(double value){
        return truncate((int) value);
    }

}
