package forpleuvoir.dhwu1a.core.event.message;

import forpleuvoir.dhwu1a.core.message.StrangerMessage;
import forpleuvoir.dhwu1a.core.user.base.User;

import javax.annotation.Nullable;

/**
 * 陌生人消息事件
 *
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.event.message
 * <p>#class_name StrangerMessageEvent
 * <p>#create_time 2021/7/4 12:06
 */
public class StrangerMessageEvent extends MessageEvent<StrangerMessage> {

    public StrangerMessageEvent(StrangerMessage message) {
        super(message);
    }

    @Nullable
    @Override
    public User getUser() {
        return null;
    }
}
