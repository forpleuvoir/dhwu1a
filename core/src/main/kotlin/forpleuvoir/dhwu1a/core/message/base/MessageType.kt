package forpleuvoir.dhwu1a.core.message.base

import forpleuvoir.dhwu1a.core.message.messagesender.*

/**
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.message.base
 *
 * #class_name MessageType
 *
 * #create_time 2021/6/29 22:35
 */
enum class MessageType(val clazz: Class<out MessageSender?>) {
    GroupMessage(GroupMessageSender::class.java),
    FriendMessage(FriendMessageSender::class.java),
    TempMessage(TempMessageSender::class.java),
    StrangerMessage(StrangerMessageSender::class.java),
    OtherClientMessage(OtherClientMessageSender::class.java);
}