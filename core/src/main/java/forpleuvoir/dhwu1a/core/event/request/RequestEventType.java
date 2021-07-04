package forpleuvoir.dhwu1a.core.event.request;

/**
 * 申请事件类型
 *
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.event.request
 * <p>#class_name RequestEventType
 * <p>#create_time 2021/7/4 1:43
 */
public enum RequestEventType {
    NewFriendRequestEvent(NewFriendRequestEvent.class),
    MemberJoinRequestEvent(MemberJoinRequestEvent.class),
    BotInvitedJoinGroupRequestEvent(BotInvitedJoinGroupRequestEvent.class);


    private final Class<? extends RequestEvent> clazz;

    RequestEventType(Class<? extends RequestEvent> clazz) {
        this.clazz = clazz;
    }

    public Class<? extends RequestEvent> getClazz() {
        return clazz;
    }


    public static boolean hasKey(String key) {
        var isHas = false;
        for (RequestEventType value : RequestEventType.values()) {
            isHas = value.name().equals(key);
            if (isHas) break;
        }
        return isHas;
    }
}
