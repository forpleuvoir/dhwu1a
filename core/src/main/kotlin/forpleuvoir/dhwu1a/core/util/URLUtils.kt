package forpleuvoir.dhwu1a.core.util

/**
 * url工具类
 *
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.util
 *
 * #class_name URLUtils
 *
 * #create_time 2021/6/28 21:32
 */
object URLUtils {
    @JvmStatic
    fun getWSUrl(ip: String?, port: Int): String {
        return String.format("ws://%s:%d", ip, port)
    }
}