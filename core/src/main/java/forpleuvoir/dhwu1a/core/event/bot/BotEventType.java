package forpleuvoir.dhwu1a.core.event.bot;

/**
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.event.bot
 * <p>#class_name BotEventType
 * <p>#create_time 2021/7/3 20:28
 */
public enum BotEventType {
    BotOnlineEvent(BotOnlineEvent.class),
    BotOfflineEventActive(BotOfflineEventActive.class),
    BotOfflineEventForce(BotOfflineEventForce.class),
    BotOfflineEventDropped(BotOfflineEventDropped.class),
    BotReloginEvent(BotReloginEvent.class);


    private final Class<? extends BotEvent> clazz;

    BotEventType(Class<? extends BotEvent> clazz) {
        this.clazz = clazz;
    }

    public Class<? extends BotEvent> getClazz() {
        return clazz;
    }

    public static boolean hasKey(String key) {
        var isHas = false;
        for (BotEventType value : BotEventType.values()) {
                isHas = value.name().equals(key);
            if (isHas) break;
        }
        return isHas;
    }
}
