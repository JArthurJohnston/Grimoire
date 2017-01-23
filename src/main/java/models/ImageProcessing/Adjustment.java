package models.ImageProcessing;

import java.awt.image.BufferedImage;

public abstract class Adjustment {

    public abstract void execute(final BufferedImage image);
    
    public abstract void reset();

    protected static int redValue(final BufferedImage image, final int x, final int y){
        final int color = image.getRGB(x, y);
        return (color >>> 16) & 0xFF;
    }

    protected static int greenValue(final BufferedImage image, final int x, final int y){
        final int color = image.getRGB(x, y);
        return (color >>>  8) & 0xFF;
    }

    protected static int blueValue(final BufferedImage image, final int x, final int y){
        final int color = image.getRGB(x, y);
        return (color >>>  0) & 0xFF;
    }

    protected static int truncateValue(final float value){
        if(value < 0){
            return 0;
        } else if(value > 255){
            return 255;
        }
        return (int)value;
    }

    protected static int truncateValue(final double value){
        if(value < 0){
            return 0;
        } else if(value > 255){
            return 255;
        }
        return (int)value;
    }


}
