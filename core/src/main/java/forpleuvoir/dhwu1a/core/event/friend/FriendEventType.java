package forpleuvoir.dhwu1a.core.event.friend;

/**
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.event.friend
 * <p>#class_name FriendEventType
 * <p>#create_time 2021/7/3 20:59
 */
public enum FriendEventType {
    FriendInputStatusChangedEvent(FriendInputStatusChangedEvent.class),
    FriendNickChangedEvent(FriendNickChangedEvent.class),
    FriendRecallEvent(FriendRecallEvent.class);


    private final Class<? extends FriendEvent> clazz;

    FriendEventType(Class<? extends FriendEvent> clazz) {
        this.clazz = clazz;
    }

    public Class<? extends FriendEvent> getClazz() {
        return clazz;
    }

    public static boolean hasKey(String key) {
        var isHas = false;
        for (FriendEventType value : FriendEventType.values()) {
            isHas = value.name().equals(key);
            if (isHas) break;
        }
        return isHas;
    }
}
