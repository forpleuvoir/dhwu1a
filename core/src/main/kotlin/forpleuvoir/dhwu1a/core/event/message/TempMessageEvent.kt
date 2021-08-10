package forpleuvoir.dhwu1a.core.event.message

import forpleuvoir.dhwu1a.core.message.TempMessage
import forpleuvoir.dhwu1a.core.user.Group
import forpleuvoir.dhwu1a.core.user.Member

/**
 * 临时消息事件
 *
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.event.message
 *
 * #class_name TempMessageEvent
 *
 * #create_time 2021/7/4 11:59
 */
class TempMessageEvent(message: TempMessage) : MessageEvent<TempMessage?>(message) {
    /**
     * 事件相关群
     */
    val group: Group

    /**
     * 事件相关群员
     */
    val member: Member?
    override fun getUser(): Member? {
        return message!!.member
    }

    init {
        member = message.member
        group = member!!.group
    }
}