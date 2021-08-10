package forpleuvoir.dhwu1a.core.event.group

/**
 * @author forpleuvoir
 *
 * #project_name dhwu1a
 *
 * #package forpleuvoir.dhwu1a.core.event.group
 *
 * #class_name GroupEventType
 *
 * #create_time 2021/7/3 21:45
 */
enum class GroupEventType(val clazz: Class<out GroupEvent>) {
    BotGroupPermissionChangeEvent(forpleuvoir.dhwu1a.core.event.group.BotGroupPermissionChangeEvent::class.java),
    BotMuteEvent(forpleuvoir.dhwu1a.core.event.group.BotMuteEvent::class.java),
    BotUnmuteEvent(forpleuvoir.dhwu1a.core.event.group.BotUnmuteEvent::class.java),
    BotJoinGroupEvent(forpleuvoir.dhwu1a.core.event.group.BotJoinGroupEvent::class.java),
    BotLeaveEventActive(forpleuvoir.dhwu1a.core.event.group.BotLeaveEventActive::class.java),
    BotLeaveEventKick(forpleuvoir.dhwu1a.core.event.group.BotLeaveEventKick::class.java),
    GroupRecallEvent(forpleuvoir.dhwu1a.core.event.group.GroupRecallEvent::class.java),
    GroupNameChangeEvent(forpleuvoir.dhwu1a.core.event.group.GroupNameChangeEvent::class.java),
    GroupEntranceAnnouncementChangeEvent(forpleuvoir.dhwu1a.core.event.group.GroupEntranceAnnouncementChangeEvent::class.java),
    GroupMuteAllEvent(forpleuvoir.dhwu1a.core.event.group.GroupMuteAllEvent::class.java),
    GroupAllowAnonymousChatEvent(forpleuvoir.dhwu1a.core.event.group.GroupAllowAnonymousChatEvent::class.java),
    GroupAllowConfessTalkEvent(forpleuvoir.dhwu1a.core.event.group.GroupAllowConfessTalkEvent::class.java),
    GroupAllowMemberInviteEvent(forpleuvoir.dhwu1a.core.event.group.GroupAllowMemberInviteEvent::class.java),
    MemberJoinEvent(forpleuvoir.dhwu1a.core.event.group.MemberJoinEvent::class.java),
    MemberLeaveEventKick(forpleuvoir.dhwu1a.core.event.group.MemberLeaveEventKick::class.java),
    MemberLeaveEventQuit(forpleuvoir.dhwu1a.core.event.group.MemberLeaveEventQuit::class.java),
    MemberCardChangeEvent(forpleuvoir.dhwu1a.core.event.group.MemberCardChangeEvent::class.java),
    MemberSpecialTitleChangeEvent(forpleuvoir.dhwu1a.core.event.group.MemberSpecialTitleChangeEvent::class.java),
    MemberPermissionChangeEvent(forpleuvoir.dhwu1a.core.event.group.MemberPermissionChangeEvent::class.java),
    MemberMuteEvent(forpleuvoir.dhwu1a.core.event.group.MemberMuteEvent::class.java),
    MemberUnmuteEvent(forpleuvoir.dhwu1a.core.event.group.MemberUnmuteEvent::class.java),
    MemberHonorChangeEvent(forpleuvoir.dhwu1a.core.event.group.MemberHonorChangeEvent::class.java);

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