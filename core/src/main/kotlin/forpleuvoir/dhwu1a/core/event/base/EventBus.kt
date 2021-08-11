package forpleuvoir.dhwu1a.core.event.base

import com.google.common.collect.ImmutableList
import com.google.common.collect.ImmutableMap
import forpleuvoir.dhwu1a.core.event.base.annotation.EventHandlerParser
import forpleuvoir.dhwu1a.core.util.Dhwu1aLog
import org.java_websocket.util.NamedThreadFactory
import org.jetbrains.annotations.NotNull
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.Executors
import java.util.concurrent.ThreadPoolExecutor
import java.util.function.Consumer

/**
 * 事件总线
 *
 * @author forpleuvoir

 * #project_name dhwu1a

 * #package forpleuvoir.dhwu1a.core.event.base

 * #class_name EventBus

 * #create_time 2021/8/10 0:37
 */
class EventBus private constructor() {

    companion object {
        val instance: EventBus by lazy { EventBus() }
    }

    @Transient
    private val log = Dhwu1aLog(EventBus::class.java)

    @Transient
    private val executor = Executors.newCachedThreadPool() as ThreadPoolExecutor

    @Transient
    private val eventListeners =
        ConcurrentHashMap<Class<out Dhwu1aEvent>, ConcurrentLinkedQueue<Consumer<in Dhwu1aEvent>>>()

    fun <E : Dhwu1aEvent> broadcast(event: E) {
        executor.execute {
            try {
                event.printEventLog()
                event.broadcastHandle(getEventListeners())
            } catch (e: Exception) {
                log.error(e.message, e)
            }
        }
    }

    inline fun <reified E : Dhwu1aEvent> subscribe(crossinline listener: (E) -> Unit) {
        subscribe(E::class.java) {
            listener.invoke(it)
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun <E : Dhwu1aEvent> subscribe(channel: Class<out E>, listener: Consumer<in E>) {
        log.debug("事件订阅({})", channel.name)
        if (eventListeners.containsKey(channel)) {
            eventListeners[channel]!!.add(listener as Consumer<in Dhwu1aEvent>?)
        } else {
            eventListeners[channel] =
                ConcurrentLinkedQueue<Consumer<in Dhwu1aEvent>>(listOf(listener as Consumer<in Dhwu1aEvent>?))
        }
    }

    fun registerEventsListener(@NotNull listener: Listener) {
        EventHandlerParser.parse(listener, this)
    }

    private fun getEventListeners(): ImmutableMap<Class<out Dhwu1aEvent>, ImmutableList<Consumer<in Dhwu1aEvent>>> {
        val builder = ImmutableMap.Builder<Class<out Dhwu1aEvent>, ImmutableList<Consumer<in Dhwu1aEvent>>>()
        eventListeners.forEach { (k: Class<out Dhwu1aEvent>, v: ConcurrentLinkedQueue<Consumer<in Dhwu1aEvent>>) ->
            val listBuilder =
                ImmutableList.Builder<Consumer<in Dhwu1aEvent>>()
            for (consumer in v) {
                listBuilder.add(consumer)
            }
            builder.put(k, listBuilder.build())
        }
        return builder.build()
    }

    init {
        executor.threadFactory = NamedThreadFactory("event bus")
    }


}