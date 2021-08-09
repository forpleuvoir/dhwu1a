package forpleuvoir.dhwu1a.core.event.base

import com.google.common.collect.ImmutableList
import com.google.common.collect.ImmutableMap
import java.util.function.Consumer

/**
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.event.base
 *
 * #class_name IEventBroadcastHandler
 *
 * #create_time 2021/7/2 21:25
 */
interface IEventBroadcastHandler {
    fun broadcastHandle(
        eventListeners: ImmutableMap<Class<out Dhwu1aEvent>, ImmutableList<Consumer<in Dhwu1aEvent>>>
    )

}