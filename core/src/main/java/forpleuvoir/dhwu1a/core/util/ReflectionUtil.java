package forpleuvoir.dhwu1a.core.util;

import java.lang.reflect.Field;
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
    private transient static final Dhwu1aLog log = new Dhwu1aLog(ReflectionUtil.class);

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

    /**
     * 设置对象对应字段的值
     *
     * @param fieldName 字段名
     * @param object    对象
     * @param value     值
     */
    public static void setFieldValue(String fieldName, Object object, Object value) {
        try {
            Field field = getDeclaredField(object, fieldName);
            assert field != null;
            field.setAccessible(true);
            field.set(object, value);
        } catch (NullPointerException | IllegalAccessException e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 循环向上转型, 获
     *
     * @param object    : 子类对象
     * @param fieldName : 父类中
     * @return 父类中
     */
    public static Field getDeclaredField(Object object, String fieldName) {
        Field field;
        Class<?> clazz = object.getClass();
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName);
                return field;
            } catch (Exception ignored) {
            }
        }
        return null;
    }
}
