package forpleuvoir.dhwu1a.core.event.message

import forpleuvoir.dhwu1a.core.message.StrangerMessage
import forpleuvoir.dhwu1a.core.user.base.User

/**
 * 陌生人消息事件
 *
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.event.message
 *
 * #class_name StrangerMessageEvent
 *
 * #create_time 2021/7/4 12:06
 */
class StrangerMessageEvent(message: StrangerMessage) : MessageEvent<StrangerMessage>(message) {
    override val user: User?
        get() = null

}