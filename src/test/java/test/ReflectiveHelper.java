package test;

import java.lang.reflect.Field;

/**
 * Created by Arthur on 1/11/2017.
 */
public class ReflectiveHelper {

    public static Object getPrivateField(Object object, String fieldName)throws Exception{
        Field declaredField = object.getClass().getDeclaredField(fieldName);
        declaredField.setAccessible(true);
        Object privateValue = declaredField.get(object);
        return privateValue;
    }

    public static <T>T getPrivateField(Object object, String fieldName, Class<T> returnType) throws Exception{
        return (T)getPrivateField(object, fieldName);
    }
}
