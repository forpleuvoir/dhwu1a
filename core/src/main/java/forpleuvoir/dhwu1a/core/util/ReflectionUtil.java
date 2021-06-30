package forpleuvoir.dhwu1a.core.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author forpleuvoir
 * <p>#project_name dhwuia
 * <p>#package forpleuvoir.dhwu1a.core.util
 * <p>#class_name ReflectionUtil
 * <p>#create_time 2021/4/20 5:16
 */
public class ReflectionUtil {

    public static boolean isImplemented(Class<?> type, Class<?> target) {
        return getInterfaces(type).stream().anyMatch(aClass -> aClass.equals(target));
    }

    public static boolean isExtended(Class<?> type, Class<?> target) {
        return getSuperClass(type).stream().anyMatch(aClass -> aClass.equals(target));
    }

    /**
     * 获取所有父类
     *
     * @param clazz {@link Class}
     * @return {@link List}
     */
    public static List<Class<?>> getSuperClass(Class<?> clazz) {
        List<Class<?>> clazzs = new ArrayList<>();
        Class<?> suCl = clazz.getSuperclass();
        clazzs.add(clazz.getSuperclass());
        while (suCl != null) {
            clazzs.add(suCl);
            suCl = suCl.getSuperclass();
        }
        return clazzs;
    }

    /**
     * 获取该类实现的所有接口
     *
     * @param clazz {@link Class}
     * @return {@link List}
     */
    public static List<Class<?>> getInterfaces(Class<?> clazz) {
        Class<?>[] interfaces = clazz.getInterfaces();
        return Arrays.asList(interfaces);
    }

    public static void setFieldValue(Object object,Object value){

    }
}
