package forpleuvoir.dhwu1a.core.event.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 事件处理
 *
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.event.base.annotation
 * <p>#class_name EventHandler
 * <p>#create_time 2021-08-07 17:40
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventHandler {
}
