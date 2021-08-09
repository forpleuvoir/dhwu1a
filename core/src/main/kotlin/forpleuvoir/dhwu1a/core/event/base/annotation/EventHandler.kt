package forpleuvoir.dhwu1a.core.event.base.annotation

/**
 * 事件处理
 *
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.event.base.annotation
 *
 * #class_name EventHandler
 *
 * #create_time 2021-08-07 17:40
 */
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class EventHandler