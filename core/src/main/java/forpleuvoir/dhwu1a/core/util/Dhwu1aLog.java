package forpleuvoir.dhwu1a.core.util;

import forpleuvoir.dhwu1a.config.LogConfig;
import forpleuvoir.dhwu1a.core.Dhwu1a;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.util
 * <p>#class_name Dhwu1aLog
 * <p>#create_time 2021/6/29 23:26
 */
public class Dhwu1aLog {
    public static final LogConfig LOG_CONFIG = Dhwu1a.getInstance().logConfig;

    private transient final Logger log;

    public Dhwu1aLog(Class<?> clazz) {
        this.log = LoggerFactory.getLogger(clazz);
    }

    public void info(String format, Object... args) {
        if (LOG_CONFIG.getInfo())
            log.info(format, args);
    }

    public void info(String message) {
        if (LOG_CONFIG.getInfo())
            log.info(message);
    }

    public void debug(String message) {
        if (LOG_CONFIG.getDebug())
            log.debug(message);
    }

    public void debug(String format, Object... args) {
        if (LOG_CONFIG.getDebug())
            log.debug(format, args);
    }

    public void error(String message) {
        if (LOG_CONFIG.getError())
            log.error(message);
    }

    public void error(String format, Object... args) {
        if (LOG_CONFIG.getError())
            log.error(format, args);
    }

    public void warn(String message) {
        if (LOG_CONFIG.getWarn())
            log.warn(message);
    }

    public void warn(String format, Object... args) {
        if (LOG_CONFIG.getWarn())
            log.warn(format, args);
    }
}
