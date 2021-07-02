package forpleuvoir.dhwu1a.core.event.base;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import forpleuvoir.dhwu1a.core.Dhwu1a;
import forpleuvoir.dhwu1a.core.message.base.IPlainText;
import forpleuvoir.dhwu1a.core.user.bot.Bot;
import forpleuvoir.dhwu1a.core.util.Dhwu1aLog;

import java.util.function.Consumer;

/**
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.event.base
 * <p>#class_name Dhwu1aEvent
 * <p>#create_time 2021/7/2 20:58
 */
public abstract class Dhwu1aEvent implements IPlainText, IEventBroadcastHandler {
    protected final Dhwu1aLog log;
    public transient final Bot bot = Dhwu1a.bot;

    public Dhwu1aEvent() {
        this.log = new Dhwu1aLog(this.getClass());
    }

    public void printEventLog() {
        log.info("E/Bot.{}: {}", bot.id, toPlainText());
    }

    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    public void broadcastHandle(
            ImmutableMap<Class<? extends Dhwu1aEvent>, ImmutableList<Consumer<? extends Dhwu1aEvent>>> eventListeners
    ) {
        if (eventListeners.containsKey(this.getClass())) {
            for (Consumer listener : eventListeners.get(this.getClass())) {
                listener.accept(this);
            }
        }
    }
}
