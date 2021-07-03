package forpleuvoir.dhwu1a.core.event.group;

import com.google.gson.annotations.SerializedName;
import forpleuvoir.dhwu1a.core.common.data.MemberData;
import forpleuvoir.dhwu1a.core.user.Group;

import javax.annotation.Nullable;

import static forpleuvoir.dhwu1a.core.common.ApiKey.*;

/**
 * 群员称号改变
 *
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.event.group
 * <p>#class_name MemberHonorChangeEvent
 * <p>#create_time 2021/7/4 1:24
 */
public class MemberHonorChangeEvent extends GroupEvent {
    /**
     * 群员信息
     */
    @SerializedName(MEMBER)
    public final MemberData member;
    /**
     * 称号变化行为：achieve获得成好，lose失去称号
     */
    @SerializedName(ACTION)
    public final Action action;
    /**
     * 称号名称
     */
    @SerializedName(HONOR)
    public final String honor;

    private MemberHonorChangeEvent(MemberData member,
                                   Action action,
                                   String honor
    ) {
        super(GroupEventType.MemberHonorChangeEvent);
        this.member = member;
        this.action = action;
        this.honor = honor;
    }

    @Nullable
    @Override
    public Group getGroup() {
        return member.getGroup();
    }

    @Override
    public String toPlainText() {
        return String
                .format("%s[action:%s,honor:%s,member:%d,group:%d]", type, action, honor, member.id, member.group.id);
    }

    public enum Action {
        achieve, lose
    }
}
