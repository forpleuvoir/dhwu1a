package forpleuvoir.dhwu1a.core.event.base;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import java.util.function.Consumer;

/**
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.event.base
 * <p>#class_name IEventBroadcastHandler
 * <p>#create_time 2021/7/2 21:25
 */
@FunctionalInterface
public interface IEventBroadcastHandler {
    void broadcastHandle(final
                         ImmutableMap<Class<? extends Dhwu1aEvent>, ImmutableList<Consumer<? extends Dhwu1aEvent>>>
                                 eventListeners
    );
}
