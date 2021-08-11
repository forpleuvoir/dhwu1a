package forpleuvoir.dhwu1a.core.util


import java.lang.reflect.Field

/**
 * @author forpleuvoir
 *
 * #project_name dhwuia
 *
 * #package forpleuvoir.dhwu1a.core.util
 *
 * #class_name ReflectionUtil
 *
 * #create_time 2021/4/20 5:16
 */
object ReflectionUtil {
    @Transient
    private val log = Dhwu1aLog(ReflectionUtil::class.java)
    fun isImplemented(type: Class<*>, target: Class<*>): Boolean {
        return getInterfaces(type).stream().anyMatch { aClass: Class<*> -> aClass == target }
    }

    @JvmStatic
    fun isExtended(type: Class<*>, target: Class<*>): Boolean {
        return getSuperClass(type).stream().anyMatch { aClass: Class<*> -> aClass == target }
    }

    /**
     * 获取所有父类
     *
     * @param clazz [Class]
     * @return [List]
     */
    fun getSuperClass(clazz: Class<*>): List<Class<*>> {
        val clazzs: MutableList<Class<*>> = ArrayList()
        var suCl = clazz.superclass
        clazzs.add(clazz.superclass)
        while (suCl != null) {
            clazzs.add(suCl)
            suCl = suCl.superclass
        }
        return clazzs
    }

    /**
     * 获取该类实现的所有接口
     *
     * @param clazz [Class]
     * @return [List]
     */
    fun getInterfaces(clazz: Class<*>): List<Class<*>> {
        val interfaces = clazz.interfaces
        return listOf(*interfaces)
    }

    /**
     * 设置对象对应字段的值
     *
     * @param fieldName 字段名
     * @param object    对象
     * @param value     值
     */
    fun setFieldValue(fieldName: String, `object`: Any, value: Any?) {
        try {
            val field = getDeclaredField(`object`, fieldName)!!
            field.isAccessible = true
            field[`object`] = value
        } catch (e: NullPointerException) {
            log.error(e.message, e)
        } catch (e: IllegalAccessException) {
            log.error(e.message, e)
        }
    }

    /**
     * 循环向上转型, 获
     *
     * @param object    : 子类对象
     * @param fieldName : 父类中
     * @return 父类中
     */
    fun getDeclaredField(`object`: Any, fieldName: String): Field? {
        var field: Field
        var clazz: Class<*> = `object`.javaClass
        while (clazz != Any::class.java) {
            try {
                field = clazz.getDeclaredField(fieldName)
                return field
            } catch (ignored: Exception) {
            }
            clazz = clazz.superclass
        }
        return null
    }
}