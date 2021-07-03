package forpleuvoir.dhwu1a.core.event.group;

import com.google.gson.annotations.SerializedName;
import forpleuvoir.dhwu1a.core.common.data.GroupData;
import forpleuvoir.dhwu1a.core.common.data.MemberData;
import forpleuvoir.dhwu1a.core.user.Group;
import forpleuvoir.dhwu1a.core.user.base.Permission;

import javax.annotation.Nullable;

import static forpleuvoir.dhwu1a.core.common.ApiKey.MEMBER;

/**
 * 新人入群的事件
 *
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.event.group
 * <p>#class_name MemberJoinEvent
 * <p>#create_time 2021/7/3 23:48
 */
public class MemberJoinEvent extends GroupEvent {
    /**
     * 新人信息
     * <p>{@link MemberData#group}中的{@link GroupData#permission}为Bot的{@link Permission}
     */
    @SerializedName(MEMBER)
    public final MemberData member;

    private MemberJoinEvent(MemberData member) {
        super(GroupEventType.MemberJoinEvent);
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
