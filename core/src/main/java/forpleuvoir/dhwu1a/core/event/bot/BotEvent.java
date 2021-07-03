package forpleuvoir.dhwu1a.core.event.bot;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import forpleuvoir.dhwu1a.core.event.base.Dhwu1aEvent;
import forpleuvoir.dhwu1a.core.util.JsonUtil;
import forpleuvoir.dhwu1a.core.util.ReflectionUtil;

import java.util.function.Consumer;

import static forpleuvoir.dhwu1a.core.common.ApiKey.TYPE;

/**
 * Bot事件
 *
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.event.bot
 * <p>#class_name BotEvent
 * <p>#create_time 2021/7/3 20:25
 */
public abstract class BotEvent extends Dhwu1aEvent {
    /**
     * 事件类型
     */
    @SerializedName(TYPE)
    public final BotEventType type;

    protected BotEvent(BotEventType type) {
        this.type = type;
    }

    public static BotEvent parse(JsonObject object) {
        var type = BotEventType.valueOf(object.get(TYPE).getAsString());
        return (BotEvent) JsonUtil.gson.fromJson(object, type.getClazz()).initialize();
    }

    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    public void broadcastHandle(
            ImmutableMap<Class<? extends Dhwu1aEvent>, ImmutableList<Consumer<? extends Dhwu1aEvent>>> eventListeners
    ) {
        super.broadcastHandle(eventListeners);
        if (ReflectionUtil.isExtended(this.getClass(), BotEvent.class)) {
            if (eventListeners.containsKey(BotEvent.class)) {
                for (Consumer listener : eventListeners.get(BotEvent.class)) {
                    listener.accept(this);
                }
            }
        }
    }

    @Override
    public String toPlainText() {
        return String.format("%s[%d]", type, bot.id);
    }
}
