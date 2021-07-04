package forpleuvoir.dhwu1a.core.event.message;

import forpleuvoir.dhwu1a.core.message.OtherClientMessage;
import forpleuvoir.dhwu1a.core.user.base.User;

/**
 * 其他客户端消息事件
 *
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.event.message
 * <p>#class_name OtherClientMessageEvent
 * <p>#create_time 2021/7/4 12:05
 */
public class OtherClientMessageEvent extends MessageEvent<OtherClientMessage> {

    protected OtherClientMessageEvent(OtherClientMessage message) {
        super(message);
    }

    @Override
    public User getUser() {
        return null;
    }
}
