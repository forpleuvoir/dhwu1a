package forpleuvoir.dhwu1a.core.event.group;

import com.google.gson.annotations.SerializedName;
import forpleuvoir.dhwu1a.core.common.data.MemberData;
import forpleuvoir.dhwu1a.core.user.Group;

import javax.annotation.Nullable;

import static forpleuvoir.dhwu1a.core.common.ApiKey.*;

/**
 * 群名片改动
 *
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.event.group
 * <p>#class_name MemberCardChangeEvent
 * <p>#create_time 2021/7/4 1:06
 */
public class MemberCardChangeEvent extends GroupEvent {
    /**
     * 原本名片
     */
    @SerializedName(ORIGIN)
    public final String origin;
    /**
     * 现在名片
     */
    @SerializedName(CURRENT)
    public final String current;
    /**
     * 名片改动的群员的信息
     */
    @SerializedName(MEMBER)
    public final MemberData member;

    private MemberCardChangeEvent(String origin, String current,
                                  MemberData member
    ) {
        super(GroupEventType.MemberCardChangeEvent);
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
