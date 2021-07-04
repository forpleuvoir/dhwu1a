package forpleuvoir.dhwu1a.core.message;

import com.google.gson.JsonObject;
import forpleuvoir.dhwu1a.core.message.base.Message;
import forpleuvoir.dhwu1a.core.message.base.MessageType;
import forpleuvoir.dhwu1a.core.message.messagesender.GroupMessageSender;
import forpleuvoir.dhwu1a.core.user.Group;
import forpleuvoir.dhwu1a.core.user.Member;

import javax.annotation.Nullable;

/**
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.message
 * <p>#class_name GroupMessage
 * <p>#create_time 2021/7/1 20:51
 */
public class GroupMessage extends Message {

    /**
     * 相关的群
     */
    public final Group group;
    /**
     * 发送消息的群员
     */
    public final Member member;

    public GroupMessage(JsonObject object) {
        super(MessageType.GroupMessage, object);
        this.group = (Group) sender.getUser();
        this.member = getMember();
    }

    @Nullable
    public Member getMember() {
        return ((GroupMessageSender) sender).getMember();
    }

    @Override
    public void printMessageLog() {
        log.info("RM/Bot.{}:[{}({})] {}({}) >> {}", bot.id, group.data.name, group.id,
                 member.data.memberName,
                 member.id, this.toPlainText()
        );
    }

}
