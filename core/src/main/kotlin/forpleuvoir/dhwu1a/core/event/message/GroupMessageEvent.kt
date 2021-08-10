package forpleuvoir.dhwu1a.core.event.message

import forpleuvoir.dhwu1a.core.message.GroupMessage
import forpleuvoir.dhwu1a.core.user.Group
import forpleuvoir.dhwu1a.core.user.Member

/**
 * 群消息事件
 *
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.event.message
 *
 * #class_name GroupMessageEvent
 *
 * #create_time 2021/7/4 11:50
 */
class GroupMessageEvent(message: GroupMessage) : MessageEvent<GroupMessage?>(message) {
    /**
     * 事件相关群
     */
    val group: Group?

    /**
     * 事件相关群员
     */
    val member: Member?
    override fun getUser(): Group? {
        return group
    }

    init {
        group = message.group
        member = message.member
    }
}