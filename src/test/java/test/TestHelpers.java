package test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by arthur on 21/01/17.
 */
public class TestHelpers {

    public static <T>T assertTypeAndGet(Object object, Class<T> type){
        assertEquals(object.getClass(), type);
        return (T)object;
    }
}
