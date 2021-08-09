package forpleuvoir.dhwu1a.core.event.base.annotation

import forpleuvoir.dhwu1a.core.event.base.Dhwu1aEvent
import forpleuvoir.dhwu1a.core.event.base.EventBus
import forpleuvoir.dhwu1a.core.event.base.Listener
import forpleuvoir.dhwu1a.core.util.Dhwu1aLog
import forpleuvoir.dhwu1a.core.util.ReflectionUtil
import java.lang.reflect.Method
import java.util.*

/**
 * 时间处理程序解析器
 *
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.event.base
 *
 * #class_name EventHandlerParser
 *
 * #create_time 2021/8/7 20:11
 */
object EventHandlerParser {

    @Transient
    private val log = Dhwu1aLog(EventHandlerParser::class.java)

    @JvmStatic
    @Suppress("UNCHECKED_CAST")
    fun parse(listener: Listener, eventBus: EventBus) {
        val methods = getMethods(listener)
        for (method in methods) {
            val parameter = method.parameters[0]
            val type = parameter.type as Class<out Dhwu1aEvent?>
            eventBus.subscribe(type) { event: Dhwu1aEvent? ->
                try {
                    method.invoke(listener, event)
                } catch (e: Exception) {
                    log.error(e.message, e)
                }
            }
        }
    }

    private fun getMethods(listener: Listener): List<Method> {
        val methods = LinkedList<Method>()
        for (method in listener.javaClass.methods) {
            method.isAccessible = true
            if (method.returnType == Void.TYPE) {
                if (method.getAnnotation(EventHandler::class.java) != null) {
                    if (method.parameterCount == 1) {
                        val parameter = method.parameters[0]
                        if (ReflectionUtil.isExtended(parameter.type, Dhwu1aEvent::class.java)) {
                            methods.add(method)
                        }
                    }
                }
            }
        }
        return methods
    }
}