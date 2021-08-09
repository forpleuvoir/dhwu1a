package forpleuvoir.dhwu1a.core.event.message;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import forpleuvoir.dhwu1a.core.event.base.Dhwu1aEvent;
import forpleuvoir.dhwu1a.core.message.*;
import forpleuvoir.dhwu1a.core.message.base.Message;
import forpleuvoir.dhwu1a.core.user.base.User;
import forpleuvoir.dhwu1a.core.util.ReflectionUtil;

import javax.annotation.Nullable;
import java.util.function.Consumer;

/**
 * 消息事件
 *
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.event.message
 * <p>#class_name MessageEvent
 * <p>#create_time 2021/7/4 2:28
 */
public abstract class MessageEvent<M extends Message> extends Dhwu1aEvent {
    /**
     * 所有消息事件都有相关消息
     */
    public final M message;

    protected MessageEvent(M message) {
        this.message = message;
    }

    public static MessageEvent<?> parse(Message message) {
        return switch (message.type) {
            case GroupMessage -> new GroupMessageEvent((GroupMessage) message);
            case FriendMessage -> new FriendMessageEvent((FriendMessage) message);
            case TempMessage -> new TempMessageEvent((TempMessage) message);
            case StrangerMessage -> new StrangerMessageEvent((StrangerMessage) message);
            case OtherClientMessage -> new OtherClientMessageEvent((OtherClientMessage) message);
        };
    }

    @Override
    public void printEventLog() {
        message.printMessageLog();
    }

    @Nullable
    public abstract User getUser();

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void broadcastHandle(
            ImmutableMap<Class<? extends Dhwu1aEvent>, ImmutableList<Consumer<? extends Dhwu1aEvent>>> eventListeners
    ) {
        super.broadcastHandle(eventListeners);
        if (ReflectionUtil.isExtended(this.getClass(), MessageEvent.class)) {
            if (eventListeners.containsKey(MessageEvent.class)) {
                for (Consumer listener : eventListeners.get(MessageEvent.class)) {
                    listener.accept(this);
                }
            }
        }
    }

    @Override
    public String toPlainText() {
        return String.format("%s[%s]", message.type, message.toPlainText());
    }

    @Override
    public String toString() {
        return toPlainText();
    }
}
