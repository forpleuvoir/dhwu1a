package forpleuvoir.dhwu1a.core.event.request

import com.google.common.collect.ImmutableList
import com.google.common.collect.ImmutableMap
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import forpleuvoir.dhwu1a.core.common.*
import forpleuvoir.dhwu1a.core.event.base.Dhwu1aEvent
import forpleuvoir.dhwu1a.core.util.JsonUtil
import forpleuvoir.dhwu1a.core.util.ReflectionUtil
import java.util.function.Consumer

/**
 * 申请事件
 *
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.event.request
 *
 * #class_name RequestEvent
 *
 * #create_time 2021/7/4 1:42
 */
abstract class RequestEvent protected constructor(
    /**
     * 事件类型
     */
    @field:SerializedName(TYPE) val type: RequestEventType,
    /**
     * 事件标识，响应该事件时的标识
     */
    @field:SerializedName(EVENT_ID) val eventId: Long,
    /**
     * 申请人QQ号
     */
    @field:SerializedName(FROM_ID) val fromId: Long,
    /**
     * 相关群号 没有则为0
     */
    @field:SerializedName(GROUP_ID) val groupId: Long,
    /**
     * 邀请人（好友）的昵称或群名片
     */
    @field:SerializedName(NICK) val nick: String,
    /**
     * 邀请消息
     */
    @field:SerializedName(MESSAGE) val message: String
) : Dhwu1aEvent() {
    override fun broadcastHandle(
        eventListeners: ImmutableMap<Class<out Dhwu1aEvent>, ImmutableList<Consumer<in Dhwu1aEvent>>>
    ) {
        super.broadcastHandle(eventListeners)
        if (ReflectionUtil.isExtended(this.javaClass, RequestEvent::class.java)) {
            if (eventListeners.containsKey(RequestEvent::class.java)) {
                for (listener in eventListeners[RequestEvent::class.java]!!) {
                    listener.accept(this)
                }
            }
        }
    }

    override fun toPlainText(): String {
        return String.format("%s[eventId:%d,fromId:%d,groupId:%d,message:%s]", type, eventId, fromId, groupId, message)
    }

    companion object {
        fun parse(`object`: JsonObject): RequestEvent {
            val type = RequestEventType.valueOf(`object`[TYPE].asString)
            return JsonUtil.gson.fromJson(`object`, type.clazz)!!.initialize() as RequestEvent
        }
    }
}