package forpleuvoir.dhwu1a.core.user;

import forpleuvoir.dhwu1a.core.common.data.GroupData;
import forpleuvoir.dhwu1a.core.common.data.MemberData;
import forpleuvoir.dhwu1a.core.message.base.MessageSenderObject;
import forpleuvoir.dhwu1a.core.user.base.User;
import forpleuvoir.dhwu1a.core.util.Dhwu1aLog;
import forpleuvoir.dhwu1a.core.util.JsonUtil;
import forpleuvoir.dhwu1a.core.websocket.base.CommandSender;
import forpleuvoir.dhwu1a.core.websocket.command.Command;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedDeque;

import static forpleuvoir.dhwu1a.core.common.ApiKey.DATA;
import static forpleuvoir.dhwu1a.core.common.ApiKey.TARGET;
import static forpleuvoir.dhwu1a.core.common.data.UserData.bot;

/**
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.user
 * <p>#class_name Group
 * <p>#create_time 2021/7/1 21:58
 */
public class Group extends User {
    private transient static final Dhwu1aLog log = new Dhwu1aLog(Group.class);
    /**
     * 群信息
     */
    public final GroupData data;

    private final ConcurrentLinkedDeque<Member> members = new ConcurrentLinkedDeque<>();

    public Group(GroupData groupData) {
        super(groupData.id);
        this.data = groupData;
        syncMember();
    }


    public void syncMember() {
        log.info("({})同步群员列表", data.name);
        bot.sendCommand(
                new CommandSender(Command.memberList,
                                  Map.of(TARGET, id)
                ), data -> {
                    if (data.get(DATA).isJsonArray()) {
                        members.clear();
                        data.get(DATA).getAsJsonArray().forEach(element -> {
                            var memberData = JsonUtil.gson.fromJson(element, MemberData.class);
                            members.add(new Member(this, memberData));
                        });
                    }
                }
        );
    }

    @Override
    public MessageSenderObject messageSenderObject() {
        return new MessageSenderObject(Command.sendGroupMessage, log,
                                       String.format("[%s(%d)]", this.data.name, this.id),
                                       this
        );
    }

    @Override
    public String toJsonString() {
        return data.toJsonString();
    }

    @Nullable
    public Member getMember(Long id) {
        return members.stream().filter(member -> member.id.equals(id)).findFirst().orElse(null);
    }
}
