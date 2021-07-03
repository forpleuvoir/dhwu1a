package forpleuvoir.dhwu1a.core.event.bot;

/**
 * Bot主动重新登录
 *
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.event.bot
 * <p>#class_name BotReloginEvent
 * <p>#create_time 2021/7/3 20:48
 */
public class BotReloginEvent extends BotEvent {
    public BotReloginEvent() {
        super(BotEventType.BotReloginEvent);
    }
}
