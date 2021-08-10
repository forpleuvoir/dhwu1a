package forpleuvoir.dhwu1a.core.event.message

import com.google.common.collect.ImmutableList
import com.google.common.collect.ImmutableMap
import forpleuvoir.dhwu1a.core.event.base.Dhwu1aEvent
import forpleuvoir.dhwu1a.core.message.*
import forpleuvoir.dhwu1a.core.message.base.Message
import forpleuvoir.dhwu1a.core.message.base.MessageType
import forpleuvoir.dhwu1a.core.user.base.User
import forpleuvoir.dhwu1a.core.util.ReflectionUtil.isExtended
import java.util.function.Consumer

/**
 * 消息事件
 * @author forpleuvoir

 * #project_name dhwu1a

 * #package forpleuvoir.dhwu1a.core.event.message

 * #class_name MessageEvent

 * #create_time 2021/8/10 22:14
 */
abstract class MessageEvent<M : Message>(val message: M) : Dhwu1aEvent() {
    companion object {
        @JvmStatic
        @Suppress("UNCHECKED_CAST")
        fun parse(message: Message): MessageEvent<Message> {
            return when (message.type) {
                MessageType.GroupMessage -> GroupMessageEvent(message as GroupMessage) as MessageEvent<Message>
                MessageType.FriendMessage -> FriendMessageEvent(message as FriendMessage) as MessageEvent<Message>
                MessageType.TempMessage -> TempMessageEvent(message as TempMessage) as MessageEvent<Message>
                MessageType.StrangerMessage -> StrangerMessageEvent(message as StrangerMessage) as MessageEvent<Message>
                MessageType.OtherClientMessage -> OtherClientMessageEvent(message as OtherClientMessage) as MessageEvent<Message>
            }
        }
    }

    abstract val user: User?

    override fun printEventLog() {
        message.printMessageLog()
    }

    override fun broadcastHandle(eventListeners: ImmutableMap<Class<out Dhwu1aEvent>, ImmutableList<Consumer<in Dhwu1aEvent>>>) {
        super.broadcastHandle(eventListeners)
        if (isExtended(this.javaClass, MessageEvent::class.java)) {
            if (eventListeners.containsKey(MessageEvent::class.java)) {
                for (listener in eventListeners[MessageEvent::class.java]!!) {
                    listener.accept(this)
                }
            }
        }
    }

    override fun toPlainText(): String {
        return String.format("%s[%s]", message.type, message.toPlainText())
    }

    override fun toString(): String {
        return toPlainText()
    }

}