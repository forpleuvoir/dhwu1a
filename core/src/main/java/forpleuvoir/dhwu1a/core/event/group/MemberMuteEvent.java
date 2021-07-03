package forpleuvoir.dhwu1a.core.event.group;

import com.google.gson.annotations.SerializedName;
import forpleuvoir.dhwu1a.core.common.data.MemberData;
import forpleuvoir.dhwu1a.core.common.data.OperatorData;
import forpleuvoir.dhwu1a.core.user.Group;

import javax.annotation.Nullable;

import static forpleuvoir.dhwu1a.core.common.ApiKey.*;

/**
 * 群成员被禁言事件（该成员不是Bot）
 *
 * @author forpleuvoir
 * <p>#project_name dhwu1a
 * <p>#package forpleuvoir.dhwu1a.core.event.group
 * <p>#class_name MemberMuteEvent
 * <p>#create_time 2021/7/4 1:20
 */
public class MemberMuteEvent extends GroupEvent {
    /**
     * 禁言时长，单位为秒
     */
    @SerializedName(DURATION_SECONDS)
    public final Long durationSeconds;
    /**
     * 被消禁言的群员的信息
     */
    @SerializedName(MEMBER)
    public final MemberData member;
    /**
     * 操作者的信息，当null时为Bot操作
     */
    @SerializedName(OPERATOR)
    public final OperatorData operator;

    private MemberMuteEvent(Long durationSeconds, MemberData member,
                            OperatorData operator
    ) {
        super(GroupEventType.MemberMuteEvent);
        this.durationSeconds = durationSeconds;
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
        return String.format("%s[member:%d,operator:%d,group:%d]", type, member.id, operator.id, operator.group.id);
    }
}
