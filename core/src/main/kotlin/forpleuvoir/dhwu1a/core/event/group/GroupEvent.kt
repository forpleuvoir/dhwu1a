package forpleuvoir.dhwu1a.core.event.group

import com.google.common.collect.ImmutableList
import com.google.common.collect.ImmutableMap
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import forpleuvoir.dhwu1a.core.common.TYPE
import forpleuvoir.dhwu1a.core.event.base.Dhwu1aEvent
import forpleuvoir.dhwu1a.core.user.Group
import forpleuvoir.dhwu1a.core.util.JsonUtil
import forpleuvoir.dhwu1a.core.util.ReflectionUtil
import java.util.function.Consumer

/**
 * 群事件
 *
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.event.group
 *
 * #class_name GroupEvent
 *
 * #create_time 2021/7/3 21:45
 */
abstract class GroupEvent protected constructor(
    /**
     * 事件类型
     */
    @field:SerializedName(TYPE) val type: GroupEventType
) : Dhwu1aEvent() {

    abstract fun getGroup(): Group?

    override fun broadcastHandle(
        eventListeners: ImmutableMap<Class<out Dhwu1aEvent>, ImmutableList<Consumer<in Dhwu1aEvent>>>
    ) {
        super.broadcastHandle(eventListeners)
        if (ReflectionUtil.isExtended(this.javaClass, GroupEvent::class.java)) {
            if (eventListeners.containsKey(GroupEvent::class.java)) {
                for (listener in eventListeners[GroupEvent::class.java]!!) {
                    listener.accept(this)
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun parse(`object`: JsonObject): GroupEvent {
            val type = GroupEventType.valueOf(`object`[TYPE].asString)
            return JsonUtil.gson.fromJson(`object`, type.clazz).initialize() as GroupEvent
        }
    }
}