package forpleuvoir.dhwu1a.core.event.message;

import forpleuvoir.dhwu1a.core.message.TempMessage;
import forpleuvoir.dhwu1a.core.user.Group;
import forpleuvoir.dhwu1a.core.user.Member;

import javax.annotation.Nullable;

/**
 * 临时消息事件
 *
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.event.message
 * <p>#class_name TempMessageEvent
 * <p>#create_time 2021/7/4 11:59
 */
public class TempMessageEvent extends MessageEvent<TempMessage> {
    /**
     * 事件相关群
     */
    public final Group group;
    /**
     * 事件相关群员
     */
    @Nullable
    public final Member member;

    protected TempMessageEvent(TempMessage message) {
        super(message);
        this.member = message.member;
        this.group = member.getGroup();
    }

    @Override
    public Member getUser() {
        return message.member;
    }
}
