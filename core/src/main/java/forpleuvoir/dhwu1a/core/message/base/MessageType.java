package forpleuvoir.dhwu1a.core.message.base;

import forpleuvoir.dhwu1a.core.message.messagesender.*;

/**
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.message.base
 * <p>#class_name MessageType
 * <p>#create_time 2021/6/29 22:35
 */
public enum MessageType {
    GroupMessage(GroupMessageSender.class),
    FriendMessage(FriendMessageSender.class),
    TempMessage(TempMessageSender.class),
    StrangerMessage(StrangerMessageSender.class),
    OtherClientMessage(OtherClientMessageSender.class);

    private final Class<? extends MessageSender> clazz;

    MessageType(Class<? extends MessageSender> clazz) {
        this.clazz = clazz;
    }

    public Class<? extends MessageSender> getClazz() {
        return clazz;
    }
}
