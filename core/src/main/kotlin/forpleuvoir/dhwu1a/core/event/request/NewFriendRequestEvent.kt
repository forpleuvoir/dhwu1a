package forpleuvoir.dhwu1a.core.event.request

/**
 * 添加好友申请
 *
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.event.request
 *
 * #class_name NewFriendRequestEvent
 *
 * #create_time 2021/7/4 1:44
 */
class NewFriendRequestEvent private constructor(
    eventId: Long, fromId: Long, nick: String, message: String,
    groupId: Long
) : RequestEvent(RequestEventType.NewFriendRequestEvent, eventId, groupId, fromId, nick, message)