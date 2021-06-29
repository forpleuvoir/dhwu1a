package forpleuvoir.dhwu1a.core.message.base;

import forpleuvoir.dhwu1a.core.message.messageitem.MessageItem;
import forpleuvoir.dhwu1a.core.user.User;
import forpleuvoir.dhwu1a.core.user.bot.Bot;

import java.util.List;

/**
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.message.base
 * <p>#class_name Message
 * <p>#create_time 2021/6/29 22:16
 */
public abstract class Message implements IMessage {
    /**
     * 接收消息的Bot
     */
    public final Bot bot;
    /**
     * 消息类型
     */
    protected MessageType type;
    /**
     * 消息发送者
     */
    protected User sender;
    /**
     * 消息链
     */
    protected List<MessageItem> messageChain;

    public Message(Bot bot) {
        this.bot = bot;
    }
}
