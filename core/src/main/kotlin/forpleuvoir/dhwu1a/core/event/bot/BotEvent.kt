package forpleuvoir.dhwu1a.core.event.bot

import com.google.common.collect.ImmutableList
import com.google.common.collect.ImmutableMap
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import forpleuvoir.dhwu1a.core.common.TYPE
import forpleuvoir.dhwu1a.core.event.base.Dhwu1aEvent
import forpleuvoir.dhwu1a.core.util.JsonUtil
import forpleuvoir.dhwu1a.core.util.ReflectionUtil
import java.util.function.Consumer

/**
 * Bot事件
 *
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.event.bot
 *
 * #class_name BotEvent
 *
 * #create_time 2021/7/3 20:25
 */
abstract class BotEvent protected constructor(
    /**
     * 事件类型
     */
    @field:SerializedName(TYPE) val type: BotEventType
) : Dhwu1aEvent() {

    override fun broadcastHandle(
        eventListeners: ImmutableMap<Class<out Dhwu1aEvent>, ImmutableList<Consumer<in Dhwu1aEvent>>>
    ) {
        super.broadcastHandle(eventListeners)
        if (ReflectionUtil.isExtended(this.javaClass, BotEvent::class.java)) {
            if (eventListeners.containsKey(BotEvent::class.java)) {
                for (listener in eventListeners[BotEvent::class.java]!!) {
                    listener.accept(this)
                }
            }
        }
    }

    override fun toPlainText(): String {
        return String.format("%s[%d]", type, bot!!.id)
    }

    companion object {
        @JvmStatic
        fun parse(`object`: JsonObject): BotEvent {
            val type = BotEventType.valueOf(`object`[TYPE].asString)
            return JsonUtil.gson.fromJson(`object`, type.clazz)!!.initialize() as BotEvent
        }
    }
}