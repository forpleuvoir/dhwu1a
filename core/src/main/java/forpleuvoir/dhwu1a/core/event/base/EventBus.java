package forpleuvoir.dhwu1a.core.event.base;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import forpleuvoir.dhwu1a.core.util.Dhwu1aLog;
import org.java_websocket.util.NamedThreadFactory;

import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.Consumer;

/**
 * 事件总线
 *
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.event.base
 * <p>#class_name EventBus
 * <p>#create_time 2021/7/2 21:06
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class EventBus {
    private static final transient EventBus INSTANCE = new EventBus();

    public static EventBus getInstance() {
        return INSTANCE;
    }

    private static transient final Dhwu1aLog log = new Dhwu1aLog(EventBus.class);
    private transient final ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
    private transient final
    ConcurrentHashMap<Class<? extends Dhwu1aEvent>, ConcurrentLinkedQueue<Consumer<? extends Dhwu1aEvent>>>
            eventListeners = new ConcurrentHashMap<>();

    private EventBus() {
        executor.setThreadFactory(new NamedThreadFactory("Dhwu1a Event"));
    }

    public <E extends Dhwu1aEvent> void broadcast(E event) {
        executor.execute(() -> {
            try {
                event.printEventLog();
                event.broadcastHandle(getEventListeners());
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        });
    }

    public <E extends Dhwu1aEvent> void subscribe(Class<E> channel, Consumer<E> listener) {
        log.debug("事件订阅({})", channel.getName());
        if (eventListeners.containsKey(channel)) {
            if (!eventListeners.get(channel).contains(listener)) {
                eventListeners.get(channel).add(listener);
            }
        } else {
            eventListeners.put(channel, new ConcurrentLinkedQueue(Collections.singletonList(listener)));
        }
    }


    private ImmutableMap<Class<? extends Dhwu1aEvent>, ImmutableList<Consumer<? extends Dhwu1aEvent>>> getEventListeners() {
        var builder = new ImmutableMap.Builder<Class<? extends Dhwu1aEvent>, ImmutableList<Consumer<? extends Dhwu1aEvent>>>();
        eventListeners.forEach((k, v) -> {
            var listBuilder = new ImmutableList.Builder<Consumer<? extends Dhwu1aEvent>>();
            for (Consumer<? extends Dhwu1aEvent> consumer : v) {
                listBuilder.add(consumer);
            }
            builder.put(k, listBuilder.build());
        });
        return builder.build();
    }

}
