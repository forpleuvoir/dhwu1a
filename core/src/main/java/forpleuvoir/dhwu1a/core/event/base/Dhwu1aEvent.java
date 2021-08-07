package forpleuvoir.dhwu1a.core.event.base;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import forpleuvoir.dhwu1a.core.Dhwu1a;
import forpleuvoir.dhwu1a.core.common.IJsonData;
import forpleuvoir.dhwu1a.core.message.base.IPlainText;
import forpleuvoir.dhwu1a.core.user.bot.Bot;
import forpleuvoir.dhwu1a.core.util.Dhwu1aLog;
import forpleuvoir.dhwu1a.core.util.ReflectionUtil;

import java.util.function.Consumer;

/**
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.event.base
 * <p>#class_name Dhwu1aEvent
 * <p>#create_time 2021/7/2 20:58
 */
public abstract class Dhwu1aEvent implements IPlainText, IJsonData, IEventBroadcastHandler {
    /**
     * @see #initialize()
     */
    protected transient final Dhwu1aLog log = new Dhwu1aLog(this.getClass());
    /**
     * @see #initialize()
     */
    public transient final Bot bot = Dhwu1a.getInstance().getBot();

    /**
     * 所有非new实例化的事件必须调用此方法才能获取Bot以及log
     */
    public Dhwu1aEvent initialize() {
        ReflectionUtil.setFieldValue("log", this, new Dhwu1aLog(this.getClass()));
        ReflectionUtil.setFieldValue("bot", this, Dhwu1a.getInstance().getBot());
        return this;
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
