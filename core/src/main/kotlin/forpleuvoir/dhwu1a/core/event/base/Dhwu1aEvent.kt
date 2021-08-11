package forpleuvoir.dhwu1a.core.event.base

import com.google.common.collect.ImmutableList
import com.google.common.collect.ImmutableMap
import forpleuvoir.dhwu1a.core.Dhwu1a
import forpleuvoir.dhwu1a.core.common.IJsonData
import forpleuvoir.dhwu1a.core.message.base.IPlainText
import forpleuvoir.dhwu1a.core.user.bot.Bot
import forpleuvoir.dhwu1a.core.util.Dhwu1aLog
import forpleuvoir.dhwu1a.core.util.ReflectionUtil
import java.util.function.Consumer

/**
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.event.base
 *
 * #class_name Dhwu1aEvent
 *
 * #create_time 2021/7/2 20:58
 */
abstract class Dhwu1aEvent : IPlainText, IJsonData, IEventBroadcastHandler {
    /**
     * @see initialize
     */
    @Transient
    protected val log = Dhwu1aLog(this.javaClass)

    /**
     * @see initialize
     */
    @JvmField
    @Transient
    val bot: Bot = Dhwu1a.instance.bot

    /**
     * 所有非new实例化的事件必须调用此方法才能获取Bot以及log
     */
    fun initialize(): Dhwu1aEvent {
        ReflectionUtil.setFieldValue("log", this, Dhwu1aLog(this.javaClass))
        ReflectionUtil.setFieldValue("bot", this, Dhwu1a.instance.bot)
        return this
    }

    open fun printEventLog() {
        log.info("E/Bot.{}: {}", bot.id, toPlainText())
    }

    override fun broadcastHandle(eventListeners: ImmutableMap<Class<out Dhwu1aEvent>, ImmutableList<Consumer<in Dhwu1aEvent>>>) {
        if (eventListeners.containsKey(this.javaClass)) {
            for (listener in eventListeners[this.javaClass]!!) {
                listener.accept(this)
            }
        }
    }
}

