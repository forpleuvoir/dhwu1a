package forpleuvoir.dhwu1a.core.util;


/**
 * url工具类
 *
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.util
 * <p>#class_name URLUtils
 * <p>#create_time 2021/6/28 21:32
 */
public class URLUtils {
    public static String getWSUrl(String ip, int port) {
        return String.format("ws://%s:%d", ip, port);
    }
}
