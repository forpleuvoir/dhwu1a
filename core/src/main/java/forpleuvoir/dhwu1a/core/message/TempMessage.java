package forpleuvoir.dhwu1a.core.message;

import com.google.gson.JsonObject;
import forpleuvoir.dhwu1a.core.message.base.Message;
import forpleuvoir.dhwu1a.core.message.base.MessageType;
import forpleuvoir.dhwu1a.core.message.messagesender.TempMessageSender;
import forpleuvoir.dhwu1a.core.user.Group;
import forpleuvoir.dhwu1a.core.user.Member;

/**
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.message
 * <p>#class_name TempMessage
 * <p>#create_time 2021/7/1 21:37
 */
public class TempMessage extends Message {
    /**
     * 发送消息的群员
     */
    public final Member member;
    /**
     * 相关的群
     */
    public final Group group;

    public TempMessage(JsonObject object) {
        super(MessageType.TempMessage, object);
        this.member = (Member) sender.getUser();
        this.group = ((TempMessageSender) sender).group.getUser();
    }

    @Override
    public void printMessageLog() {
        log.info("RM/Bot.{}:[{}({})] {}(Temp {}) >> {}", bot.id, group.data.name, group.id, member.data.memberName,
                 member.id, this.toPlainText()
        );
    }
}
