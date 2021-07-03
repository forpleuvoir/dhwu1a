package forpleuvoir.dhwu1a.core.event.group;

import com.google.gson.annotations.SerializedName;
import forpleuvoir.dhwu1a.core.common.data.GroupData;
import forpleuvoir.dhwu1a.core.common.data.MemberData;
import forpleuvoir.dhwu1a.core.common.data.OperatorData;
import forpleuvoir.dhwu1a.core.user.Group;
import forpleuvoir.dhwu1a.core.user.base.Permission;

import javax.annotation.Nullable;

import static forpleuvoir.dhwu1a.core.common.ApiKey.MEMBER;
import static forpleuvoir.dhwu1a.core.common.ApiKey.OPERATOR;

/**
 * 成员被踢出群（该成员不是Bot）
 *
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.event.group
 * <p>#class_name MemberLeaveEventKick
 * <p>#create_time 2021/7/3 23:58
 */
public class MemberLeaveEventKick extends GroupEvent {
    /**
     * 被踢者的信息
     * <p>{@link MemberData#group}中的{@link GroupData#permission}为Bot的{@link Permission}
     */
    @SerializedName(MEMBER)
    public final MemberData member;
    /**
     * 操作的管理员或群主信息，当null时为Bot操作
     */
    @SerializedName(OPERATOR)
    public final OperatorData operator;

    private MemberLeaveEventKick(MemberData member,
                                 OperatorData operator
    ) {
        super(GroupEventType.MemberLeaveEventKick);
        this.member = member;
        this.operator = operator;
    }

    @Nullable
    @Override
    public Group getGroup() {
        return operator.getGroup();
    }

    @Override
    public String toPlainText() {
        return String.format("%s[member:%d,operator:%d]", type, member.id, operator == null ? bot.id : operator.id);
    }
}
