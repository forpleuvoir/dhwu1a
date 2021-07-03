package forpleuvoir.dhwu1a.core.event.group;

import com.google.gson.annotations.SerializedName;
import forpleuvoir.dhwu1a.core.common.data.MemberData;
import forpleuvoir.dhwu1a.core.user.Group;

import javax.annotation.Nullable;

import static forpleuvoir.dhwu1a.core.common.ApiKey.*;

/**
 * 群头衔改动（只有群主有操作限权）
 *
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.event.group
 * <p>#class_name MemberSpecialTitleChangeEvent
 * <p>#create_time 2021/7/4 1:11
 */
public class MemberSpecialTitleChangeEvent extends GroupEvent {
    /**
     * 原头衔
     */
    @SerializedName(ORIGIN)
    public final String origin;
    /**
     * 现头衔
     */
    @SerializedName(CURRENT)
    public final String current;
    /**
     * 名片改动的群员的信息
     */
    @SerializedName(MEMBER)
    public final MemberData member;

    private MemberSpecialTitleChangeEvent(String origin, String current,
                                          MemberData member
    ) {
        super(GroupEventType.MemberSpecialTitleChangeEvent);
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
