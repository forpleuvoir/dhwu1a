package forpleuvoir.dhwu1a.core.event.group;

import com.google.gson.annotations.SerializedName;
import forpleuvoir.dhwu1a.core.common.data.MemberData;
import forpleuvoir.dhwu1a.core.user.Group;
import forpleuvoir.dhwu1a.core.user.base.Permission;

import javax.annotation.Nullable;

import static forpleuvoir.dhwu1a.core.common.ApiKey.*;

/**
 * 成员权限改变的事件（该成员不是Bot）
 *
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.event.group
 * <p>#class_name MemberPermissionChangeEvent
 * <p>#create_time 2021/7/4 1:13
 */
public class MemberPermissionChangeEvent extends GroupEvent {
    /**
     * 原权限
     */
    @SerializedName(ORIGIN)
    public final Permission origin;
    /**
     * 现权限
     */
    @SerializedName(CURRENT)
    public final Permission current;
    /**
     * 名片改动的群员的信息
     */
    @SerializedName(MEMBER)
    public final MemberData member;

    private MemberPermissionChangeEvent(Permission origin,
                                        Permission current,
                                        MemberData member
    ) {
        super(GroupEventType.MemberPermissionChangeEvent);
        this.origin = origin;
        this.current = current;
        this.member = member;
    }

    @Nullable
    @Override
    public Group getGroup() {
        return member.getGroup();
    }

    @Override
    public String toPlainText() {
        return String.format("%s[origin:%s,current:%s,member:%d,group:%d]", type, origin, current, member.id,
                             member.group.id
        );
    }
}
