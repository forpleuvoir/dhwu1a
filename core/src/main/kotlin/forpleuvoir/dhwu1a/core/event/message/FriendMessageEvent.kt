package forpleuvoir.dhwu1a.core.event.message

import forpleuvoir.dhwu1a.core.message.FriendMessage
import forpleuvoir.dhwu1a.core.user.Friend
import forpleuvoir.dhwu1a.core.user.base.User

/**
 * 好友消息事件
 *
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.event.message
 *
 * #class_name FriendMessageEvent
 *
 * #create_time 2021/7/4 11:46
 */
class FriendMessageEvent(message: FriendMessage) : MessageEvent<FriendMessage>(message) {
    /**
     * 事件相关好友
     */
    val friend: Friend

    override val user: User
        get() = friend

    init {
        friend = message.friend
    }
}