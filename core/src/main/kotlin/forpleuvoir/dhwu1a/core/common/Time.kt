package forpleuvoir.dhwu1a.core.common

/**
 * 时间工具类
 * @author forpleuvoir

 * #project_name dhwu1a

 * #package forpleuvoir.dhwu1a.core.common

 * #class_name Time

 * #create_time 2021-08-13 17:04
 */

fun Long.sec(): Long {
    return this * 1000
}

fun Long.minute(): Long {
    return this.sec() * 60
}

fun Long.hour(): Long {
    return this.minute() * 60
}

fun Long.day(): Long {
    return this.hour() * 24
}