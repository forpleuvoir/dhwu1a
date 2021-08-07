package forpleuvoir.dhwu1a.core.user;

import forpleuvoir.dhwu1a.core.common.data.MemberData;
import forpleuvoir.dhwu1a.core.common.data.Subject;
import forpleuvoir.dhwu1a.core.message.base.MessageSenderObject;
import forpleuvoir.dhwu1a.core.user.base.Profile;
import forpleuvoir.dhwu1a.core.user.base.User;
import forpleuvoir.dhwu1a.core.util.Dhwu1aLog;
import forpleuvoir.dhwu1a.core.util.JsonUtil;
import forpleuvoir.dhwu1a.core.websocket.base.CommandSender;
import forpleuvoir.dhwu1a.core.websocket.command.Command;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.function.Consumer;

import static forpleuvoir.dhwu1a.core.common.ApiKey.*;

/**
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.user
 * <p>#class_name Member
 * <p>#create_time 2021/7/1 21:59
 */
public class Member extends User {
    private transient static final Dhwu1aLog log = new Dhwu1aLog(Member.class);

    /**
     * 群员所在群
     */
    public final Group group;
    /**
     * 群员信息
     */
    public final MemberData data;
    /**
     * 群员资料
     */
    private Profile profile;

    public Member(Group group, MemberData memberData) {
        super(memberData.id);
        this.group = group;
        this.data = memberData;
        getProfile(null);
    }


    public void getProfile(@Nullable Consumer<Profile> consumer) {
        bot.sendCommand(
                new CommandSender(Command.memberList, Map.of(TARGET, group.id, MEMBER_ID, id)),
                data -> {
                    this.profile = JsonUtil.gson.fromJson(data, Profile.class);
                    if (consumer != null) {
                        consumer.accept(profile);
                    }
                }
        );
    }

    public void nudge() {
        bot.sendCommand(new CommandSender(
                                Command.sendNudge,
                                Map.of(TARGET, this.id, SUBJECT, group.id, KIND, Subject.SubjectType.Group)
                        ),
                        null
        );
    }

    @Override
    public MessageSenderObject messageSenderObject() {
        return new MessageSenderObject
                (Command.sendTempMessage,
                 log,
                 String.format
                         ("[%s(%d)] %s(Temp %d)",
                          group.data.name,
                          group.id,
                          data.memberName,
                          this.id
                         ),
                 this
                );
    }

    @Nullable
    public Profile getProfile() {
        return profile;
    }

    public Group getGroup() {
        return group;
    }

    @Override
    public String toJsonString() {
        return data.toJsonString();
    }
}
