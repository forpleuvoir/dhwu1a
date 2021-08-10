package forpleuvoir.dhwu1a.core.event.message

import forpleuvoir.dhwu1a.core.message.OtherClientMessage
import forpleuvoir.dhwu1a.core.user.base.User

/**
 * 其他客户端消息事件
 *
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.event.message
 *
 * #class_name OtherClientMessageEvent
 *
 * #create_time 2021/7/4 12:05
 */
class OtherClientMessageEvent(message: OtherClientMessage) : MessageEvent<OtherClientMessage>(message) {

    override val user: User?
        get() = null
}