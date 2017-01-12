package test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Arthur on 1/11/2017.
 */
public class GrimoireCollectionAsserts {

    public static <T>void assertEmpty(String message, T[] objects){
        assertEquals(message, 0, objects.length);
    }

    public static <T>void assertEmpty(T[] objects){
        assertEmpty(null, objects);
    }

    public static <T>void assertEmpty(String message, List<T> objects){
        assertEquals(message, 0, objects.size());
    }

    public static <T>void assertEmpty(List<T> objects){
        assertEmpty(null, objects);
    }

    public static <T>void denyEmpty(List<T> objects){
        denyEmpty(null, objects);
    }

    public static <T>void denyEmpty(String message, List<T> objects){
        assertTrue(message, objects.size()> 0);
    }

    public static <T>void denyEmpty(T[] objects){
        denyEmpty(null, objects);
    }

    public static <T>void denyEmpty(String message, T[] objects){
        assertTrue(message, objects.length > 0);
    }
}
