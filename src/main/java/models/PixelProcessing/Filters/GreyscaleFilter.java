package models.PixelProcessing.Filters;

/**
 * Created by Arthur on 1/12/2017.
 */
public class GreyscaleFilter extends PixelFilter{

    private boolean applyGreyscale = false;

    public int execute(int rgbValue) {
        if(this.applyGreyscale){
            return truncate(
                    0.299 * red(rgbValue) +
                    0.587 * green(rgbValue) +
                    0.114 * blue(rgbValue));
        }
        return rgbValue;
    }

    public void reset() {
        applyGreyscale = false;
    }

    public void update(boolean applyGreyscale){
        this.applyGreyscale = applyGreyscale;
    }
}
