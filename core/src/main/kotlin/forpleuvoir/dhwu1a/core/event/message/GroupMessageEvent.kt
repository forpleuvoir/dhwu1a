package forpleuvoir.dhwu1a.core.event.message

import forpleuvoir.dhwu1a.core.message.GroupMessage
import forpleuvoir.dhwu1a.core.user.Group
import forpleuvoir.dhwu1a.core.user.Member
import forpleuvoir.dhwu1a.core.user.base.User

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
class GroupMessageEvent(message: GroupMessage) : MessageEvent<GroupMessage>(message) {
    /**
     * 事件相关群
     */
    val group: Group

    /**
     * 事件相关群员
     */
    val member: Member?

    override val user: User
        get() = group

    init {
        group = message.group
        member = message.member
    }


}