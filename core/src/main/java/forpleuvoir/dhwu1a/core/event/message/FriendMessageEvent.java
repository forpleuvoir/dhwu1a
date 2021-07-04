package forpleuvoir.dhwu1a.core.event.message;

import forpleuvoir.dhwu1a.core.message.FriendMessage;
import forpleuvoir.dhwu1a.core.user.Friend;
import forpleuvoir.dhwu1a.core.user.base.User;

/**
 * 好友消息事件
 *
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.event.message
 * <p>#class_name FriendMessageEvent
 * <p>#create_time 2021/7/4 11:46
 */
public class FriendMessageEvent extends MessageEvent<FriendMessage> {
    /**
     * 事件相关好友
     */
    public final Friend friend;

    protected FriendMessageEvent(FriendMessage message) {
        super(message);
        this.friend = message.friend;
    }

    @Override
    public User getUser() {
        return friend;
    }
}
