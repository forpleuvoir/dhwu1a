package forpleuvoir.dhwu1a.core.event.request

import com.google.gson.annotations.SerializedName
import forpleuvoir.dhwu1a.core.common.GROUP_NAME

/**
 * Bot被邀请入群申请
 *
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.event.request
 *
 * #class_name BotInvitedJoinGroupRequestEvent
 *
 * #create_time 2021/7/4 2:02
 */
class BotInvitedJoinGroupRequestEvent private constructor(
    eventId: Long, fromId: Long, nick: String,
    message: String,
    groupId: Long,
    /**
     * 被邀请进入群的群名称
     */
    @field:SerializedName(GROUP_NAME) val groupName: String
) : RequestEvent(RequestEventType.BotInvitedJoinGroupRequestEvent, eventId, fromId, groupId, nick, message)