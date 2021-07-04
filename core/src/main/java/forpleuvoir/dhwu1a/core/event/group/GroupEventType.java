package forpleuvoir.dhwu1a.core.event.group;

/**
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.event.group
 * <p>#class_name GroupEventType
 * <p>#create_time 2021/7/3 21:45
 */
public enum GroupEventType {
    BotGroupPermissionChangeEvent(BotGroupPermissionChangeEvent.class),
    BotMuteEvent(BotMuteEvent.class),
    BotUnmuteEvent(BotUnmuteEvent.class),
    BotJoinGroupEvent(BotJoinGroupEvent.class),
    BotLeaveEventActive(BotLeaveEventActive.class),
    BotLeaveEventKick(BotLeaveEventKick.class),
    GroupRecallEvent(GroupRecallEvent.class),
    GroupNameChangeEvent(GroupNameChangeEvent.class),
    GroupEntranceAnnouncementChangeEvent(GroupEntranceAnnouncementChangeEvent.class),
    GroupMuteAllEvent(GroupMuteAllEvent.class),
    GroupAllowAnonymousChatEvent(GroupAllowAnonymousChatEvent.class),
    GroupAllowConfessTalkEvent(GroupAllowConfessTalkEvent.class),
    GroupAllowMemberInviteEvent(GroupAllowMemberInviteEvent.class),
    MemberJoinEvent(MemberJoinEvent.class),
    MemberLeaveEventKick(MemberLeaveEventKick.class),
    MemberLeaveEventQuit(MemberLeaveEventQuit.class),
    MemberCardChangeEvent(MemberCardChangeEvent.class),
    MemberSpecialTitleChangeEvent(MemberSpecialTitleChangeEvent.class),
    MemberPermissionChangeEvent(MemberPermissionChangeEvent.class),
    MemberMuteEvent(MemberMuteEvent.class),
    MemberUnmuteEvent(MemberUnmuteEvent.class),
    MemberHonorChangeEvent(MemberHonorChangeEvent.class);

    private final Class<? extends GroupEvent> clazz;

    GroupEventType(Class<? extends GroupEvent> clazz) {
        this.clazz = clazz;
    }

    public Class<? extends GroupEvent> getClazz() {
        return clazz;
    }

    public static boolean hasKey(String key) {
        var isHas = false;
        for (GroupEventType value : GroupEventType.values()) {
            isHas = value.equals(GroupEventType.valueOf(key));
            if (isHas) break;
        }
        return isHas;
    }
}
