package forpleuvoir.dhwu1a.core.event.message;

import forpleuvoir.dhwu1a.core.message.GroupMessage;
import forpleuvoir.dhwu1a.core.user.Group;
import forpleuvoir.dhwu1a.core.user.Member;

import javax.annotation.Nullable;

/**
 * 群消息事件
 *
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.event.message
 * <p>#class_name GroupMessageEvent
 * <p>#create_time 2021/7/4 11:50
 */
public class GroupMessageEvent extends MessageEvent<GroupMessage> {
    /**
     * 事件相关群
     */
    public final Group group;
    /**
     * 事件相关群员
     */
    @Nullable
    public final Member member;

    public GroupMessageEvent(GroupMessage message) {
        super(message);
        this.group = message.group;
        this.member = message.getMember();
    }

    @Nullable
    public Member getMember() {
        return member;
    }

    @Override
    public Group getUser() {
        return group;
    }
}
