package forpleuvoir.dhwu1a.core.event.request

import com.google.gson.annotations.SerializedName
import forpleuvoir.dhwu1a.core.common.*

/**
 * 用户入群申请（Bot需要有管理员权限）
 *
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.event.request
 *
 * #class_name MemberJoinRequestEvent
 *
 * #create_time 2021/7/4 2:02
 */
class MemberJoinRequestEvent private constructor(
    eventId: Long, fromId: Long, nick: String, message: String,
    groupId: Long,
    /**
     * 申请人申请入群的群名称
     */
    @field:SerializedName(GROUP_NAME) val groupName: String
) : RequestEvent(RequestEventType.MemberJoinRequestEvent, eventId, fromId, groupId, nick, message)