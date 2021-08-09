package forpleuvoir.dhwu1a.core.message.base;

import com.google.gson.JsonObject;
import forpleuvoir.dhwu1a.core.Dhwu1a;
import forpleuvoir.dhwu1a.core.message.*;
import forpleuvoir.dhwu1a.core.message.messageitem.Source;
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItem;
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItemType;
import forpleuvoir.dhwu1a.core.user.bot.Bot;
import forpleuvoir.dhwu1a.core.util.Dhwu1aLog;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

import static forpleuvoir.dhwu1a.core.common.ApiKey.*;

/**
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.message.base
 * <p>#class_name Message
 * <p>#create_time 2021/6/29 22:16
 */
public abstract class Message implements IMessage {
    private transient static final Dhwu1aLog LOG = new Dhwu1aLog(Message.class);
    protected transient final Dhwu1aLog log;
    /**
     * 接收消息的Bot
     */
    public transient final Bot bot = Dhwu1a.getInstance().getBot();
    /**
     * 消息类型
     */
    public final MessageType type;
    /**
     * 消息发送者
     */
    public final MessageSender sender;
    /**
     * 消息链
     */
    protected final List<MessageItem> messageChain;

    @Nullable
    public static Message parse(JsonObject object) {
        try {
            MessageType messageType = MessageType.valueOf(object.get(TYPE).getAsString());
            return switch (messageType) {
                case FriendMessage -> new FriendMessage(object);
                case TempMessage -> new TempMessage(object);
                case GroupMessage -> new GroupMessage(object);
                case StrangerMessage -> new StrangerMessage(object);
                case OtherClientMessage -> new OtherClientMessage(object);
            };
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return null;
        }
    }

    public void quote(Consumer<Integer> messageId, List<MessageItem> messageChain) {
        Objects.requireNonNull(this.sender.getUser()).quote(this.getMessageId(), messageId, messageChain);
    }

    public void quote(Consumer<Integer> messageId, MessageItem... messageChain) {
        quote(messageId, Arrays.asList(messageChain));
    }


    public Message(MessageType type, JsonObject object) {
        this.type = type;
        this.log = new Dhwu1aLog(this.getClass());
        this.sender = MessageSender.parse(type, object.get(SENDER).getAsJsonObject());
        this.messageChain = MessageItem.parse(object.getAsJsonArray(MESSAGE_CHAIN));
    }

    public List<MessageItem> getMessageChain() {
        return messageChain;
    }

    @Override
    public String toPlainText() {
        StringBuilder stringBuilder = new StringBuilder();
        messageChain.stream().filter(messageItem -> messageItem.type != MessageItemType.Source)
                    .forEach(messageItem -> stringBuilder.append(messageItem.toPlainText()));
        return stringBuilder.toString();
    }

    public static String toPlainText(List<MessageItem> messageChain) {
        StringBuilder stringBuilder = new StringBuilder();
        messageChain.stream().filter(messageItem -> messageItem.type != MessageItemType.Source)
                    .forEach(messageItem -> stringBuilder.append(messageItem.toPlainText()));
        return stringBuilder.toString();
    }

    @Nullable
    public Source getSource() {
        return (Source) messageChain.stream().filter(messageItem -> messageItem.type == MessageItemType.Source)
                                    .findFirst().orElse(null);
    }

    @Nullable
    public Integer getMessageId() {
        if (getSource() != null)
            return getSource().id;
        return null;
    }
}
