package forpleuvoir.dhwu1a.core.message.base;

import forpleuvoir.dhwu1a.core.Dhwu1a;
import forpleuvoir.dhwu1a.core.message.messageitem.base.MessageItem;
import forpleuvoir.dhwu1a.core.user.Group;
import forpleuvoir.dhwu1a.core.user.Member;
import forpleuvoir.dhwu1a.core.user.base.User;
import forpleuvoir.dhwu1a.core.user.bot.Bot;
import forpleuvoir.dhwu1a.core.util.Dhwu1aLog;
import forpleuvoir.dhwu1a.core.util.JsonUtil;
import forpleuvoir.dhwu1a.core.websocket.base.CommandSender;
import forpleuvoir.dhwu1a.core.websocket.command.Command;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

import static forpleuvoir.dhwu1a.core.common.ApiKey.*;

/**
 * 消息发送器
 *
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.message.base
 * <p>#class_name MessageSenderObject
 * <p>#create_time 2021/7/4 15:48
 */
public class MessageSenderObject {

    private final Bot bot = Dhwu1a.getInstance().getBot();
    /**
     * 指令
     */
    private final Command command;
    /**
     * 日志对象
     */
    private final Dhwu1aLog log;
    /**
     * 日志格式
     */
    private final String logFormat;
    /**
     * 参数
     */
    private final Map<String, Object> params = new HashMap<>();

    public MessageSenderObject(Command command, Dhwu1aLog log, String logFormat, User user) {
        this.command = command;
        this.log = log;
        this.logFormat = logFormat;
        params.put(TARGET, user.id);
        params.put(QQ, user.id);
        if (user instanceof Group)
            params.put(GROUP, user.id);
        else if (user instanceof Member member) {
            params.put(GROUP, member.group.id);
        }
    }

    public void sendMessage(List<MessageItem> messageChain, @Nullable Consumer<Integer> messageId) {
        params.put(MESSAGE_CHAIN, messageChain);
        bot.sendCommand(new CommandSender(command, params), data ->
                Optional.ofNullable(JsonUtil.ifHasKey(data, MESSAGE_ID)).ifPresent(id -> {
                    log.info("SM/Bot.{}: {} << {}", bot.id, logFormat, Message.toPlainText(messageChain));
                    if (messageId != null)
                        messageId.accept(id.getAsInt());
                }));
    }

    public MessageSenderObject isQuoted(Integer id) {
        this.params.put(QUOTE, id);
        return this;
    }
}
