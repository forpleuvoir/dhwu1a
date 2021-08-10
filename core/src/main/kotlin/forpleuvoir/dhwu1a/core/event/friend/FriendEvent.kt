package forpleuvoir.dhwu1a.core.event.friend

import com.google.common.collect.ImmutableList
import com.google.common.collect.ImmutableMap
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import forpleuvoir.dhwu1a.core.common.FRIEND
import forpleuvoir.dhwu1a.core.common.TYPE
import forpleuvoir.dhwu1a.core.common.data.FriendData
import forpleuvoir.dhwu1a.core.event.base.Dhwu1aEvent
import forpleuvoir.dhwu1a.core.user.Friend
import forpleuvoir.dhwu1a.core.util.JsonUtil
import forpleuvoir.dhwu1a.core.util.ReflectionUtil
import java.util.*
import java.util.concurrent.atomic.AtomicReference
import java.util.function.Consumer

/**
 * 好友事件
 *
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.event.friend
 *
 * #class_name FriendEvent
 *
 * #create_time 2021/7/3 20:55
 */
abstract class FriendEvent protected constructor(
    /**
     * 好友事件类型
     */
    @field:SerializedName(TYPE) val type: FriendEventType, friend: FriendData?
) : Dhwu1aEvent() {
    /**
     * 事件相关好友
     */
    @SerializedName(FRIEND)
    val friend: FriendData?
    open fun getFriend(): Friend? {
        val f: AtomicReference<Friend> = AtomicReference<Friend>()
        Optional.ofNullable<FriendData>(friend)
            .ifPresent { friendData: FriendData -> f.set(friendData.user) }
        return f.get()
    }

    override fun broadcastHandle(
        eventListeners: ImmutableMap<Class<out Dhwu1aEvent>, ImmutableList<Consumer<in Dhwu1aEvent>>>
    ) {
        super.broadcastHandle(eventListeners)
        if (ReflectionUtil.isExtended(this.javaClass, FriendEvent::class.java)) {
            if (eventListeners.containsKey(FriendEvent::class.java)) {
                for (listener in eventListeners[FriendEvent::class.java]!!) {
                    listener.accept(this)
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun parse(`object`: JsonObject): FriendEvent {
            val type = FriendEventType.valueOf(`object`[TYPE].asString)
            return JsonUtil.gson.fromJson(`object`, type.clazz)!!.initialize() as FriendEvent
        }
    }

    init {
        this.friend = friend
    }
}