package forpleuvoir.dhwu1a.core.event.request

/**
 * 申请事件类型
 *
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.event.request
 *
 * #class_name RequestEventType
 *
 * #create_time 2021/7/4 1:43
 */
enum class RequestEventType(val clazz: Class<out RequestEvent?>) {
    NewFriendRequestEvent(forpleuvoir.dhwu1a.core.event.request.NewFriendRequestEvent::class.java),
    MemberJoinRequestEvent(forpleuvoir.dhwu1a.core.event.request.MemberJoinRequestEvent::class.java),
    BotInvitedJoinGroupRequestEvent(forpleuvoir.dhwu1a.core.event.request.BotInvitedJoinGroupRequestEvent::class.java);

    companion object {
        fun hasKey(key: String): Boolean {
            var isHas = false
            for (value in values()) {
                isHas = value.name == key
                if (isHas) break
            }
            return isHas
        }
    }
}