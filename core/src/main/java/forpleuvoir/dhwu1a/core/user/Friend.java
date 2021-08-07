package forpleuvoir.dhwu1a.core.user;

import forpleuvoir.dhwu1a.core.common.data.FriendData;
import forpleuvoir.dhwu1a.core.common.data.Subject;
import forpleuvoir.dhwu1a.core.message.base.MessageSenderObject;
import forpleuvoir.dhwu1a.core.user.base.User;
import forpleuvoir.dhwu1a.core.util.Dhwu1aLog;
import forpleuvoir.dhwu1a.core.websocket.base.CommandSender;
import forpleuvoir.dhwu1a.core.websocket.command.Command;

import java.util.Map;

import static forpleuvoir.dhwu1a.core.common.ApiKey.*;

/**
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.user
 * <p>#class_name Friend
 * <p>#create_time 2021/6/29 22:44
 */
public class Friend extends User {
    private transient static final Dhwu1aLog log = new Dhwu1aLog(Friend.class);
    /**
     * 好友信息
     */
    public final FriendData data;


    public Friend(FriendData data) {
        super(data.id);
        this.data = data;
    }

    public void nudge() {
        bot.sendCommand(new CommandSender(
                                Command.sendNudge,
                                Map.of(TARGET, this.id, SUBJECT, this.id, KIND, Subject.SubjectType.Friend)
                        ),
                        null
        );
    }

    @Override
    public String toJsonString() {
        return data.toJsonString();
    }

    @Override
    public MessageSenderObject messageSenderObject() {
        return new MessageSenderObject(Command.sendFriendMessage, log,
                                       String.format("%s(%d)", this.data.nickname, this.id), this
        );
    }
}
