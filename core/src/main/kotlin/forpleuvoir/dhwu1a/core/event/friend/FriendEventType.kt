package forpleuvoir.dhwu1a.core.event.friend

/**
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.event.friend
 *
 * #class_name FriendEventType
 *
 * #create_time 2021/7/3 20:59
 */
enum class FriendEventType(val clazz: Class<out FriendEvent?>) {
    FriendInputStatusChangedEvent(forpleuvoir.dhwu1a.core.event.friend.FriendInputStatusChangedEvent::class.java),
    FriendNickChangedEvent(forpleuvoir.dhwu1a.core.event.friend.FriendNickChangedEvent::class.java),
    FriendRecallEvent(forpleuvoir.dhwu1a.core.event.friend.FriendRecallEvent::class.java);

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