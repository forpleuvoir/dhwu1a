package forpleuvoir.dhwu1a.core.util

import forpleuvoir.dhwu1a.core.Dhwu1a.Companion.instance
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.util
 *
 * #class_name Dhwu1aLog
 *
 * #create_time 2021/6/29 23:26
 */
class Dhwu1aLog(clazz: Class<*>?) {
    @Transient
    private val log: Logger
    fun info(format: String?, vararg args: Any?) {
        if (LOG_CONFIG.info) log.info(format, *args)
    }

    fun info(message: String?) {
        if (LOG_CONFIG.info) log.info(message)
    }

    fun debug(message: String?) {
        if (LOG_CONFIG.debug) log.debug(message)
    }

    fun debug(format: String?, vararg args: Any?) {
        if (LOG_CONFIG.debug) log.debug(format, *args)
    }

    fun error(message: String?) {
        if (LOG_CONFIG.error) log.error(message)
    }

    fun error(format: String?, vararg args: Any?) {
        if (LOG_CONFIG.error) log.error(format, *args)
    }

    fun warn(message: String?) {
        if (LOG_CONFIG.warn) log.warn(message)
    }

    fun warn(format: String?, vararg args: Any?) {
        if (LOG_CONFIG.warn) log.warn(format, *args)
    }

    companion object {
        val LOG_CONFIG = instance!!.logConfig
    }

    init {
        log = LoggerFactory.getLogger(clazz)
    }
}