package forpleuvoir.dhwu1a.core.event.bot;

/**
 * Bot被服务器断开或因网络问题而掉线
 *
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.event.bot
 * <p>#class_name BotOfflineEventDropped
 * <p>#create_time 2021/7/3 20:47
 */
public class BotOfflineEventDropped extends BotEvent {
    public BotOfflineEventDropped() {
        super(BotEventType.BotOfflineEventDropped);
    }
}
