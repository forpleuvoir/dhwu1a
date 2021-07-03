package forpleuvoir.dhwu1a.core.event.group;

import com.google.gson.annotations.SerializedName;
import forpleuvoir.dhwu1a.core.common.data.MemberData;
import forpleuvoir.dhwu1a.core.user.Group;

import javax.annotation.Nullable;

import static forpleuvoir.dhwu1a.core.common.ApiKey.MEMBER;

/**
 * 成员主动离群（该成员不是Bot）
 *
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.event.group
 * <p>#class_name MemberLeaveEventQuit
 * <p>#create_time 2021/7/4 0:05
 */
public class MemberLeaveEventQuit extends GroupEvent {

    /**
     * 退群群员的信息
     */
    @SerializedName(MEMBER)
    public final MemberData member;

    private MemberLeaveEventQuit(MemberData member) {
        super(GroupEventType.MemberLeaveEventQuit);
        this.member = member;
    }

    @Nullable
    @Override
    public Group getGroup() {
        return member.getGroup();
    }

    @Override
    public String toPlainText() {
        return String.format("%s[member:%d,group:%d]", type, member.id, member.group.id);
    }
}
